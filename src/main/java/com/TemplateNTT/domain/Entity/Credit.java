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
public class Credit {

	@Id
	private String creditid;
	@NotNull
	private String creditname;
	@NotNull
	@BusinessPartner
	private String code_BusinessPartner;
	@NotNull
	private String accountid;
	@NotNull
	private String credittype;
	@NotNull
	private Integer creditcardid;

	@NotNull
	private Date createdate;
	@NotNull
	private Date assignmentdate;
	@NotNull
	private Double approvedcredit;
	@NotNull
	private String currencycredit;
	@NotNull
	private Boolean valid;

}
