package org.example.boundary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("api/public")
    public String read() {
        return "Hello Public!";
    }
}