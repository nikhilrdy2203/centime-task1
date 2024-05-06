package com.assignment.servicethere.controller;

import com.assignment.servicethere.model.TheJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "/service3"
)
public class ConcatenateController {

    private static final Logger logger = LoggerFactory.getLogger(ConcatenateController.class);

    @Autowired
    private Tracer tracer;

    @PostMapping(
            path = "/concatenate"
    )
    public ResponseEntity<String> concatenate(@RequestBody TheJson request){
        String traceId = tracer.currentSpan().context().traceId();
        logger.info("Concatenate method called in service 3 with [TraceId] : {}", traceId);
        String response = request.getName() +" "+ request.getSurname();
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}
