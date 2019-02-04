package br.com.costalopes.isholiday.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public abstract class HolidayService implements HolidayServiceInterface {

	protected Client client = Client.create();
	protected WebResource resource;
	protected ClientResponse response;
	
}
