package com.ricka.princy.fjpa.exceptions;

public class MissingAnnotationException extends FJPAException {
    public MissingAnnotationException(Class<?> annotation) {
        super("The annotation " + annotation.toString() + " is missing !!");
    }

    public MissingAnnotationException(String message) {
        super(message);
    }
}
