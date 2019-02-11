package com.customink.urlshortener.service;

import com.customink.urlshortener.dto.UrlDTO;

public interface UrlShorteningService {

	UrlDTO shortenUrl(UrlDTO urlDTO);

}
