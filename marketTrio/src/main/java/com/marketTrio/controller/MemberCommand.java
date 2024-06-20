package com.marketTrio.controller;

import java.io.Serializable;

import com.marketTrio.domain.Member;

@SuppressWarnings("serial")
public class MemberCommand implements Serializable {
	private Member member;

	private boolean newMember;

	private String repeatedPassword;

	public MemberCommand(Member member) {
		this.member = member;
		this.newMember = false;
	}

	public MemberCommand() {
		this.member = new Member();
		this.newMember = true;
	}

	public Member getMember() {
		return member;
	}

	public boolean isNewMember() {
		return newMember;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}
}
