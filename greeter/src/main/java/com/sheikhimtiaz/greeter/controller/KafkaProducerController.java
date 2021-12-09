package com.sheikhimtiaz.greeter.controller;


import com.sheikhimtiaz.greeter.service.KafKaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController
{
    private final KafKaProducerService producerService;

    @Autowired
    public KafkaProducerController(KafKaProducerService producerService)
    {
        this.producerService = producerService;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message)
    {
        this.producerService.sendMessage(message);
    }

    @PostMapping(value = "/greet")
    public String greetUser(@RequestParam("name") String name)
    {
        return "Hello "+name+"!";
    }

    @GetMapping(value = "/greet")
    public String checkHealth()
    {
        return "UP and running!";
    }
}

