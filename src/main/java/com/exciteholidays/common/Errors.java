package com.exciteholidays.common;

import java.util.stream.Stream;

public enum Errors {
	VALID(0L, "Valid"),
    INVALID(-1L, "Invalid"),
    HIGH(-2L, "High"),
    LOW(-3L, "Low"),
    NOT_AVAILABLE(-4L, "Not Available");
	
	private final Long code;
	private final String msg;
	
	Errors(Long code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Long getCode(){
		return code;
	}
	
	public String getMsg() {
		return msg; 
	}
	
	public static final Errors errors(Long code) {
		return Stream.of(Errors.values()).filter(c -> c.code == code).findAny().orElse(VALID);
	}
}