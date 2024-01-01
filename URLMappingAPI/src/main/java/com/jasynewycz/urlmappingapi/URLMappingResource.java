package com.jasynewycz.urlmappingapi;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/mapping")
@Produces(APPLICATION_JSON)
@Consumes({TEXT_PLAIN, APPLICATION_JSON})
public interface URLMappingResource {

    @GET
    @Path("/{id}")
    Response getMapping(@PathParam("id") String shortUrl);

    @POST
    Response createMapping(String longUrl);

}