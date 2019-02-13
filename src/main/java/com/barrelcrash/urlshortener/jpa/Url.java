package com.barrelcrash.urlshortener.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.barrelcrash.urlshortener.util.UrlConversionUtil;

@Entity
@Table(name="URL")
public class Url {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "ORIGIN_URL", nullable = false)
	private String originUrl;
	
	@Column(name = "SHORT_URL", nullable = false)
	private String shortUrl;
	
	
	public Long getId() {
		return this.id;
	}
	
	public String getOriginUrl() {
		return this.originUrl;
	}
	
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
	
	public String getShortUrl() {
		return this.shortUrl;
	}
	
	@PostPersist
	public void setShortUrl() {
		this.shortUrl = UrlConversionUtil.convertToShortUrl(this.id);
	}
}
