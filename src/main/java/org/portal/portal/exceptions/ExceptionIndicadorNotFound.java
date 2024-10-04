package org.portal.portal.exceptions;

public class ExceptionIndicadorNotFound extends RuntimeException {

    public ExceptionIndicadorNotFound() { super("Indicador não encontrado"); }

    public ExceptionIndicadorNotFound(String message) {super(message);}
}
