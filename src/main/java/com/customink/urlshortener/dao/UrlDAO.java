package com.customink.urlshortener.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.customink.urlshortener.jpa.Url;

public interface UrlDAO extends CrudRepository<Url, Long> {

	public Optional<Url> findByOriginUrl(String originUrl);

}
