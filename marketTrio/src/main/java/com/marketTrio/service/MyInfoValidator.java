package com.marketTrio.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.marketTrio.controller.MemberCommand;
import com.marketTrio.domain.Member;

@Component
public class MyInfoValidator implements Validator {
	// Member 객체나 Member 객체를 상속하는 다른 객체에 대한 유효성 검사를 수행할 수 있음.
	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		MemberCommand memberCommand = (MemberCommand) obj;
		Member member = memberCommand.getMember();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.nickname", "NICKNAME_REQUIRED",
				"Nickname is required.");
		if (member.getPassword() == null || member.getPassword().length() < 1
				|| !member.getPassword().equals(memberCommand.getRepeatedPassword())) {
			errors.reject("PASSWORD_MISMATCH",
					"Passwords did not match or were not provided. Matching passwords are required.");
		} else if (member.getPassword() != null && member.getPassword().length() > 0) {
			if (!member.getPassword().equals(memberCommand.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH", "Passwords did not match. Matching passwords are required.");
			}
		}
	}
}
