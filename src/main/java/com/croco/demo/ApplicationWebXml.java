package com.croco.demo;

import com.croco.demo.config.DefaultProfileUtlil;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@ComponentScan
@EnableAutoConfiguration
public class ApplicationWebXml extends SpringBootServletInitializer implements WebApplicationInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        DefaultProfileUtlil.addDefaultProfile(application.application());
        return application.bannerMode(Banner.Mode.OFF).sources(DemoApplication.class);
    }
}
