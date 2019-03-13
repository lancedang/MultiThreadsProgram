package com.mdc.example.mdcdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mdc")
public class MdcController {

    private Logger logger = LoggerFactory.getLogger(MdcController.class);

    @RequestMapping("/test")
    public void testMdc() {
        logger.info("MDCServlet has been called");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("MDCServlet has been finished");
    }

}
