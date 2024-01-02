package com.jasynewycz.urlmappingapi.utils;

import org.apache.commons.validator.routines.UrlValidator;

public class URLValidator {

    private static final String[] SCHEMES = {"http", "https"};
    private static final UrlValidator validator = new UrlValidator(SCHEMES);

    public static boolean validateURL(String url) {

        return validator.isValid(url);
    }
}
