package com.covid.covid19.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "No Such State")
@SuppressWarnings("serual")
public class CoronaVirusException extends Exception {

    public CoronaVirusException() {

    }
    public CoronaVirusException(String message) {
        super(message);
    }
}
