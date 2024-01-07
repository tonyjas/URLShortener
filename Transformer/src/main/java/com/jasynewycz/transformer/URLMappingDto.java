package com.jasynewycz.transformer;

/**
 * simple wrapper for a hashed counter value and it's actual counter value
 * @param uniqueId hashed counter value
 * @param counterValue the numeric counter value
 */
public record URLMappingDto(String uniqueId, long counterValue) {


}
