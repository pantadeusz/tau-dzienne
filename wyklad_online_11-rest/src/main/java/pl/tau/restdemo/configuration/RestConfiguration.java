package pl.tau.restdemo.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc // tak jak w XML: <mvc:annotation-driven />
@ComponentScan({"pl.tau.restdemo"})
public class RestConfiguration {
    //@Override
    //public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
    //    super.configureMessageConverters(messageConverters);
    //}
}



