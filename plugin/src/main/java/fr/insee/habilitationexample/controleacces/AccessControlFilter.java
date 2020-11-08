package fr.insee.habilitationexample.controleacces;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

@Slf4j
public class AccessControlFilter implements Filter {

    private UserIdFinder userIdFinder;
    private ResourcesAllowedService resourcesAllowedService;
    private ResourcesMatcher resourcesMatcher;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("Entrée de :"+req.getMethod()+ " "+req.getRequestURI());
        try{
            if (checkAccess(req)){
                chain.doFilter(request, response);
            }else{
                ((HttpServletResponse)response).sendError(403);
            }
        }catch(Exception e){
            log.error("Error in processing access check (see causes)", e);
            ((HttpServletResponse)response).sendError(500, "Error in processing access check : "+e.getMessage());
        }

        log.info("Sortie de :"+response);
    }

    private boolean checkAccess(HttpServletRequest req) throws Exception{

        UserId userId = userIdFinder.find(req);
        ResourcesAllowed resourcesAllowed =  resourcesAllowedService.findByUserId(userId);
        Resource resourceTarget =new Resource(req.getMethod(), req.getRequestURI());
        return resourcesMatcher.checkIf(resourceTarget).match(resourcesAllowed);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("INIT controle accès");
        userIdFinder = (request)->new UserId("user");
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
        resourcesMatcher=Resource::equals;
    }

    @Override
    public void destroy() {

    }
}
