package com.artbyte.GlobalException;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.jboss.logging.Logger;

public class ErrorPageResponsEexceptionMapper implements ExceptionMapper<Exception>{

    @Inject
    Logger logger;

    @Inject
    Provider<ContainerRequestContext> containerRequestContextProvider;

    @Override
    public Response toResponse(Exception exception) {
        Response errorResponse = mapExceptionResponse(exception);
        // Modify error response...
        return errorResponse;
    }


    public Response mapExceptionResponse(Exception exception){

        // Use response from WebApplicationException as they are
        if(exception instanceof WebApplicationException){
            Response originalErrorResponse = ((WebApplicationException) exception).getResponse();
            return Response.fromResponse(originalErrorResponse)
            .entity(originalErrorResponse.getStatusInfo().getReasonPhrase()).build();

        }else if(exception instanceof IllegalArgumentException){
            //Special mappings
            return Response.status(400).entity(exception.getMessage()).build();

        }else{
            logger.fatalf(exception, "Failed to proces request to: {}",
            containerRequestContextProvider.get().getUriInfo());
            return Response.serverError().entity("Internal server error").build();
        }
    }


    
}
