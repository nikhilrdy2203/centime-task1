package com.assignment.serviceone.controller;

import com.assignment.serviceone.exception.InValidJsonProvidedException;
import com.assignment.serviceone.model.TheJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping(
        path = "/service1"
)

public class OrchestrationController {

    @Autowired
    private Tracer tracer;

    private static final Logger logger = LoggerFactory.getLogger(OrchestrationController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.properties.service2-greet-url}")
    private String SERVICE2_GREET_URL;

    @Value("${app.properties.service3-concatenate-url}")
    private String SERVICE3_CONCATENATE_URL;

    @GetMapping(
            path = "/status"
    )
    public String getServiceStatus(){
        String traceId = tracer.currentSpan().context().traceId();
        logger.info("getServiceStatus endpoint called with [TraceId]:{}", traceId);
        return "Up";
    }

    @PostMapping("/concatenate")
    public ResponseEntity<String> concatenateStrings(@RequestBody @Valid TheJson json, BindingResult bindingResult) {

        String traceId = tracer.currentSpan().context().traceId();
        logger.info("Concatenate endpoint called with payload: {} with [TraceId]:{}", json,traceId);

        if(bindingResult.hasErrors()){

            StringBuilder errorMessage = new StringBuilder();

            for(FieldError error : bindingResult.getFieldErrors()){
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            throw new InValidJsonProvidedException(errorMessage.toString());
        }

        String responseFromService2 = restTemplate.getForEntity(SERVICE2_GREET_URL, String.class).getBody();
        String responseFromService3 = restTemplate.postForEntity(SERVICE3_CONCATENATE_URL, json, String.class).getBody();

        String finalResponce = responseFromService2.concat(" "+responseFromService3);

        return new ResponseEntity<>(
                finalResponce,
                HttpStatus.OK
        );
    }

}
