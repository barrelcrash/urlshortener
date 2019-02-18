package com.barrelcrash.urlshortener.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.barrelcrash.urlshortener.util.UrlConversionUtil;

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
	
	@Test
	public void testValideUrlFormat() {
		assertEquals("http://www.website.com", UrlConversionUtil.normalizeUrlFormat("www.website.com"));
		assertEquals("http://www.website.com", UrlConversionUtil.normalizeUrlFormat("http://www.website.com"));
		assertEquals("https://www.website.com", UrlConversionUtil.normalizeUrlFormat("https://www.website.com"));
	}
}
