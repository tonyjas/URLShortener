package com.jasynewycz.urlredirectservlet;

import java.io.*;

import com.jasynewycz.urlmapping.service.URLMapping;
import com.jasynewycz.urlmapping.service.URLMappingService;
import jakarta.inject.Inject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

/**
 * We perform a redirect here rather than a forward so the end user gets the "real" long URL
 * in their browser. This will help limit the hits on our service in theory if a user
 * favorites this link after opening etc...
 */
@WebServlet(name = "RedirectServlet", value = "/*", initParams = {
        @WebInitParam(name="resolve-against-context-root", value="true")
})
public class RedirectServlet extends HttpServlet {

    @Inject
    URLMappingService urlMappingService;

    @Override
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        int lastIndexOfSlash = request.getRequestURL().lastIndexOf("/");
        String shortUrl = request.getRequestURL().substring(lastIndexOfSlash+1);

        URLMapping mapping = urlMappingService.find(shortUrl);
        if(mapping == null) {

            response.sendError(404, "No mapping exists for provided URL: " + request.getRequestURL());
            return;
        }

        // TODO issue something to state this URL has been used at this time stamp do enable analytics
        // and potentially purging of "dead" links.

        // redirect to long URL
        response.sendRedirect(mapping.getLongUrl());
    }

    @Override
    public void destroy() {
    }
}