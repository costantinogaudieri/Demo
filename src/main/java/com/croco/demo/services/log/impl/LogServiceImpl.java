package com.croco.demo.services.log.impl;



import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.croco.demo.domains.configurationParameters.StartupParameter;
import com.croco.demo.repositories.configurationParameters.StartupParameterRepository;
import com.croco.demo.services.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(LogService.class);

    @Autowired
    StartupParameterRepository startupParameterRepository;

    private static final List<String> lEVELS_LIST = new ArrayList<>(Arrays.asList("INFO","DEBUG","ERROR",
                                        "ALL","WARN","FATAL","OFF","TRACE"));

    @PostConstruct
    public void postConstruct(){
        List<StartupParameter> params = startupParameterRepository.findByCod("LOG_LEVEL");

        if(params != null && !params.isEmpty()){
            StartupParameter param = params.get(0);
            String logLevel = param.getValue();
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

            switch (logLevel.toUpperCase()){
                case "DEBUG":
                    context.getLogger("com.croco.demo").setLevel(Level.DEBUG);
                    log.debug("LOG LEVEL SET TO DEBUG");
                    break;
                case "INFO":
                    context.getLogger("com.croco.demo").setLevel(Level.INFO);
                    log.info("LOG LEVEL SET TO INFO");
                    break;
                case "ERROR":
                    context.getLogger("com.croco.demo").setLevel(Level.ERROR);
                    log.error("LOG LEVEL SET TO ERROR");
            }


        }
    }

    @Override
    public void setLevelRuntime(String level) {
        if(level != null && lEVELS_LIST.contains(level.toUpperCase())){
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

            switch (level.toUpperCase()){
                case "DEBUG":
                    context.getLogger("com.croco.demo").setLevel(Level.DEBUG);
                    log.debug("LOG LEVEL SET TO DEBUG");
                    break;
                case "INFO":
                    context.getLogger("com.croco.demo").setLevel(Level.INFO);
                    log.info("LOG LEVEL SET TO INFO");
                    break;
                case "ERROR":
                    context.getLogger("com.croco.demo").setLevel(Level.ERROR);
                    log.error("LOG LEVEL SET TO ERROR");
            }


        }
    }
}
