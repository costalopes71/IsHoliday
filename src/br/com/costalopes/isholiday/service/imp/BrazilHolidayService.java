package br.com.costalopes.isholiday.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;

import br.com.costalopes.isholiday.model.Holiday;
import br.com.costalopes.isholiday.service.HolidayService;
import br.com.costalopes.isholiday.service.to.countriesresponse.BrazilHolidayResponse;

public final class BrazilHolidayService extends HolidayService {

	private static final String URL = "https://api.calendario.com.br/?";
	private static final String TOKEN = "am9hb2xvcGVzNzFAaG90bWFpbC5jb20maGFzaD00MzAyMjIyNQ";
	private int ibgeCode;
	private int year;

	public BrazilHolidayService(int ibgeCode, int year) {
		this.ibgeCode = ibgeCode;
		this.year = year;
	}
	
	@Override
	public List<Holiday> getHolidaysOfTheYear() throws Exception {

		// setando parametros da URL
		resource = client.resource(URL + "json=true&ano=" + year + "&ibge=" + ibgeCode + "&token=" + TOKEN);
		response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		// valida a resposta do servidor - status http 200 OK
		if (response.getStatus() != 200) {
			throw new Exception("Error trying to reach server. Error code: " + response.getStatus());
		}

		BrazilHolidayResponse[] responseEntities = response.getEntity(BrazilHolidayResponse[].class);
		
		List<Holiday> holidays = new ArrayList<>();
		for (BrazilHolidayResponse holidayResponse : responseEntities) {
			holidays.add(holidayResponse.toHoliday());
		}
		
		return holidays;
		
	}
	
}