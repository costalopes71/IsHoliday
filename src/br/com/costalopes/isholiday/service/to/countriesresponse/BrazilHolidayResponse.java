package br.com.costalopes.isholiday.service.to.countriesresponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.costalopes.isholiday.model.Holiday;
import br.com.costalopes.isholiday.model.HolidayTypeCode;
import br.com.costalopes.isholiday.service.to.HolidayResponse;

public class BrazilHolidayResponse extends HolidayResponse {

	private static final long serialVersionUID = 193891302144515L;
	
	private String date;
	private String name;
	private String link;
	private String type;
	private String description;
	private String raw_description;
	private String type_code;
	private transient static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public BrazilHolidayResponse() {

	}
	
	public BrazilHolidayResponse(String date, String name, String link, String type, String description, String raw_description,
			String type_code) {
		super();
		this.date = date;
		this.name = name;
		this.link = link;
		this.type = type;
		this.description = description;
		this.raw_description = raw_description;
		this.type_code = type_code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public String getRaw_description() {
		return raw_description;
	}

	public void setRaw_description(String raw_description) {
		this.raw_description = raw_description;
	}

	@Override
	public String toString() {
		return "Holiday [date=" + date + ", name=" + name + ", link=" + link + ", type=" + type + ", description="
				+ description + ", raw_description=" + raw_description + ", type_code=" + type_code + "]";
	}
	
	@Override
	public Holiday toHoliday() {
		
		Holiday holiday = new Holiday();
		holiday.setName(name);
		holiday.setDescription(description);
		holiday.setLink(link);
		holiday.setRaw_description(raw_description);
		holiday.setType(type);
		
		switch (Integer.valueOf(type_code)) {
		case 1:
			holiday.setTypeCode(HolidayTypeCode.NATIONAL_HOLIDAY);
			break;
		case 2:
			holiday.setTypeCode(HolidayTypeCode.STATE_HOLIDAY);
			break;
		case 3:
			holiday.setTypeCode(HolidayTypeCode.MUNICIPAL_HOLIDAY);
			break;
		case 4:
			holiday.setTypeCode(HolidayTypeCode.FACULTATIVE);
			break;
		default:
			holiday.setTypeCode(HolidayTypeCode.OTHERS);
			break;
		}
		
		holiday.setDate(LocalDate.parse(date, DTF));
		
		return holiday;
	}
	
}
