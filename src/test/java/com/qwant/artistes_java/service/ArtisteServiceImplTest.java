package com.qwant.artistes_java.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qwant.artistes_java.persistence.dao.ArtisteRepository;
import com.qwant.artistes_java.rest.dto.ArtisteDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArtisteServiceImplTest {

	@Autowired
	ArtisteService artisteService;

	@Autowired
	ArtisteRepository artisteRepository;

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
		cleanAllData();
	}

	@Test
	public void testCreate() {
		ArtisteDto aDto = new ArtisteDto();
		aDto.setFirstname("Matthieu");
		aDto.setLastname("Bossennec");
		aDto.setBirthdate(LocalDate.of(1988, 10, 29));
		aDto.setBiography("Something about me");
		final ArtisteDto savedArtisteDto = artisteService.create(aDto);

		Assert.assertTrue(savedArtisteDto.getId() > 0);
		Assert.assertEquals("Matthieu", savedArtisteDto.getFirstname());

		aDto.setFirstname("Mr");
		aDto.setLastname("Test");
		aDto.setBirthdate(LocalDate.of(1980, 11, 28));
		aDto.setBiography("Something about this guy");
		artisteService.create(aDto);

		final List<ArtisteDto> artistes = artisteService.getArtistes();

		Assert.assertEquals(2, artistes.size());
	}

	public void cleanAllData() {
		artisteRepository.deleteAll();
	}

}
