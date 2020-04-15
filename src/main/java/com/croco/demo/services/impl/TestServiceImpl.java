package com.croco.demo.services.impl;

import com.croco.demo.domains.Test;
import com.croco.demo.repositories.TestRepository;
import com.croco.demo.services.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    private final Logger log = LoggerFactory.getLogger(TestService.class);

    @Autowired
    TestRepository testRepository;

    @Override
    public void test() {
        List<Test> testList = new ArrayList<>();
        testList = testRepository.findAll();

        Test test = new Test();
        test.setId(testList == null || testList.isEmpty() ? Long.valueOf(0) :
                 Long.valueOf(testList.size()));

        test.setTest("this is test number " + String.valueOf(testList == null || testList.isEmpty() ? 0 : testList.size()));

        testRepository.save(test);

        log.info("test info");
        log.debug("test debug");
        log.error("test error");

    }
}
