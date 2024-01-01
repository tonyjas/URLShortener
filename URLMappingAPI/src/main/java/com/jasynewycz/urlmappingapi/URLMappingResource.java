package com.jasynewycz.urlmappingapi;

import com.jasynewycz.urlmapping.service.URLMapping;
import com.jasynewycz.urlmapping.service.URLMappingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/mapping")
@Produces(APPLICATION_JSON)
@Consumes({TEXT_PLAIN, APPLICATION_JSON})
public class URLMappingResource {

    @Inject
    URLMappingService urlMappingService;

    @Inject
    UriInfo uriInfo;

    @GET
    @Path("/{id}")
    public Response getMapping(@PathParam("id") String shortUrl) {

        URLMapping mapping = urlMappingService.find(shortUrl);

        if(mapping == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(mapping).build();
    }

    @POST
    public Response createMapping(String longUrl) {

        URLMapping urlMapping = new URLMapping();
        urlMapping.setLongUrl(longUrl);

        // validate input!
        urlMapping = urlMappingService.create(urlMapping);
        URI createdURI = getURIFromHash(urlMapping.getUrlHash());
        return Response.created(createdURI).entity(urlMapping).build();
    }

    protected URI getURIFromHash(String hash) {
        return uriInfo.getAbsolutePathBuilder().path(hash).build();
    }
}