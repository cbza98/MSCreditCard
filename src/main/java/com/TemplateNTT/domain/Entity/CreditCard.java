package com.TemplateNTT.domain.Entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

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
public class CreditCard {
	@NotNull
	private Integer creditcardid;

	@NotNull
	private String cardname;
	@NotNull
	private Integer accountcode;
	@NotNull
	private String cardnumber;
	@NotNull
	private Date ddate;
	@NotNull
	private String ccv;
	@Id
	private String creditid;
	@NotNull
	private Boolean valid;

}
