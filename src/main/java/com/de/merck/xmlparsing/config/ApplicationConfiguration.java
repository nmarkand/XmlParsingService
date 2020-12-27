package com.de.merck.xmlparsing.config;

import io.prometheus.client.Counter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class ApplicationConfiguration {

    //ServletContext - Created by web container and one per web application.
    //ApplicationContext -- Created by spring and several ApplicationContexts are possible for a web application.
    // Example -       ApplicationContext cm1 = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    //        ApplicationContext cm2 = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

    //One ServletContext may contain several ApplicationContext

    /* A single instance of 'application' scoped bean lives within a ServletContext instance. That means it can be used
    between multiple servlet based applications running in the same ServletContext, e.g. two Spring's ApplicationContexts
    can use the same 'application' scoped bean. The default 'singleton' bean lives only within a single ApplicationContext,
     whereas, an 'application' bean lives within ServletContext i.e. across multiple ApplicationContexts. Spring stores
     'application' scoped bean as a regular ServletContext attribute.*/

    // Benefit here is when globalCounter is used from anywhere within the ServletContext of application (from within several ApplicationContexts)
    // its count get increased and we will get real count. So all in all it is a global counter.

    @Bean
    @ApplicationScope
    public Counter globalCounter() {
        return Counter.build().
                name("XmlParsingApplicationGlobalCounter")
                .help("XmlParsingApplicationGlobalCounter").register();
    }

    //Local counter is specific to XmlParsingService application
    //Which means it can not be used by other spring ApplicationContexts
    //It is a singleton bean for XmlParsingService application and it will create separate objects for separate ApplicationContext.
    @Bean
    public Counter localCounter() {
        return Counter.build().
                name("XmlParsingApplicationLocalCounter")
                .help("XmlParsingApplicationLocalCounter").register();
    }
}
