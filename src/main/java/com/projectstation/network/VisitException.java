package com.projectstation.network;

public class VisitException extends Exception {
    public VisitException(Exception ex) {
        super(ex);
    }

    public VisitException(String ex) {
        super(ex);
    }
}
