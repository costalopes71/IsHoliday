package br.com.costalopes.isholiday;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.costalopes.isholiday.model.Holiday;

public interface IsHolidayInterface {

	boolean isHoliday();
	Optional<List<Holiday>> getHoliday();
	IsHoliday changeDate(LocalDate newDate);
	void buildHolidayList();
	List<Holiday> getNationalHolidays();
	int daysToNextHoliday();
	
}
