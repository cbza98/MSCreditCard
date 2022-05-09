package com.TemplateNTT.application.Validation.Class;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.TemplateNTT.application.Validation.Interfaces.BusinessPartner;

public class BusinessPartnerValidator implements ConstraintValidator<BusinessPartner, String> {

	List<String> bp = Arrays.asList("720-89-5219", "720-89-52191", "720-89-521934", "720-89-521945");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		return bp.contains(value);

	}
}