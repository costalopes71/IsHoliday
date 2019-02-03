package br.com.costalopes.isholiday;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.costalopes.isholiday.model.Holiday;
import br.com.costalopes.isholiday.service.HolidayService;

public class IsHoliday {

	private LocalDate date;
	private List<Holiday> holidays = new ArrayList<>();
	private HolidayService service = new HolidayService();
	private static final String WORKSPACE = createTempDir();

	public IsHoliday(LocalDate date, int ibgeCode) {
		this.date = date;
		buildHolidayList(ibgeCode);
	}
	
	public boolean isHoliday() {
		for (Holiday holiday : holidays) {
			if (holiday.getDate().equals(date))
				return true;
		}
		return false;
	}
	
	public IsHoliday changeDate(LocalDate newDate) {
		this.date = newDate;
		return this;
	}
	
	private void buildHolidayList(int ibgeCode) {

		Path ibgeBin = Paths.get(WORKSPACE + "/" + ibgeCode + "_" + date.getYear() + ".bin");
		if (Files.exists(ibgeBin)) {
			deserializeHolidays(ibgeBin);
		} else {
			try {
				holidays = service.getHolidaysOfTheYear(ibgeCode, date.getYear());
				serializeHolidays(ibgeBin);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	private void serializeHolidays(Path binFile) {
		
		try (OutputStream os = Files.newOutputStream(binFile, StandardOpenOption.CREATE);
			 ObjectOutputStream oos = new ObjectOutputStream(os);)
		{
			
			holidays.forEach(holiday -> {
				try {
					oos.writeObject(holiday);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void deserializeHolidays(Path binfile) {

		try (InputStream is = Files.newInputStream(binfile, StandardOpenOption.READ);
			 ObjectInputStream ois = new ObjectInputStream(is);) 
		{
			
			try {
				while (true) {
					holidays.add((Holiday) ois.readObject());
				}
			} catch (EOFException e) {

			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	private static String createTempDir() {
		
		File workspace = new File("./binfiles/ibge_bin");
		
		if (!workspace.exists())
			workspace.mkdirs();
		
		String workspaceDir = null;
		try {
			workspaceDir = workspace.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return workspaceDir;
	}
	
}