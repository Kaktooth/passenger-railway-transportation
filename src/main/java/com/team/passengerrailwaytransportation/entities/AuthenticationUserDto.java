package com.team.passengerrailwaytransportation.entities;

import com.team.passengerrailwaytransportation.utility.AppConstraints;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthenticationUserDto {

  @NotNull
  @NotEmpty(message = "Email should not be empty")
  private String email;

  @Size(min = AppConstraints.Web.Security.minSizeOfPassword,
      max = AppConstraints.Web.Security.maxSizeOfPassword)
  @NotNull
  @NotEmpty(message = "Password should not be empty")
  private String password;
}
