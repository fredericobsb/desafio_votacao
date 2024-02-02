package com.sicred.sistema.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CpfDTO implements Serializable {
    
	private static final long serialVersionUID = 1L;
	@JsonProperty("status")
    private String status;
}
