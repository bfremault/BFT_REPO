package com.bft.bo;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class NoteNavigation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7929676348786976607L;

	private Map<String,Object> noteNavigation = new HashMap<String,Object>();

	public NoteNavigation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoteNavigation(Map<String,Object> noteNavigation) {
		super();
		this.noteNavigation = noteNavigation;
	}

	public Map<String,Object> getNoteNavigation() {
		return noteNavigation;
	}

	public void setNoteNavigation(Map<String,Object> noteNavigation) {
		this.noteNavigation = noteNavigation;
	}
		
}
