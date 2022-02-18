package com.infoedge.reactive.ReactorSample.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
	@JsonIgnore
	@Id
	private String id;
	@NotBlank
	@JsonProperty("id")
	private String refId;
	@NotBlank
	private String companyId;
	private String vendorId;
	private String title;
	private String longDescription;
	private String shortDescription;
}