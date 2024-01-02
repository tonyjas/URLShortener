package com.jasynewycz.urlmappingapi;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import static com.jasynewycz.urlmapping.service.URLMapping.*;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/mapping")
@Produces(APPLICATION_JSON)
@Consumes({TEXT_PLAIN, APPLICATION_JSON})
public interface URLMappingResource {

    @GET
    @Path("/{id}")
    Response getMapping(@PathParam("id") @NotNull @Size(min = MIN_HASH_LENGTH, max = MAX_HASH_LENGTH) String shortUrl);

    @POST
    Response createMapping(@NotNull @Size(min = MIN_LONG_URL_LENGTH, max = MAX_LONG_URL_LENGTH) String longUrl);

}