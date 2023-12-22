package com.jasynewycz.transformer;

import java.math.BigInteger;

/**
 * Simple implementation of base62 algorithm using a long
 * giving up to: 9,223,372,036,854,775,807
 * Note:
 *  - based on quick research 10-20% of tweets contain URL's
 *  - ~ 500,000,000 tweets per day
 *  - - therefore 100 million containing links that this service could serve up
 *  - - for 100 years worth of data we would need 5,000,000,000,000~
 *  - - based on this our long should be sufficient for this hypothetical use case for twitter
 * Counter to that is that this service could of course be used outside twitter, however there is still a huge
 * space left for expansion to cover this.
 * a design choice could be to go towards using a BigInteger to stretch this further
 */
public class Base62 {

    private static final String BASE_62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final BigInteger SIXTY_TWO = new BigInteger("62");

    /**
     *
     * @param value numeric value to encode into Base62
     * @return Base62 encoded representation of the value as a String Instance
     */
    public static String encode(long value) {

        if(value < 0 || value == Long.MAX_VALUE) {
            throw new IllegalArgumentException("long value for base62 encoding must be between 0 and " + Long.MAX_VALUE + " value provided: " + value);
        }

        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            sb.append(BASE_62_CHARS.charAt((int)(value % 62)));
            value /= 62;
        }
        // pad to minimum 6 chars
        while (sb.length() < 6) {
            sb.append(0);
        }
        return sb.reverse().toString();
    }


    public static String encode(BigInteger value) {
        StringBuilder sb = new StringBuilder();
        while (value.compareTo(BigInteger.ZERO) != 0) {
            BigInteger[] array = value.divideAndRemainder(SIXTY_TWO);
            sb.append(array[1]);
            value = array[0];
        }
        while (sb.length() < 6) {
            sb.append(0);
        }
        return sb.reverse().toString();
    }


    public static long decodeToLong(String value) {
        long decode = 0;
        for(int i = 0; i < value.length(); i++) {

            int c = BASE_62_CHARS.indexOf("" + value.charAt(i));
            if(c == -1) {
                throw new IllegalArgumentException("decodeToLong passed a non base62 value to decode of '" + value.charAt(i) + "' as part of value: " + value);
            }
            decode = decode * 62 + c;
        }
        return decode;
    }
}