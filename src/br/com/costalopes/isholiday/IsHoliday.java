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
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.costalopes.isholiday.model.Holiday;
import br.com.costalopes.isholiday.service.HolidayService;

public abstract class IsHoliday implements IsHolidayInterface {

	protected LocalDate date;
	protected List<Holiday> holidays = new ArrayList<>();
	protected HolidayService service;
	protected static final String WORKSPACE = createTempDir();

	public IsHoliday(LocalDate date, HolidayService service) {
		this.date = date;
		this.service = service;
	}
	
	@Override
	public boolean isHoliday() {
		for (Holiday holiday : holidays) {
			if (holiday.getDate().equals(date))
				return true;
		}
		return false;
	}
	
	@Override
	public Optional<List<Holiday>> getHoliday() {
		List<Holiday> aux = holidays.stream().filter(holiday -> holiday.getDate().equals(date)).collect(Collectors.toList());

		if (aux.size() == 0)
			return Optional.empty();
		else
			return Optional.of(aux);
		
	}
	
	@Override
	public IsHoliday changeDate(LocalDate newDate) {
		this.date = newDate;
		return this;
	}

	@Override
	public List<Holiday> getNationalHolidays() {
		// TODO Implementar
		return null;
	}

	@Override
	public int daysToNextHoliday() {
		// TODO Implementar
		return 0;
	}
	
	protected void serializeHolidays(Path binFile) {
		
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

	protected void deserializeHolidays(Path binfile) {

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
		
		File workspace = new File("./binfiles/holidays");
		
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