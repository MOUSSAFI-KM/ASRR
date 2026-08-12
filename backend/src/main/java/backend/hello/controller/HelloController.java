package main.java.backend.hello.controller;


import main.java.backend.hello.dto.HelloResponse;
import main.java.backend.hello.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    @Operation(
            summary = "Get welcome message",
            description = "Returns the welcome message of the application."
    )
    @GetMapping
    public HelloResponse hello() {
        return helloService.getMessage();
    }
}