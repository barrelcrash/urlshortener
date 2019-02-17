package com.barrelcrash.urlshortener.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.barrelcrash.urlshortener.dto.UrlDTO;
import com.barrelcrash.urlshortener.service.UrlShorteningService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/short")
public class UrlShortenerController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UrlShortenerController.class);
	
	@Autowired
	private UrlShorteningService urlShorteningService;

	@ApiOperation(value = "Shorten a URL")
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(value = "/shortenUrl", produces="application/json")
	public UrlDTO shortenUrl(HttpServletResponse response, @RequestBody UrlDTO urlDTO) {
		LOG.info("Shortening incoming URL: {}", urlDTO.getOriginUrl());
		return urlShorteningService.shortenUrl(urlDTO);
	}

	@ApiOperation(value = "Get the originUrl for a given shortUrl")
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/get/{shortUrl}")
	public UrlDTO getOriginUrl(HttpServletRequest request, HttpServletResponse response, @PathVariable("shortUrl") String shortUrl) {
		LOG.info("Retrieving original URL for shortURL: {}", shortUrl);
		return urlShorteningService.getUrl(shortUrl);
	}

	@ApiOperation(value = "Get redirect from a shortened URL")
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/{shortUrl}")
	public ModelAndView redirectToOriginUrl(ModelMap model, @PathVariable("shortUrl") String shortUrl) {
		String originUrl = urlShorteningService.getOriginUrl(shortUrl);
		LOG.info("Redirecting from `{}` to `{}`", shortUrl, originUrl);
        return new ModelAndView("redirect:" + originUrl, model);
    } 
}
