package com.qwant.artistes_java.rest.dto;

import java.time.LocalDate;

public class ArtisteDto {

	private Long id;
	private String firstname;
	private String lastname;
	private String biography;
	private String picUrl;
	private LocalDate birthdate;

	public ArtisteDto() {
		
	}

	public ArtisteDto(final Long id, final String firstname, final String lastname, final String biography, final LocalDate birthdate, final String picUrl) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.biography = biography;
		this.birthdate = birthdate;
		this.picUrl = picUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(final String biography) {
		this.biography = biography;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(final LocalDate birthdate) {
		this.birthdate = birthdate;
	}

}
