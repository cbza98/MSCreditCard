package com.TemplateNTT.domain.Entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.TemplateNTT.application.Validation.Interfaces.BusinessPartner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

	@Id
	private String accountId;
	@NotNull
	private String accountName;
	@NotNull
	@BusinessPartner
	private String code_BusinessPartner;
	@NotNull
	private String accountNumber;
	@NotNull
	private String accountType;
	@NotNull
	private Date date_Opened;
	@NotNull
	private Boolean valid;

}
