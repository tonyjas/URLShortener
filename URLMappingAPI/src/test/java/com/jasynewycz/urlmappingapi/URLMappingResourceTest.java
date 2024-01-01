package com.jasynewycz.urlmappingapi;

import com.jasynewycz.urlmapping.service.URLMapping;
import com.jasynewycz.urlmapping.service.URLMappingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class URLMappingResourceTest {

    @Mock
    URLMappingService urlMappingService;

    @Mock
    UriInfo uriInfo;

    /**
     * TODO fix test dependencies
     */
    @Test
    public void testGetMapping() {
/*
        URLMappingResource resource = new URLMappingResource();
        resource.urlMappingService = urlMappingService;
        resource.uriInfo = uriInfo;
        final String url = "https://www.bbc.co.uk";

        URLMapping newMapping = new URLMapping();
        newMapping.setLongUrl(url);
        newMapping.setNumericId(1);
        newMapping.setUrlHash("000001");

        resource.getMapping(newMapping.getUrlHash());

        verify(urlMappingService.find(newMapping.getUrlHash()));
*/
    }

    /**
     * TODO need to extract and mock the creation of the createdURI
     */
    @Test
    public void testCreateMapping() {
/*
        URLMappingResource resource = new URLMappingResource();
        resource.urlMappingService = urlMappingService;
        resource.uriInfo = uriInfo;

        URLMapping newMapping = new URLMapping();
        newMapping.setLongUrl(url);
        newMapping.setNumericId(1);
        newMapping.setUrlHash("000001");

        when(urlMappingService.create(any())).thenReturn(newMapping);
        when(resource.getURIFromHash(newMapping.getUrlHash())).thenReturn(URI.create("dummy"));

        Response response = resource.createMapping(url);
*/
    }


}
