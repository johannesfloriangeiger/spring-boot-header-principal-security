package org.example.model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record Principal(String identity, boolean authenticated,
                        List<GrantedAuthority> authorities) implements Authentication {

    private static final AnonymousAuthenticationToken ANONYMOUS_PRINCIPAL = new AnonymousAuthenticationToken("ignored", "ignored", AuthorityUtils.createAuthorityList("ignored"));

    private static final String HEADER_IDENTITY = "id";

    private static final String HEADER_ROLES = "roles";

    private static final String HEADER_ROLES_SEPARATOR = ",";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.identity;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return null;
    }

    public static Authentication from(final HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader(HEADER_IDENTITY))
                .flatMap(identity -> Optional.ofNullable(httpServletRequest.getHeader(HEADER_ROLES))
                        .map(roles -> Arrays.stream(roles.split(HEADER_ROLES_SEPARATOR))
                                .map("ROLE_%s"::formatted)
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.<GrantedAuthority>toList()))
                        .map(authorities -> (Authentication) new Principal(identity, true, authorities)))
                .orElse(ANONYMOUS_PRINCIPAL);
    }
}