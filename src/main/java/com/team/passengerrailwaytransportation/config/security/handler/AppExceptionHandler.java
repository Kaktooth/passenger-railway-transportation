package com.team.passengerrailwaytransportation.config.security.handler;

import com.team.passengerrailwaytransportation.entities.ErrorMessage;
import com.team.passengerrailwaytransportation.exeption.MailSendFailedException;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
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
  @Value("${access.denied.error.message}")
  private String accessDeniedErrorMessage;

  @NonNull
  @Value("${bad-request.mail.error.message}")
  private String badMailSenderMessage;

  @NonNull
  @Value("${bad-request.error.message}")
  private String badRequestMessage;

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

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorMessage> handleResourceNotFoundException(
      @NonNull final HttpServletRequest request,
      @NonNull final ConstraintViolationException exception) {

      final var message = ErrorMessage.builder()
          .status(HttpStatus.BAD_REQUEST.value())
          .date(new Date())
          .description(badRequestMessage)
          .url(request.getRequestURL().toString())
          .build();
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

  @ExceptionHandler(MailSendFailedException.class)
  public ResponseEntity<ErrorMessage> handleAllExceptions(
      @NonNull final MailSendFailedException exception,
      @NonNull final HttpServletRequest request) {
    log.info("Mail sender error: " + exception.getMessage());
    final var responseStatus =
        exception.getClass().getAnnotation(ResponseStatus.class);
    final var status =
        responseStatus != null ? responseStatus.value() : HttpStatus.BAD_GATEWAY;
    final var message = ErrorMessage.builder()
        .status(status.value())
        .date(new Date())
        .description(badMailSenderMessage)
        .url(request.getRequestURL().toString())
        .build();
    return new ResponseEntity<>(message, status);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> handleAllExceptions(
      @NonNull final Exception exception,
      @NonNull final HttpServletRequest request) {
    log.info("Internal error: " + exception.getMessage());
    final var responseStatus =
        exception.getClass().getAnnotation(ResponseStatus.class);
    final var status =
        responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
    final var message = ErrorMessage.builder()
        .status(status.value())
        .date(new Date())
        .description(internalServerErrorMessage)
        .url(request.getRequestURL().toString())
        .build();
    return new ResponseEntity<>(message, status);
  }
}
