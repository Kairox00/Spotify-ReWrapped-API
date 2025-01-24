package com.spotify.rewrapped.exceptions;

import lombok.Getter;

@Getter
public class ApiException extends Exception {
  int statusCode;

  public ApiException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

}
