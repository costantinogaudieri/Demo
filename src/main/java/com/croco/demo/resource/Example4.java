package com.croco.demo.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lol/tst")
public class Example4 {

    private final Logger log = LoggerFactory.getLogger(Example4.class);

    @GetMapping(value="/hello")
    //@RequestMapping(value="/",method=RequestMethod.GET)
    public String hello(){

        log.error("CIAOOO");
        return ("HIIIIIIIIIIII");
    }
}
