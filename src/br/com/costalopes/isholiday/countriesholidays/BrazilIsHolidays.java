package br.com.costalopes.isholiday.countriesholidays;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import br.com.costalopes.isholiday.IsHoliday;
import br.com.costalopes.isholiday.service.imp.BrazilHolidayService;

public final class BrazilIsHolidays extends IsHoliday {

	private int ibgeCode;
	
	public BrazilIsHolidays(LocalDate date, int ibgeCode) {
		super(date, new BrazilHolidayService(ibgeCode, date.getYear()));
		this.ibgeCode = ibgeCode;
		buildHolidayList();
	}

	@Override
	public void buildHolidayList() {
		
		Path brazilDir = Paths.get(WORKSPACE + "/brazil");
		if (!Files.exists(brazilDir)) {
			brazilDir.toFile().mkdirs();
		}
		
		Path ibgeBinFile = Paths.get(brazilDir.toString() + "/" + ibgeCode + "_" + date.getYear() + ".bin");
		if (Files.exists(ibgeBinFile)) {
			deserializeHolidays(ibgeBinFile);
		} else {
			try {
				holidays = service.getHolidaysOfTheYear();
				serializeHolidays(ibgeBinFile);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
	}

}
