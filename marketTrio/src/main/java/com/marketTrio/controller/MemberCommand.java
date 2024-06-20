package com.marketTrio.controller;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.marketTrio.domain.Member;

@SuppressWarnings("serial")
public class MemberCommand implements Serializable {
	private Member member;
	@NotBlank(message = "Password is mandatory")
	private String repeatedPassword;

	public MemberCommand(Member member) {
		this.member = member;
	}

	public MemberCommand() {
		this.member = new Member();
	}

	public Member getMember() {
		return member;
	}
	
	public void setMember(Member member) {
        this.member = member;
    }
	
	@NotBlank(message = "Password is mandatory")
	public String getPassword() {
		return member.getPassword();
	}
	
	public void setPassword(String password) {
        member.setPassword(password);
    }
	
	public String getRepeatedPassword() {
        return repeatedPassword;
    }

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	@NotBlank(message = "Name is mandatory")
	public String getName() {
        return member.getName();
    }

    public void setName(String name) {
        member.setName(name);
    }

    @NotBlank(message = "Password is mandatory")
    public String getNickname() {
        return member.getNickname();
    }

    public void setNickname(String nickname) {
        member.setNickname(nickname);
    }

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    public String getEmail() {
        return member.getEmail();
    }

    public void setEmail(String email) {
        member.setEmail(email);
    }

    @NotBlank(message = "User ID is mandatory")
    public String getPhone() {
        return member.getPhone();
    }

    public void setPhone(String phone) {
        member.setPhone(phone);
    }

    @NotBlank(message = "User ID is mandatory")
    public String getUserId() {
        return member.getId();
    }

    public void setUserId(String userId) {
        member.setId(userId);
    }
}
