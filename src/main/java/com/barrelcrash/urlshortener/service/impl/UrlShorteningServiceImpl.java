package com.barrelcrash.urlshortener.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barrelcrash.urlshortener.dao.UrlDAO;
import com.barrelcrash.urlshortener.dto.UrlDTO;
import com.barrelcrash.urlshortener.jpa.Url;
import com.barrelcrash.urlshortener.service.UrlShorteningService;
import com.barrelcrash.urlshortener.util.UrlConversionUtil;

@Service
public class UrlShorteningServiceImpl implements UrlShorteningService {
	
	@Autowired
	UrlDAO urlDAO;

	/**
	 * Shorten a URL, persist it, and return the result.
	 */
	@Override
	public UrlDTO shortenUrl(UrlDTO urlDTO) {
		
		// if this URL has already been shortened - return that
		Optional<Url> urlEntity = urlDAO.findByOriginUrl(urlDTO.getOriginUrl());
		if (urlEntity.isPresent()) {
			return new UrlDTO(urlEntity.get());
		}
		
		// create a new URL row and save it
		// shortURL created on persistence
		Url newUrl = new Url();
		newUrl.setOriginUrl(urlDTO.getOriginUrl());
		Url savedUrl = urlDAO.save(newUrl);
		
		// set the shortUrl to the DTO and send it back
		urlDTO.setShortUrl(savedUrl.getShortUrl());
		return urlDTO;
	}

	/**
	 * Get the origin and short URL for a given shortUrl.
	 */
	@Override
	public UrlDTO getUrl(String shortUrl) {
		
		Long urlId = UrlConversionUtil.convertToUrlId(shortUrl);
		
		Optional<Url> found = urlDAO.findById(urlId);
		
		if (found.isPresent()) {
			return new UrlDTO(found.get());
		}
		
		return null;
	}

	/**
	 * Get the originUrl for a given shortUrl.
	 */
	@Override
	public String getOriginUrl(String shortUrl) {
		
		Long urlId = UrlConversionUtil.convertToUrlId(shortUrl);
		
		Optional<Url> found = urlDAO.findById(urlId);
		
		if (found.isPresent()) {
			return found.get().getOriginUrl();
		}
		
		return null;
	}
}
