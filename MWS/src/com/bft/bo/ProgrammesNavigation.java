package com.bft.bo;

import java.util.Map;

public class ProgrammesNavigation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6624871909089912339L;
	
	private Map<String,Object> programmesNavigation;

	public ProgrammesNavigation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProgrammesNavigation(Map<String, Object> programmesNavigation) {
		super();
		this.programmesNavigation = programmesNavigation;
	}

	public Map<String, Object> getProgrammesNavigation() {
		return programmesNavigation;
	}

	public void setProgrammesNavigation(Map<String, Object> programmesNavigation) {
		this.programmesNavigation = programmesNavigation;
	}
	
}
