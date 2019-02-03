package br.com.costalopes.isholiday.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Holiday implements Serializable {

	private static final long serialVersionUID = -4302353747089929370L;
	
	private LocalDate date;
	private String name;
	private String link;
	private String type;
	private String raw_description;
	private String description;
	private HolidayTypeCode typeCode;

	public Holiday() {
		date = LocalDate.now();
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRaw_description() {
		return raw_description;
	}

	public void setRaw_description(String raw_description) {
		this.raw_description = raw_description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HolidayTypeCode getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(HolidayTypeCode typeCode) {
		this.typeCode = typeCode;
	}

	@Override
	public String toString() {
		return "Holiday [date=" + date + ", name=" + name + ", link=" + link + ", type=" + type + ", raw_description="
				+ raw_description + ", description=" + description + ", typeCode=" + typeCode + "]";
	}
	
}
