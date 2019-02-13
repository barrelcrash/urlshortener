package com.barrelcrash.urlshortener.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	private UrlShorteningService urlShorteningService;

	// TODO: add error handling
	
	@ApiOperation(value = "Shorten a URL")
	@PutMapping(value = "/shortenUrl", produces="application/json")
	public UrlDTO shortenUrl(HttpServletResponse response, @RequestBody UrlDTO urlDTO) {
		return urlShorteningService.shortenUrl(urlDTO);
	}

	@ApiOperation(value = "Get the originUrl for a given shortUrl")
	@GetMapping(value = "/get/{shortUrl}")
	public UrlDTO getOriginUrl(HttpServletRequest request, HttpServletResponse response, @PathVariable("shortUrl") String shortUrl) {
		return urlShorteningService.getUrl(shortUrl);
	}

	@ApiOperation(value = "Get redirect from a shortened URL")
	@GetMapping(value = "/{shortUrl}")
	public ModelAndView redirectToOriginUrl(ModelMap model, @PathVariable("shortUrl") String shortUrl) {
		String originUrl = urlShorteningService.getOriginUrl(shortUrl);
        return new ModelAndView("redirect:" + originUrl, model);
    } 
}
