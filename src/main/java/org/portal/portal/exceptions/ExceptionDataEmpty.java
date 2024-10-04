package org.portal.portal.exceptions;

public class ExceptionDataEmpty extends RuntimeException {

    public ExceptionDataEmpty() { super("Datas não informadas"); }

    public ExceptionDataEmpty(String message) {super(message);}
}
