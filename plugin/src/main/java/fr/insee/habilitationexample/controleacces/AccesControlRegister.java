package fr.insee.habilitationexample.controleacces;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(WebApplicationInitializer.class)
public class AccesControlRegister implements ServletContainerInitializer, WebApplicationInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }
}
