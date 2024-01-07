package com.jasynewycz.urlmappingapi.utils;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * Simple URL validator based on apache commons UrlValidator. This class provides a simple wrapper to allow for
 * adding to or replacing the implementation in the future.
 *
 * Explicitly restricts to just http and https protocols but does not perform any checks such as ensuring the URL
 * actually goes somewhere or that where it goes is not malicious.
 */
public class URLValidator {

    private static final String[] VALID_SCHEMES = {"http", "https"};
    private static final UrlValidator validator = new UrlValidator(VALID_SCHEMES);

    public static boolean validateURL(String url) {

        return validator.isValid(url);
    }
}
