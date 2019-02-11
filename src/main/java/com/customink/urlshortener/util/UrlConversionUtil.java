package com.customink.urlshortener.util;

public class UrlConversionUtil {
	
	private static final int BASE = 52;
	
	private static final String BASE_DIGITS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private static final String BLANK = "";

	/**
	 * Convert a URL id to a base 52 a-Z string.
	 * @param id
	 * @return
	 */
	public static String convertToShortUrl(long id) {
		
		int remainder = Math.toIntExact(id % BASE);

		int dividend = Math.toIntExact(id / BASE);
		
		String out = BLANK;
		
		while (remainder > 0) {
			
			String nextDigit = BASE_DIGITS.substring(remainder, remainder + 1);
			
			out = nextDigit + out;
			
			remainder = Math.toIntExact(dividend % BASE);
			
			dividend = Math.toIntExact(dividend / BASE);
		}
		
		return out;
	}
	
	/**
	 * Convert a short URL to a URL ID.
	 * @param shortUrl
	 * @return
	 */
	public static long convertToUrlId(String shortUrl) {
		// TODO Auto-generated method stub
		return 0;
	}
}
