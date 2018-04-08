package com.qwant.artistes_java.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.qwant.artistes_java.rest.dto.ArtisteDto;

public interface ArtisteService {

	public List<ArtisteDto> getArtistes();

	public ArtisteDto create(final ArtisteDto artisteDto);

	public ArtisteDto getArtisteById(final long artisteId);

	public ArtisteDto updateArtiste(final ArtisteDto artisteDto);

	public void deleteArtisteById(final long artisteId);

	public void savePictureForArtiste(final long artisteId, final String realPath, final MultipartFile convertFromMultiPart) throws IOException;

}
