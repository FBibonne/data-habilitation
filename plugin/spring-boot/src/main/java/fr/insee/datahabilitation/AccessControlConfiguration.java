package fr.insee.datahabilitation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.DispatcherType;
import java.util.Collections;
import java.util.EnumSet;

@Configuration
@Slf4j
public class AccessControlConfiguration {


    @Bean
    public FilterRegistrationBean<AccessControlFilter> accessControlRegister(){
        FilterRegistrationBean<AccessControlFilter> registrer=new FilterRegistrationBean<>();
        registrer.setFilter(new AccessControlFilter());
        registrer.setAsyncSupported(true);
        registrer.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        registrer.setEnabled(true);
        registrer.setMatchAfter(true);
        registrer.setName("accessControlFilter");
        registrer.setOrder(Ordered.LOWEST_PRECEDENCE);
        registrer.setUrlPatterns(Collections.singleton("/*"));
        return registrer;
    }
}
