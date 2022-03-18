package com.artbyte.GenerateErrorPages;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.artbyte.CraftingMediaType.DetermineContentMediaType;
import com.artbyte.CraftingMediaType.MediaTypeDependedToContent;
import com.artbyte.GlobalException.ErrorPageResponsEexceptionMapper;

public class ErrorPagesGeneratos implements ExceptionMapper<Exception> {

    @Inject
    Provider<ContainerRequestContext> provider;

    @Inject
    ErrorPageResponsEexceptionMapper ErrorPageResponsEexceptionMapper;

    @Inject
    DetermineContentMediaType dtcp;

    @Inject
    MediaTypeDependedToContent media;

    @Override
    public Response toResponse(Exception exception) {
        Response errorResponse = ErrorPageResponsEexceptionMapper.mapExceptionResponse(exception);
        MediaType errorMediaType = dtcp.determineErrorContentMediaType(provider.get());

        String errorContent = media.createErrorContent(errorMediaType, errorResponse.getStatus(),
                errorResponse.getEntity().toString());

        return Response.fromResponse(errorResponse)
                .type(errorMediaType)
                .entity(errorContent)
                .build();
    }

}
