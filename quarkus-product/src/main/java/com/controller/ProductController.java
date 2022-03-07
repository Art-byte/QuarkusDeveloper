package com.controller;

import com.entitys.Product;
import com.repositorys.ProductReporsitory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductReporsitory reporsitory;

    @GET
    public List<Product> list(){
        return reporsitory.listProduct();
    }

    @GET
    @Path("/{id}")
    public Product getById(@PathParam("id") Long id){
        return reporsitory.findById(id);
    }

    @POST
    public Response add(Product p) {
        reporsitory.createProduct(p);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{Id}")
    public Response delete(@PathParam("Id") Long Id) {
        reporsitory.deleteProduct( reporsitory.findById(Id));
        return Response.ok().build();
    }
    @PUT
    public Response update(Product p) {
        Product product = reporsitory.findById(p.getId());
        product.setCode(p.getCode());
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        reporsitory.update(product);
        return Response.ok().build();
    }
}
