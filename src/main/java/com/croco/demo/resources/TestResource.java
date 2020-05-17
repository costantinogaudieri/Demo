package com.croco.demo.resources;

import com.croco.demo.services.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lol/tst")
public class TestResource {

    @Autowired
    private TestService testService;

    private final Logger log = LoggerFactory.getLogger(TestResource.class);

    @GetMapping(value="/hello")
    //@RequestMapping(value="/",method=RequestMethod.GET)
    public String hello(){

        log.debug("start invoke service");
        testService.test();
        log.debug("end invoke service");

        return "res";
    }
}
