package com.hsbc.wfs6.team2.exceptions;

public class DoctorNotFoundException extends Exception{

    public DoctorNotFoundException()
    {

    }

    public DoctorNotFoundException(String message)
    {
        super(message);
    }
    public DoctorNotFoundException(String message, Throwable e)
    {
        super(message, e);
    }
}
