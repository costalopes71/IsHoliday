package br.com.costalopes.isholiday.examples;

import java.time.LocalDate;

import br.com.costalopes.isholiday.IsHoliday;
import br.com.costalopes.isholiday.countriesholidays.BrazilIsHolidays;

public class UsageExamples {

	public static void main(String[] args) {

		int spIbgeCode = 3550308; // para pesquisar os codigos ibge dos municipios https://cidades.ibge.gov.br/brasil/sp/sao-paulo/panorama
		LocalDate saoPauloBirthday = LocalDate.of(2019, 1, 25);
		IsHoliday holidayAPI = new BrazilIsHolidays(saoPauloBirthday, spIbgeCode);
		
		System.out.println(saoPauloBirthday + " is holiday in Sao Paulo -> " + holidayAPI.isHoliday());
		
		
	}
	
}
