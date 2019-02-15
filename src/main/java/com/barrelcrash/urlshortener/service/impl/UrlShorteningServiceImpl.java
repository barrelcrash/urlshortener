package com.barrelcrash.urlshortener.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barrelcrash.urlshortener.controller.UrlShortenerController;
import com.barrelcrash.urlshortener.dao.UrlDAO;
import com.barrelcrash.urlshortener.dto.UrlDTO;
import com.barrelcrash.urlshortener.jpa.Url;
import com.barrelcrash.urlshortener.service.UrlShorteningService;
import com.barrelcrash.urlshortener.util.UrlConversionUtil;

@Service
public class UrlShorteningServiceImpl implements UrlShorteningService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UrlShortenerController.class);

	@Autowired
	UrlDAO urlDAO;

	/**
	 * Shorten a URL, persist it, and return the result.
	 */
	@Override
	public UrlDTO shortenUrl(UrlDTO urlDTO) {
		
		LOG.info("Attempting to shorten `{}`", urlDTO.getOriginUrl());

		// if this URL has already been shortened - return that
		Optional<Url> urlEntity = urlDAO.findByOriginUrl(urlDTO.getOriginUrl());
		if (urlEntity.isPresent()) {
			LOG.info("URL `{}` has already been shortened to `{}`",
					urlDTO.getOriginUrl(), urlEntity.get().getShortUrl());
			return new UrlDTO(urlEntity.get());
		}
		
		// create a new URL row and save it
		// shortURL created on persistence
		Url newUrl = new Url();
		newUrl.setOriginUrl(urlDTO.getOriginUrl());
		Url savedUrl = urlDAO.save(newUrl);
		
		LOG.info("Shortened `{}` to short URL `{}`",
				urlDTO.getOriginUrl(), savedUrl.getShortUrl());
		
		// set the shortUrl to the DTO and send it back
		urlDTO.setShortUrl(savedUrl.getShortUrl());
		return urlDTO;
	}

	/**
	 * Get the origin and short URL for a given shortUrl.
	 */
	@Override
	public UrlDTO getUrl(String shortUrl) {

		LOG.info("Retrieving URL `{}`", shortUrl);
		
		Long urlId = UrlConversionUtil.convertToUrlId(shortUrl);
		
		Optional<Url> found = urlDAO.findById(urlId);
		
		if (found.isPresent()) {
		LOG.info("Found URL `{}`", shortUrl);
			return new UrlDTO(found.get());
		}
		
		LOG.info("No URL for `{}` found", shortUrl);
		return null;
	}

	/**
	 * Get the originUrl for a given shortUrl.
	 */
	@Override
	public String getOriginUrl(String shortUrl) {
		
		LOG.info("Retrieving origin URL `{}`", shortUrl);

		Long urlId = UrlConversionUtil.convertToUrlId(shortUrl);
		
		Optional<Url> found = urlDAO.findById(urlId);
		
		if (found.isPresent()) {
			LOG.info("Found origin URL `{}` for short URL `{}`",
					found.get().getOriginUrl(), shortUrl);
			return found.get().getOriginUrl();
		}
		
		LOG.info("No URL for `{}` found", shortUrl);
		return null;
	}
}
