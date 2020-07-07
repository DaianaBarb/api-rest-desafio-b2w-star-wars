package com.b2w.api.challenge.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ErrorResponse implements Serializable {

  private static final long serialVersionUID = 5926468583005150707L;

  private Integer code;
  private String description;


}