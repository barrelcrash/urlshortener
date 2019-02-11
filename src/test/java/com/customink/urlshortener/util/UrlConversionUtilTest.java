package com.customink.urlshortener.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UrlConversionUtilTest {
	
	@Test
	public void testConvertsToBase52() {
		assertEquals("bV", UrlConversionUtil.convertToShortUrl(99L));
		assertEquals("dKq", UrlConversionUtil.convertToShortUrl(10000L));
		assertEquals("bXiv", UrlConversionUtil.convertToShortUrl(273541L));
	}

	@Test
	public void testConvertsFromBase52() {
		assertEquals(99L, UrlConversionUtil.convertToUrlId("bV"));
		assertEquals(10000L, UrlConversionUtil.convertToUrlId("dKq"));
		assertEquals(273541L, UrlConversionUtil.convertToUrlId("bXiv"));
	}
}
