package com.infoedge.reactive.ReactorSample.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "jobs")
public class Job {
	@JsonIgnore
	@Id
	private String id;
	@JsonProperty("id")
	private String refId;
	@NotBlank
	private String companyId;
	private String vendorId;
	private String title;
	private String description;
	private String shortDescription;
}
