package com.bloodorganmanagementsystem.app.service.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.bloodorganmanagementsystem.app.dto.ErrorMessage;

@Provider
public class AppExceptionMapper implements ExceptionMapper<Throwable> {
    /**
     * Builds and exception to send as rest
     */
    @Override
    public Response toResponse(Throwable exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        // TODO Auto-generated method stub
        return Response.status(Status.NOT_ACCEPTABLE).entity(errorMessage).build();
    }

}