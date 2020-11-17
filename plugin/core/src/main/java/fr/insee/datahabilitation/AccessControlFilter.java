package fr.insee.datahabilitation;

import fr.insee.datahabilitation.utils.StreamUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class AccessControlFilter implements Filter {

    private UserIdFinder userIdFinder;
    private ResourcesAllowedService resourcesAllowedService;
    private AllowedResourcesMatcherBuilder allowedResourcesMatcherBuilder;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException{
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("Entrée de :"+req.getMethod()+ " "+req.getRequestURI());
        if (log.isDebugEnabled()){
            log.debug(attributesOrHeadersToString(req::getHeaderNames, req::getHeader));
            log.debug(attributesOrHeadersToString(req::getAttributeNames, req::getAttribute));
        }
        try{
            if (checkAccess(req)){
                chain.doFilter(request, response);
            }else{
                ((HttpServletResponse)response).sendError(403);
            }
        }catch(Exception | UnexpectedDataForAuthentificationException e){
            log.error("Error in processing access check (see causes)", e);
            ((HttpServletResponse)response).sendError(500, "Error in processing access check : "+e.getMessage());
        }

        log.info("Sortie de :"+response);
    }

    private String attributesOrHeadersToString(Supplier<Enumeration<String>> source, Function<String, Object> keyMapper) {
        return StreamUtils.enumerationToParallelisableStream(source.get(),
                Spliterator.CONCURRENT, Spliterator.SUBSIZED )
                .map(s->s+" = "+keyMapper.apply(s)).collect(Collectors.joining(System.lineSeparator()));
    }

    private boolean checkAccess(HttpServletRequest req) throws InvalidHttpMethodNameException, InvalidResourcePathException, UnexpectedDataForAuthentificationException {

        UserId userId = userIdFinder.find(req);
        if(userId.isNotAuthentified()){
            return true;
        }else{
            ResourcesAllowed resourcesAllowed =  resourcesAllowedService.findByUserId(userId);
            Resource resourceTarget =new Resource(req.getMethod(), req.getRequestURI());
            return allowedResourcesMatcherBuilder.checkIf(resourceTarget).match(resourcesAllowed);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("INIT controle accès");
        userIdFinder = new DefaultUserIdFinder();
        resourcesAllowedService=(userId)->new ResourcesAllowed() {
            @Override
            public Stream<Resource> resources() {
                try{
                    return Stream.of(
                            new Resource("GET", "/equipes/4/joueurs/1"),
                            new Resource("GET", "/equipes/4/joueurs/2"),
                            new Resource("GET", "/equipes")
                    );
                }catch (Exception e){
                    return Stream.ofNullable(null);
                }
            }
        };
        allowedResourcesMatcherBuilder =new AllowedResourcesMatcherBuilder() {
            @Override
            public ResourceMatcher matcher() {
                return Resource::equals;
            }
        };
    }

    @Override
    public void destroy() {

    }
}
