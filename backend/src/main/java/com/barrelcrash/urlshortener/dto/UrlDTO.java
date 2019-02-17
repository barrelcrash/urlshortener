package com.barrelcrash.urlshortener.dto;

import java.util.Map;

import com.barrelcrash.urlshortener.jpa.Url;
import com.fasterxml.jackson.annotation.JsonCreator;

public class UrlDTO {

	private String originUrl;
	
	private String shortUrl;
	
	@JsonCreator
	public UrlDTO(Map<String,String> props) {
		this.originUrl = props.get("originUrl");
		this.shortUrl = props.get("shortUrl");
	}
	
	public UrlDTO(Url url) {
		this.originUrl = url.getOriginUrl();
		this.shortUrl = url.getShortUrl();
	}

	public String getOriginUrl() {
		return originUrl;
	}
	
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}
	
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
}
