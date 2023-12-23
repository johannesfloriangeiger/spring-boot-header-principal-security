package org.example.boundary;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("api/admin")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public String read() {
        return "Hello Admin!";
    }
}