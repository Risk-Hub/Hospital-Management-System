package com.hsbc.wfs6.team2.exceptions;

public class PatientNotFoundException extends Exception{

    public PatientNotFoundException()
    {

    }

    public PatientNotFoundException(String message)
    {
        super(message);
    }
    public PatientNotFoundException(String message, Throwable e)
    {
        super(message, e);
    }
}
