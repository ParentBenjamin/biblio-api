package com.biblio.api.core;

import javax.persistence.*;

@Entity(name = "staff")
public class Staff extends Utilisateur {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1335563336780353019L;
	private String staffCode;

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

}
