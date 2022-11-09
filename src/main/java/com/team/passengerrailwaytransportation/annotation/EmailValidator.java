package com.team.passengerrailwaytransportation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {

  private static final String EMAIL_PATTERN =
      "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  @Override
  public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
    return email.matches(EMAIL_PATTERN);
  }
}
