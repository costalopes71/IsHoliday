package br.com.costalopes.isholiday.service;

import java.util.List;

import br.com.costalopes.isholiday.model.Holiday;

public interface HolidayServiceInterface {

	List<Holiday> getHolidaysOfTheYear() throws Exception;
	
}
