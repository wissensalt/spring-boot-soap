package com.wissensalt.rnd.springbootsoap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 * Created on 3/28/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@SoapFault(faultCode = FaultCode.CLIENT)
public class EmployeeNotFoundException extends RuntimeException {
    /**
     *
     *
     */
    private static final long serialVersionUID = -6663788122533731913L;

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
