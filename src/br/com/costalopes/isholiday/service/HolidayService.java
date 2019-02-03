package br.com.costalopes.isholiday.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.costalopes.isholiday.model.Holiday;
import br.com.costalopes.isholiday.model.HolidayResponse;

public final class HolidayService {

	private Client client = Client.create();
	private static final String URL = "https://api.calendario.com.br/?";
	private static final String TOKEN = "am9hb2xvcGVzNzFAaG90bWFpbC5jb20maGFzaD00MzAyMjIyNQ";

	// codigo IBGE de sp: ibge=3550308
	
	public List<Holiday> getHolidaysOfTheYear(int ibgeCode, int year) throws Exception {
		
		// objeto responsavel por criar o acesso ao WS atraves da URL
		WebResource resource = null;
		
		ClientResponse response = null;
		
		// setando parametros da URL
		resource = client.resource(URL + "json=true&ano=" + year + "&ibge=" + ibgeCode + "&token=" + TOKEN);
		response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		// valida a resposta do servidor - status http 200 OK
		if (response.getStatus() != 200) {
			throw new Exception("Error trying to reach server. Error code: " + response.getStatus());
		}

		HolidayResponse[] responseEntities = response.getEntity(HolidayResponse[].class);
		
		List<Holiday> holidays = new ArrayList<>();
		for (HolidayResponse holidayResponse : responseEntities) {
			holidays.add(holidayResponse.toHoliday());
		}
		
		return holidays;
	}
	
}
