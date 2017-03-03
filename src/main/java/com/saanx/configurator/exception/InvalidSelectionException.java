package com.saanx.configurator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ${sandjelkovic}
 * @date 3.3.17.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSelectionException extends Throwable {
}
