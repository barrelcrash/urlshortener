package com.barrelcrash.urlshortener.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barrelcrash.urlshortener.dto.UrlDTO;
import com.barrelcrash.urlshortener.service.UrlShorteningService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/short")
public class UrlShortenerController {
	
	@Autowired
	private UrlShorteningService urlShorteningService;
	
	@ApiOperation(value = "Shorten a URL")
	@RequestMapping(value = "/shortenUrl", method = RequestMethod.PUT, produces="application/json")
	public UrlDTO shortenUrl(HttpServletResponse response, @RequestBody UrlDTO urlDTO) {
		return urlShorteningService.shortenUrl(urlDTO);
	}

	@ApiOperation(value = "Get the originUrl for a given shortUrl")
	@RequestMapping(value = "/get/{shortUrl}", method = RequestMethod.GET)
	public UrlDTO getOriginUrl(HttpServletRequest request, HttpServletResponse response, @RequestParam String shortUrl) {
		return urlShorteningService.getOriginUrl(shortUrl);
	}

	// TODO: make me a redirect endpoint
	@ApiOperation(value = "Get redirect from a shortened URL")
	@RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
	public UrlDTO redirectToOriginUrl(HttpServletRequest request, HttpServletResponse response, @RequestBody UrlDTO urlDTO) {
		return urlShorteningService.shortenUrl(urlDTO);
	}
}
