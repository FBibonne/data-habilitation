package fr.insee.datahabilitation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

@HandlesTypes()
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class AccessControlRegister implements ServletContainerInitializer, WebApplicationInitializer {

    /*
    Registration of our filter by ServletContainerInitializer (if the master app is not a spring app)

    Note from ServletContainerInitializer javadoc :
        Implementations of this interface must be declared by a JAR file resource located inside the META-INF/services
        directory and named for the fully qualified class name of this interface, and will be discovered using the
        runtime's service provider lookup mechanism or a container specific mechanism that is semantically equivalent
        to it. In either case, ServletContainerInitializer services from web fragment JAR files excluded from an
        absolute ordering must be ignored, and the order in which these services are discovered must follow the
        application's classloading delegation model.

     */
    @Override
    public void onStartup(@Nullable Set<Class<?>> webAppInitializerClasses, ServletContext ctx) throws ServletException {
        if ( ! isThereSpringInitializerInHandlesTypes(webAppInitializerClasses)){
            FilterRegistration.Dynamic registration = ctx.addFilter("accessControlFilter", new AccessControlFilter());
            registration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),true, "/*");
            registration.setAsyncSupported(true);
        }
    }

    private boolean isThereSpringInitializerInHandlesTypes(Set<Class<?>> webAppInitializerClasses) {
        return webAppInitializerClasses!=null
                && webAppInitializerClasses.stream().anyMatch(WebApplicationInitializer.class::isAssignableFrom);
    }


    /*
        Registration of filter in master app spring context : this method is called after all others spring registred
        filters and so the filter is registred as the last of the chain
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic registration = servletContext.addFilter("accessControlFilter", new AccessControlFilter());
        registration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),true, "/*");
        registration.setAsyncSupported(true);
    }
}
