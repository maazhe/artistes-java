package com.qwant.artistes_java.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "artiste")
public class Artiste {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "firstname", nullable = false, length = 30)
	private String firstname;

	@Column(name = "lastname", nullable = false, length = 30)
	private String lastname;

	@Column(name = "biography", nullable = true)
	private String biography;

	@Column(name = "birthdate", nullable = true)
	private LocalDate birthdate;

	@Column(name = "pic_url", nullable = true)
	private String picUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
