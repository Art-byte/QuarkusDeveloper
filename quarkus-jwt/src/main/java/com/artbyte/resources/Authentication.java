package com.artbyte.resources;


import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import com.artbyte.model.AuthRequest;
import com.artbyte.model.AuthResponse;
import com.artbyte.model.User;
import com.artbyte.utils.PBKDF2Encoder;
import com.artbyte.utils.TokenUtils;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/auth")
public class Authentication {
    
    @Inject
    PBKDF2Encoder passwordEncoder;

    @ConfigProperty(name = "com.artbyte.jwt.duration")
    public Long duration;

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    public String issuer;

    @POST
    @PermitAll
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AuthRequest authRequest){
        User u = User.findByUsername(authRequest.username);
        if(u != null && u.password.equals(passwordEncoder.encode(authRequest.password))){
            try{
                return Response.ok(new AuthResponse(TokenUtils.generateToken(u.username, u.roles, duration, issuer))).build();
            }catch(Exception e){
                return Response.status(Status.UNAUTHORIZED).build();
            }
        }else{
            return Response.status(Status.UNAUTHORIZED).build();
        }
    }
}
