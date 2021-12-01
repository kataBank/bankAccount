package com.bank.account.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bank.account.entity.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }
  /*
   * @Override public void doFilter(ServletRequest request, ServletResponse
   * response, FilterChain chain) throws IOException, ServletException {
   *
   * }
   */

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      Client user = new ObjectMapper().readValue(request.getInputStream(), Client.class);
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getAccountNumber(), user.getClientId()));
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }


  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {

    CustomUser user = (CustomUser) authResult.getPrincipal();

    List<String> roles = new ArrayList<>();

    authResult
        .getAuthorities()
        .forEach(
            a -> {
              roles.add(a.getAuthority());
            });

    // Generate JWT
    String jwt =
        JWT.create()
            .withIssuer(request.getRequestURI())
            .withSubject(user.getAccountNumber())
            .withClaim("id", user.getId())
            .withClaim("accountNumber", user.getAccountNumber())
            .withClaim("firstName", user.getFirstName())
            .withClaim("lastName", user.getLastName())
            .withClaim("emailAdress", user.getEmailAdress())
            .withClaim("phoneNumber", user.getPhoneNumber())
            .withClaim("birtDayDate", user.getBirtDayDate().toString())
            .withClaim("clientId", user.getClientId())
            .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityParams.EXPIRATION))
            .sign(Algorithm.HMAC256(SecurityParams.SECRET));

    response.getWriter().write(new ObjectMapper().writeValueAsString(jwt));
  }
}
