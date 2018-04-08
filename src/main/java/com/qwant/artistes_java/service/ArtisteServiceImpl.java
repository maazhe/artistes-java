package com.qwant.artistes_java.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qwant.artistes_java.error.CustomException;
import com.qwant.artistes_java.persistence.dao.ArtisteRepository;
import com.qwant.artistes_java.persistence.entity.Artiste;
import com.qwant.artistes_java.rest.dto.ArtisteDto;

@Service
public class ArtisteServiceImpl implements ArtisteService {

	private static final Logger logger = LoggerFactory.getLogger(ArtisteServiceImpl.class);

	@Autowired
	private ArtisteRepository artisteRepository;

	@Override
	public List<ArtisteDto> getArtistes() {
		final Iterable<Artiste> artistes = artisteRepository.findAll();
		List<ArtisteDto> result = new ArrayList<>();
		for (final Artiste a : artistes) {
			result.add(populateArtisteDto(a));
		}
		return result;
	}

	@Override
	public ArtisteDto create(final ArtisteDto artisteDto) {
		final Artiste savedArtiste = artisteRepository.save(populateArtiste(artisteDto));
		return populateArtisteDto(savedArtiste);
	}

	@Override
	public ArtisteDto getArtisteById(final long artisteId) {
		final Optional<Artiste> optArtiste = artisteRepository.findById(artisteId);
		if (!optArtiste.isPresent()) {
			throw new CustomException(String.format("No artiste found with id = %d", artisteId));
		}
		return populateArtisteDto(optArtiste.get());
	}

	@Override
	public ArtisteDto updateArtiste(final ArtisteDto artisteDto) {
		try {
			final Optional<Artiste> optArtiste = artisteRepository.findById(artisteDto.getId());
			if (!optArtiste.isPresent()) {
				throw new CustomException(String.format("No artiste found with id = %d", artisteDto.getId()));
			}
			Artiste artisteToUpdate = optArtiste.get();
			artisteToUpdate.setFirstname(artisteDto.getFirstname());
			artisteToUpdate.setLastname(artisteDto.getLastname());
			artisteToUpdate.setBiography(artisteDto.getBiography());
			artisteToUpdate.setBirthdate(artisteDto.getBirthdate());
			final Artiste savedArtiste = artisteRepository.save(artisteToUpdate);
			return populateArtisteDto(savedArtiste);
		} catch (IllegalArgumentException e) {
			throw new CustomException("id is mandatory to update artiste");
		}
	}

	@Override
	public void deleteArtisteById(final long artisteId) {
		final String picUrl = artisteRepository.getPicUrlByArtisteId(artisteId);
		if (picUrl != null) {
			File pic = new File(picUrl);
			if (pic.exists()) {
				pic.delete();
			}
		}
		try {
			artisteRepository.deleteById(artisteId);
		} catch (EmptyResultDataAccessException e) {
			throw new CustomException(String.format("No artiste found with id = %d", artisteId));
		}
	}

	@Override
	public void savePictureForArtiste(final long artisteId, final String realPath, final MultipartFile multipartFile)
			throws IOException, IllegalStateException {
		final Optional<Artiste> optArtiste = artisteRepository.findById(artisteId);
		if (!optArtiste.isPresent()) {
			throw new CustomException(String.format("No artiste found with id = %d", artisteId));
		}
		final String picUrl = saveFileToServer(realPath, multipartFile, artisteId);
		final Artiste artisteToUpdate = optArtiste.get();
		artisteToUpdate.setPicUrl(picUrl);
		artisteRepository.save(artisteToUpdate);
	}

	private ArtisteDto populateArtisteDto(final Artiste artiste) {
		ArtisteDto result = new ArtisteDto(artiste.getId(), artiste.getFirstname(), artiste.getLastname(),
				artiste.getBiography(), artiste.getBirthdate(), artiste.getPicUrl());
		return result;
	}

	private Artiste populateArtiste(final ArtisteDto artisteDto) {
		Artiste result = new Artiste();
		result.setFirstname(artisteDto.getFirstname());
		result.setLastname(artisteDto.getLastname());
		result.setBiography(artisteDto.getBiography());
		result.setBirthdate(artisteDto.getBirthdate());
		return result;
	}

	/**
	 * Save MultiPartFile to local File. Should be send to distant server in
	 * production
	 * 
	 * @param multipartFile
	 * @throws IOException,
	 *             IllegalStateExeption
	 */
	private String saveFileToServer(final String realPath, final MultipartFile multipartFile, final long artisteId)
			throws IOException, IllegalStateException {
		File savedFile = new File(
				String.format("%sartiste-%d-%s", realPath, artisteId, multipartFile.getOriginalFilename()));
		multipartFile.transferTo(savedFile);
		return savedFile.getCanonicalPath();
	}

}
