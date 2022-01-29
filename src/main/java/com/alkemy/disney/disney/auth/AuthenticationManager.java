package com.alkemy.disney.disney.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationManager {

    public Authentication authenticate(Authentication a) throws AuthenticationException;

}
