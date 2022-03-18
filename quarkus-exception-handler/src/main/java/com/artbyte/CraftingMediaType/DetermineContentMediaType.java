package com.artbyte.CraftingMediaType;

import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.util.MediaTypeHelper;

public class DetermineContentMediaType {
    
    private static final MediaType DEFAULT_ERROR_MEDIA_TYPE = MediaType.TEXT_PLAIN_TYPE;

    private static final List<MediaType> ERROR_MEDIA_TYPES =
        List.of(MediaType.TEXT_PLAIN_TYPE, MediaType.TEXT_HTML_TYPE, MediaType.APPLICATION_JSON_PATCH_JSON_TYPE);

    public MediaType determineErrorContentMediaType(ContainerRequestContext containerRequestContext){
        List<MediaType> acceptableMediaTypes = containerRequestContext.getAcceptableMediaTypes();
        MediaType bestMatch = MediaTypeHelper.getBestMatch(ERROR_MEDIA_TYPES, acceptableMediaTypes);
        return bestMatch != null ? bestMatch : DEFAULT_ERROR_MEDIA_TYPE;
    }

}
