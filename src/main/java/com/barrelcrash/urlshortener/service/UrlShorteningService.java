package com.barrelcrash.urlshortener.service;

import com.barrelcrash.urlshortener.dto.UrlDTO;

public interface UrlShorteningService {

	UrlDTO shortenUrl(UrlDTO urlDTO);

	UrlDTO getUrl(String shortUrl);

	String getOriginUrl(String shortUrl);


}
