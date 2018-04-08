package com.qwant.artistes_java.persistence.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qwant.artistes_java.persistence.entity.Artiste;

public interface ArtisteRepository extends PagingAndSortingRepository<Artiste, Serializable> {

	@Query("SELECT a.picUrl FROM artiste a WHERE a.id = :id")
	public String getPicUrlByArtisteId(final long id);
}
