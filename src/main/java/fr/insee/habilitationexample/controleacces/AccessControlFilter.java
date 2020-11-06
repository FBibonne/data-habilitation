package fr.insee.habilitationexample.controleacces;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
@Slf4j
public class AccessControlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Entrée de :"+((HttpServletRequest) request).getPathTranslated());
        chain.doFilter(request, response);
        log.info("Sortie de :"+response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("INIT controle accès");
    }

    @Override
    public void destroy() {

    }
}
