package com.assignment.servicetwo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "service2"
)
public class GreetController {

    private static final Logger log = LoggerFactory.getLogger(GreetController.class);

    @Autowired
    private Tracer tracer;

    @GetMapping(path = "hello")
    public ResponseEntity<String> getHello(){

        String traceId = tracer.currentSpan().context().traceId();
        log.info("getHello method called in service 2 with [[TraceID] : {} ] ", traceId);

        return new ResponseEntity<>(
                "Hello",
                HttpStatus.OK
        );
    }
}
