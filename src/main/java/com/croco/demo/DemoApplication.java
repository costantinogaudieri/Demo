package com.croco.demo;

import com.croco.demo.config.DefaultProfileUtlil;
import io.github.jhipster.config.JHipsterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableConfigurationProperties
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	private final Environment env;

	public DemoApplication(Environment env) {
		this.env = env;
	}

	@PostConstruct
	public void initApplication() {
		Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
		if(activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) &&
				activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION) &&
				activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_TEST)){
			log.error("you have missconfigured application. It should not " +
					"run with both dev test e prd profiles at the same time");
		}

		if(activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) &&
		activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_CLOUD)){
			log.error("you have missconfigured application. It should not " +
					"run with both dev e cloud profiles at the same time");
		}
	}

	public static void main(String[] args) throws UnknownHostException {
		//SpringApplication.run(DemoApplication.class, args);
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		DefaultProfileUtlil.addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		String protocol = "http";
		if(env.getProperty("server.ssl.key-store") != null){
			protocol = "https";
		}

		log.debug("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\t{}://localhost:{}\n\t" +
						"External: \t{}://{}:{}\n\t" +
						"Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol,
				env.getProperty("server.port"),
				protocol,
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"),
				//env.getActiveProfiles()
				new String[] {"dev","no-liquibase" }
		);
	}

}
