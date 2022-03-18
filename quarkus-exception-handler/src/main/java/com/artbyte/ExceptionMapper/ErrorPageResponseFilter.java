package com.artbyte.ExceptionMapper;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.artbyte.CraftingMediaType.DetermineContentMediaType;
import com.artbyte.CraftingMediaType.MediaTypeDependedToContent;

@Provider
public class ErrorPageResponseFilter implements ContainerResponseFilter {

    @Inject
    DetermineContentMediaType contentMediaType;

    @Inject
    MediaTypeDependedToContent content;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        int status = responseContext.getStatus();

        if (status >= Response.Status.BAD_REQUEST.getStatusCode()) {
            MediaType errorMediaType = contentMediaType.determineErrorContentMediaType(requestContext);
            String errorContent = content.createErrorContent(errorMediaType, status, responseContext.getEntity().toString());

            responseContext.setEntity(errorContent, null, errorMediaType);
        }
    }

}
