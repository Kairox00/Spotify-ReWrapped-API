package com.spotify.rewrapped.configs;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {

    HttpServletResponse response = (HttpServletResponse) res;
    HttpServletRequest request = (HttpServletRequest) req;

    // Add CORS headers to every response
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers",
        "Content-Type, Authorization, X-API-Key, X-Amz-Date, X-Api-Key, X-Amz-Security-Token, x-api-key");
    response.setHeader("Access-Control-Max-Age", "3600");

    // Handle preflight OPTIONS request
    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
      System.out.println("=== CORS Filter: Handling OPTIONS request ===");
      response.setStatus(HttpServletResponse.SC_OK);
      return;
    }

    System.out.println("=== CORS Filter: Adding headers to " + request.getMethod() + " request ===");

    chain.doFilter(req, res);
  }
}