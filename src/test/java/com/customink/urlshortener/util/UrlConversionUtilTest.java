package com.customink.urlshortener.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UrlConversionUtilTest {
	
	@Test
	public void testConvertsToBase52() {
		assertEquals("aaaabV", UrlConversionUtil.convertToShortUrl(99L));
		assertEquals("aaadKq", UrlConversionUtil.convertToShortUrl(10000L));
		assertEquals("aabXiv", UrlConversionUtil.convertToShortUrl(273541L));
	}

	@Test
	public void testConvertsFromBase52() {
		assertEquals(99L, UrlConversionUtil.convertToUrlId("bV"));
		assertEquals(99L, UrlConversionUtil.convertToUrlId("aaaabV"));
		assertEquals(10000L, UrlConversionUtil.convertToUrlId("dKq"));
		assertEquals(273541L, UrlConversionUtil.convertToUrlId("bXiv"));
	}
}
