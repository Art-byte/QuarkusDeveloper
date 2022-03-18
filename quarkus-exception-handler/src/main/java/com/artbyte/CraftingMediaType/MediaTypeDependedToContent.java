package com.artbyte.CraftingMediaType;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.quarkus.qute.Template;

public class MediaTypeDependedToContent {

    @Inject
    ObjectMapper objectMapper;

    @Inject
    Template template;

    // Create Media Type Dependend Content

    public String createErrorContent(MediaType errorMediaType, int errorStatus, String errorMessage) {
        // as a JSON
        if (errorMediaType.equals(MediaType.APPLICATION_JSON_TYPE)) {
            return createJsonErrorContent(errorStatus, errorMessage);
            // as HTML
        } else if (errorMediaType.equals(MediaType.TEXT_HTML_TYPE)) {
            return createHtmlErrorContent(errorStatus, errorMessage);
            // as Text
        } else if (errorMediaType.equals(MediaType.TEXT_PLAIN_TYPE)) {
            return createTextErrorContent(errorStatus, errorMessage);
        } else {
            throw new IllegalStateException("snh: Unexpected media type: " + errorMediaType);
        }
    }

    private static String createTextErrorContent(int errorStatus, String errorMessage) {
        return String.format("Error %d (%s)", errorStatus, errorMessage);
      }

    /**
     * JSON error Page
     * <dependency>
     * <groupId>io.quarkus</groupId>
     * <artifactId>quarkus-resteasy-jackson</artifactId>
     * </dependency>
     */
    private String createJsonErrorContent(int errorStatus, String errorMessage) {
        ObjectNode errorObject = objectMapper.createObjectNode();
        errorObject.put("status", errorStatus);
        errorObject.put("title", errorMessage);
        ArrayNode errorsArray = objectMapper.createArrayNode().add(errorObject);
        return errorsArray.asText();
    }

    /**
     * HTML error Page
     * <dependency>
     * <groupId>io.quarkus</groupId>
     * <artifactId>quarkus-resteasy-qute</artifactId>
     * </dependency>
     */
    private String createHtmlErrorContent(int errorStatus, String errorMessage) {
        return template.data("errorStatus", errorStatus)
                .data("errorMessage", errorMessage)
                .render();
    }

}
