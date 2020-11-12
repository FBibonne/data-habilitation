package fr.insee.datahabilitation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class AccessControlRegister implements WebApplicationInitializer {


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
