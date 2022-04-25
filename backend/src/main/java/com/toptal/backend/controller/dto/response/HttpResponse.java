package com.toptal.backend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class HttpResponse {
  private final Date timestamp;
  private final Object body;
  private final int status;

  public HttpResponse(Object message, HttpStatus status) {
    this.timestamp = new Date();
    this.body = message;
    this.status = status.value();
  }

  public HttpResponse(Object message) {
    this.timestamp = new Date();
    this.body = message;
    this.status = HttpStatus.OK.value();
  }

  public HttpResponse() {
    this.timestamp = new Date();
    this.body = "";
    this.status = HttpStatus.OK.value();
  }
}
