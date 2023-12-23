package org.example.boundary;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

    @GetMapping("api/secured")
    @PreAuthorize("isAuthenticated()")
    public String read() {
        return "Hello Secured!";
    }
}