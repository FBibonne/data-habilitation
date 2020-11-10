package fr.insee.datahabilitation;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

@HandlesTypes(MonFiltre.class)
public class AccessControlRegister implements ServletContainerInitializer {

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
            FilterRegistration.Dynamic registration = ctx.addFilter("accessControlFilter", new AccessControlFilter());
            registration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),true, "/*");
            registration.setAsyncSupported(true);
    }


}
