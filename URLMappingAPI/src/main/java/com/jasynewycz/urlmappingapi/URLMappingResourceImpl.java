package com.jasynewycz.urlmappingapi;

import com.jasynewycz.urlmapping.service.URLMapping;
import com.jasynewycz.urlmapping.service.URLMappingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@Path("/mapping")
public class URLMappingResourceImpl implements URLMappingResource {

    @Inject
    URLMappingService urlMappingService;

    @Inject
    UriInfo uriInfo;


    public Response getMapping(String shortUrl) {

        URLMapping mapping = urlMappingService.find(shortUrl);

        if(mapping == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(mapping).build();
    }

    public Response createMapping(String longUrl) {

        URLMapping urlMapping = new URLMapping();
        urlMapping.setLongUrl(longUrl);

        // validate input!
        urlMapping = urlMappingService.create(urlMapping);
        URI createdURI = getURIFromHash(urlMapping.getUrlHash());
        return Response.created(createdURI).entity(urlMapping).build();
    }

    private URI getURIFromHash(String hash) {
        return uriInfo.getAbsolutePathBuilder().path(hash).build();
    }

}
