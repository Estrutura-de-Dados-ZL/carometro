package com.carometro.security;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.carometro.wrapper.MultiReadHttpServletRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmailVerificationFilter extends OncePerRequestFilter {

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
      if (!validarRotasRequisicao(request)) {
        filterChain.doFilter(request, response);
        return;
      }

      if (!validarVerboRequisicao(request)) {
        filterChain.doFilter(request, response);
        return;
      }

      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String role = buscarRole(userDetails);

      if (role.equals("ADMIN")) {
        filterChain.doFilter(request, response);
        return;
      }

      MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
      String emailInToken = userDetails.getUsername();
      String emailInRequest = buscarEmailRequisicao(request, wrappedRequest);

      if (!emailInToken.equals(emailInRequest)) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("You are not authorized to modify this resource.");
        return;
      }

      filterChain.doFilter(wrappedRequest, response);
    } else {
      filterChain.doFilter(request, response);
    }
  }

  // Verifica apenas para PUT, POST e DELETE
  private boolean validarVerboRequisicao(HttpServletRequest request) {

    if (request.getMethod().equalsIgnoreCase("PUT") ||
        request.getMethod().equalsIgnoreCase("POST") ||
        validarVerboRequisicaoDelete(request)) {
      return true;
    }
    return false;
  }

  // Verifica apenas para Aluno e Experiência
  private boolean validarRotasRequisicao(HttpServletRequest request) {

    String requestUri = request.getRequestURI();
    if (requestUri.startsWith("/aluno") || requestUri.startsWith("/experiencia")) {
      return true;
    }
    return false;
  }

  private String buscarRole(UserDetails userDetails) {

    String role = userDetails.getAuthorities().stream()
        .findFirst()
        .map(grantedAuthority -> grantedAuthority.getAuthority())
        .orElse("");

    return role;
  }

  private boolean validarVerboRequisicaoDelete(HttpServletRequest request) {

    if (request.getMethod().equalsIgnoreCase("DELETE")) {
      return true;
    }
    return false;
  }

  private String buscarEmailRequisicao(
      HttpServletRequest request,
      MultiReadHttpServletRequest multiReadRequest) throws IOException {

    String emailInRequest;
    if (validarVerboRequisicaoDelete(request)) {
      emailInRequest = request.getParameter("email");
    } else {
      emailInRequest = buscarEmailBody(multiReadRequest);
    }

    return emailInRequest;
  }

  private String buscarEmailBody(MultiReadHttpServletRequest multiReadRequest) throws IOException {

    String requestBody = buscarBodyRequisicao(multiReadRequest);
    Map<String, Object> mapBody = converterJsonParaMap(requestBody);
    String emailInRequest = (String) mapBody.get("email");

    return emailInRequest;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Object> converterJsonParaMap(String requestBody)
      throws JsonMappingException, JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> mapBody = mapper.readValue(requestBody, Map.class);

    return mapBody;
  }

  private String buscarBodyRequisicao(MultiReadHttpServletRequest multiReadRequest) throws IOException {
    String requestBody = multiReadRequest.getRequestBody();

    return requestBody;
  }
}
