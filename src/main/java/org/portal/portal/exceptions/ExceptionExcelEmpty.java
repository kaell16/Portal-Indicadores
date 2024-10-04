package org.portal.portal.exceptions;

public class ExceptionExcelEmpty extends RuntimeException {

    public ExceptionExcelEmpty() { super("Arquivo sem registros"); }

    public ExceptionExcelEmpty(String message) {super(message);}
}
