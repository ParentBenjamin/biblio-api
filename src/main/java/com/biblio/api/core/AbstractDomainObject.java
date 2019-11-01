package com.biblio.api.core;

import java.io.Serializable;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractDomainObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2254291888745884601L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@Version
	protected Long version;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	

}
