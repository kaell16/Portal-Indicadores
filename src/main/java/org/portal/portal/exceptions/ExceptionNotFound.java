package org.portal.portal.exceptions;

public class ExceptionNotFound extends RuntimeException {

    public ExceptionNotFound() { super("Cadastro não encontrado"); }

    public ExceptionNotFound(String message) {super(message);}
}
