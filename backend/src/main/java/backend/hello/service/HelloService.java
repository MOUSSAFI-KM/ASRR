package backend.hello.service;

import backend.hello.dto.HelloResponse;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public HelloResponse getMessage() {
        return new HelloResponse(
                "Welcome to Airport Operations Playground!"
        );
    }
}