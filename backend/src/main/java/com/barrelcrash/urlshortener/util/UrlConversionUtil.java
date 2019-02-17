package com.barrelcrash.urlshortener.util;

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
		
		String[] digitsArray = BASE_DIGITS.split(BLANK);
		
		int remainder = Math.toIntExact(id % BASE);
		int dividend = Math.toIntExact(id / BASE);
		
		String out = BLANK;
		
		while (remainder > 0) {
			
			// remainder determines the index digit
			String nextDigit = digitsArray[remainder];
			out = nextDigit + out;
			
			// recalculate
			remainder = Math.toIntExact(dividend % BASE);
			dividend = Math.toIntExact(dividend / BASE);
		}
		
		// prepend with `0` until it is at least six digits
		while (out.length() < 6) {
			out = digitsArray[0] + out;
		}
		
		return out;
	}
	
	/**
	 * Convert a short URL to a URL ID.
	 * @param shortUrl
	 * @return
	 */
	public static long convertToUrlId(String shortUrl) {
		String[] split = shortUrl.split("");
		long result = 0;
		for (int i = 0; i < split.length; i++) {
			int pos = BASE_DIGITS.indexOf(split[i]);
			int exp = split.length - 1 - i;
			result += pos * Math.pow(BASE, exp);
		}
		return result;
	}
}
