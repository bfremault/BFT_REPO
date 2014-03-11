package com.bft.bo;

import java.util.ArrayList;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "spot")

public class Spot {
	
	@DatabaseField (id = true)
	private Integer id_spot;
	@DatabaseField
	private Integer id_pays;
	@DatabaseField
	private Float latitude;
	@DatabaseField
	private Float longitude;
	//@JsonDeserialize(contentAs=Map.class)
	//@ForeignCollectionField(eager = false)
	//@JsonUnwrapped
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private NoteNavigation noteNavigation;
	@DatabaseField
	private String orientation;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ProgrammesNavigation programmesNavigation;
	@DatabaseField
	private String region;
	@DatabaseField
	private String spot;
	@DatabaseField
	private String ville;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<Commentaires> commentaires;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<PhotosSpot> photosSpot;
	
	public static final String SPOT_FIELD_NAME = "spot";

	
	public Spot() {
		super();
		// TODO Auto-generated constructor stub
	}
	@JsonCreator
	public Spot(
			@JsonProperty("id_pays")
			Integer id_pays,
			@JsonProperty("id_spot")
			Integer id_spot,
			@JsonProperty("latitude")
			Float latitude,
			@JsonProperty("longitude")
			Float longitude,
			@JsonProperty("noteNavigation")
			Map<String,Object> noteNavigation,
			@JsonProperty("orientation")
			String orientation,
			@JsonProperty("programmesNavigation")
			Map<String,Object> programmesNavigation,
			@JsonProperty("region")
			String region,
			@JsonProperty("spot")
			String spot,
			@JsonProperty("ville")
			String ville,
			@JsonProperty("commentaires")
			ArrayList<Commentaires> commentaires,
			@JsonProperty("photosSpot")
			ArrayList<PhotosSpot> photosSpot){
		super();
		this.id_pays = id_pays;
		this.id_spot= id_spot;
		this.latitude = latitude;
		this.longitude = longitude;
		this.noteNavigation = new NoteNavigation(noteNavigation);
		this.orientation = orientation;
		this.programmesNavigation = new ProgrammesNavigation(programmesNavigation);
		this.region = region;
		this.spot = spot;
		this.ville = ville;
		this.commentaires = commentaires;
		this.photosSpot = photosSpot;
		
	}

	public Integer getId_pays() {
		return id_pays;
	}
	public void setId_pays(Integer id_pays) {
		this.id_pays = id_pays;
	}
	public Integer getId_spot() {
		return id_spot;
	}
	public void setId_spot(Integer id_spot) {
		this.id_spot = id_spot;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public NoteNavigation getNoteNavigation() {
		return noteNavigation;
	}
	public void setNoteNavigation(NoteNavigation noteNavigation) {
		this.noteNavigation = noteNavigation;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public ProgrammesNavigation getProgrammesNavigation() {
		return programmesNavigation;
	}
	public void setProgrammesNavigation(ProgrammesNavigation programmesNavigation) {
		this.programmesNavigation = programmesNavigation;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getSpot() {
		return spot;
	}
	public void setSpot(String spot) {
		this.spot = spot;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public ArrayList<Commentaires> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(ArrayList<Commentaires> commentaires) {
		this.commentaires = commentaires;
	}
	public ArrayList<PhotosSpot> getPhotosSpot() {
		return photosSpot;
	}
	public void setPhotosSpot(ArrayList<PhotosSpot> photosSpot) {
		this.photosSpot = photosSpot;
	}
	public String toString() {
		return spot;
	}
}
