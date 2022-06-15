package com.example.share.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
public FileNotFound(String messg) {
	super(messg);
}
public FileNotFound(String messg,Throwable cause) {
	super(messg,cause);
}
}
