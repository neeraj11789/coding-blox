package io.neeraj.codingblogmachine.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WebException extends RuntimeException{

    private int code;

    private String message;

}
