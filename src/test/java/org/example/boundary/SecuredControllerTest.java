package org.example.boundary;

import org.example.InitialisePrincipalFilter;
import org.example.WebSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {WebSecurityConfig.class, InitialisePrincipalFilter.class, SecuredController.class})
@AutoConfigureMockMvc
class SecuredControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnForbiddenWhenAuthorisationMissing() throws Exception {
        this.mockMvc.perform(get("/api/secured"))
                .andExpect(status()
                        .isForbidden());
    }

    @Test
    void shouldReturnMessageWhenAuthorisationPresent() throws Exception {
        this.mockMvc.perform(get("/api/secured")
                        .header("id", "user")
                        .header("roles", "USER"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .string(containsString("Hello Secured!")));
    }
}