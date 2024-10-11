package org.portal.portal.exceptions;

public class ExceptionRecordsNotPersisted extends RuntimeException {

    public ExceptionRecordsNotPersisted() {
        super("Nenhum registro gravado, validar se existem dados novos na planilha!");
    }

    public ExceptionRecordsNotPersisted(String message) { super(message); }
}
