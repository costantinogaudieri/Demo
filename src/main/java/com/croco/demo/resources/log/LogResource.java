package com.croco.demo.resources.log;

import com.croco.demo.services.log.LogService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/loglevel")

public class LogResource {

    private final Logger log = LoggerFactory.getLogger(LogResource.class);

    @Autowired
    LogService logService;

    @GetMapping("/setlog/{level}")
    @ApiOperation(value = "Runtime setting level logs")
    public void setLog(@PathVariable String level){
        logService.setLevelRuntime(level);
    }

}
