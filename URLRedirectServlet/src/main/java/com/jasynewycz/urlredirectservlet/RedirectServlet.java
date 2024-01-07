package com.jasynewycz.urlredirectservlet;

import java.io.*;

import com.jasynewycz.urlmapping.service.URLMapping;
import com.jasynewycz.urlmapping.service.URLMappingService;
import jakarta.inject.Inject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

/**
 * We perform a redirect (302) here rather than a forward so the end user gets the "real" long URL
 * in their browser. This will help limit the hits on our service in theory if a user
 * favorites this link after opening etc...
 */
@WebServlet(name = "RedirectServlet", value = "/*", initParams = {
        @WebInitParam(name="resolve-against-context-root", value="true")
})
public class RedirectServlet extends HttpServlet {

    private static final String RESPONSE_CONTENT_TYPE = "text/html";
    private static final String ERROR_NO_MAPPING_FOUND = "No mapping exists for provided URL: ";

    @Inject
    URLMappingService urlMappingService;

    @Override
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(RESPONSE_CONTENT_TYPE);

        String urlHash = extractUrlHash(request);
        URLMapping mapping = urlMappingService.find(urlHash);

        if(mapping == null) {

            response.sendError(404, ERROR_NO_MAPPING_FOUND + request.getRequestURL());
            return;
        }

        // temp redirect (302) to long URL
        response.sendRedirect(mapping.getLongUrl());
    }

    private static String extractUrlHash(HttpServletRequest request) {
        int lastIndexOfSlash = request.getRequestURL().lastIndexOf("/");
        return request.getRequestURL().substring(lastIndexOfSlash+1);
    }

    @Override
    public void destroy() {
    }
}