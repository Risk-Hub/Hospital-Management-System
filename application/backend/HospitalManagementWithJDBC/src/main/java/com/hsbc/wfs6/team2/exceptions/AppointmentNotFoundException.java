package com.hsbc.wfs6.team2.exceptions;

import com.hsbc.wfs6.team2.model.Appointment;

public class AppointmentNotFoundException extends Exception{
    public AppointmentNotFoundException()
    {

    }

    public AppointmentNotFoundException(String message)
    {
        super(message);
    }
    public AppointmentNotFoundException(String message, Throwable e)
    {
        super(message, e);
    }
}
