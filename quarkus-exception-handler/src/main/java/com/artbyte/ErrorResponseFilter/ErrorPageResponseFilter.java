package com.artbyte.ErrorResponseFilter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import io.quarkus.arc.Priority;

@Provider
@Priority(9999)
public class ErrorPageResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

                int status = responseContext.getStatus();
                if(status >= 400){
                    //Modify error response...
                }

    }
    
}
