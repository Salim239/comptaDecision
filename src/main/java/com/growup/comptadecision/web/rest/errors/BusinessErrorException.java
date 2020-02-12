package com.growup.comptadecision.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.util.Map;

/**
 * Simple exception with a message, that returns an Internal Server Error code.
 */
public class BusinessErrorException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public BusinessErrorException(String message, Map<String, Object> parameters) {

        super(null,
                message,
                Status.INTERNAL_SERVER_ERROR,
                null,
                null,
                null,
                parameters);
    }

    public BusinessErrorException(String message) {

        super(null,
                message,
                Status.INTERNAL_SERVER_ERROR,
                null,
                null,
                null,
                null);
    }


}
