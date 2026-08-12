package main.java.backend.hello.service;

import main.java.backend.hello.dto.HelloResponse;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public HelloResponse getMessage() {
        return new HelloResponse(
                "Welcome to Airport Operations Playground!"
        );
    }
}