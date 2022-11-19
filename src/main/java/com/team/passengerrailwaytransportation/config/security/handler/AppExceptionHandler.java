package com.team.passengerrailwaytransportation.config.security.handler;

import com.team.passengerrailwaytransportation.entities.ErrorMessage;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.NonNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@PropertySource("classpath:message.properties")
public class AppExceptionHandler {
  @NonNull
  @Value("${internal.server.error.message}")
  private String internalServerErrorMessage;

  @NonNull
  @Value("${unauthorized.error.message}")
  private String unauthorizedErrorMessage;

  @NonNull
  @Value("${bad.request.error.message}")
  private String badRequestErrorMessage;

  @NonNull
  @Value("${access.denied.error.message}")
  private String accessDeniedErrorMessage;

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorMessage> handleAuthenticationException(
      @NonNull final HttpServletRequest request,
      @NonNull final AuthenticationException exception) {

    final var message = ErrorMessage.builder()
        .status(HttpStatus.UNAUTHORIZED.value())
        .date(new Date())
        .description(unauthorizedErrorMessage)
        .url(request.getRequestURL().toString())
        .build();
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorMessage> handleResourceNotFoundException(
      @NonNull final HttpServletRequest request,
      @NonNull final ConstraintViolationException exception) {

    final var message = ErrorMessage.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .date(new Date())
        .description(badRequestErrorMessage)
        .url(request.getRequestURL().toString())
        .build();
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorMessage> handleAccessDeniedException(
      @NonNull final HttpServletRequest request,
      @NonNull final AccessDeniedException exception) {

    final var message = ErrorMessage.builder()
        .status(HttpStatus.FORBIDDEN.value())
        .date(new Date())
        .description(accessDeniedErrorMessage)
        .url(request.getRequestURL().toString())
        .build();
    return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
  }
}
