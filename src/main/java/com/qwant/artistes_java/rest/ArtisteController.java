package com.qwant.artistes_java.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qwant.artistes_java.rest.dto.ArtisteDto;
import com.qwant.artistes_java.service.ArtisteService;

@RestController
@CrossOrigin
@RequestMapping("/api/artistes")
public class ArtisteController {

	@Autowired
	private ArtisteService artisteService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<ArtisteDto> getArtistes(final HttpServletRequest request) {
		return artisteService.getArtistes();
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ArtisteDto createArtiste(final HttpServletRequest request, @RequestBody final ArtisteDto artisteDto) {
		return artisteService.create(artisteDto);
	}

	@RequestMapping(path = "/{artisteId}", method = RequestMethod.GET)
	public ArtisteDto getArtiste(final HttpServletRequest request, @PathVariable final long artisteId) {
		return artisteService.getArtisteById(artisteId);
	}

	@RequestMapping(path = "/", method = RequestMethod.PUT)
	public ArtisteDto updateArtiste(final HttpServletRequest request, @RequestBody final ArtisteDto artisteDto) {
		return artisteService.updateArtiste(artisteDto);
	}

	@RequestMapping(path = "/{artisteId}", method = RequestMethod.DELETE)
	public void deleteArtiste(final HttpServletRequest request, @PathVariable final long artisteId) {
		artisteService.deleteArtisteById(artisteId);
	}

	@RequestMapping(path = "/{artisteId}/pic", method = RequestMethod.POST)
	public void uploadPicForUser(final HttpServletRequest request, @PathVariable final long artisteId,
			@RequestParam(value = "image", required = true) final MultipartFile file) throws IOException {
		final String realPath = request.getServletContext().getRealPath("/");
		artisteService.savePictureForArtiste(artisteId, realPath, file);
	}

}
