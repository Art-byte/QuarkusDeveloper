package com.artbyte.security;

import io.quarkus.arc.profile.IfBuildProfile;

import java.util.Collections;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.artbyte.dto.AuthorizationDTO;

@RequestScoped
@Path("/api/auth")
@IfBuildProfile("auth")
public class AuthorizationResource {
    
    @POST
    @PermitAll
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authorize(@Valid AuthorizationDTO authorizationDTO) throws Exception{
        String token = TokenUtils.getInstance().generateTokenString(authorizationDTO);
        return Response.ok().entity(Collections.singletonMap("token", token)).build();
    }
}
