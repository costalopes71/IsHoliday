package br.com.costalopes.isholiday.examples;

import java.time.LocalDate;

import br.com.costalopes.isholiday.IsHoliday;

public class UsageExamples {

	public static void main(String[] args) {
		
		LocalDate data = LocalDate.of(2019, 1, 25); //aniversario de SP
		int spIbgeCode = 3550308; // para pesquisar os codigos ibge dos municipios https://cidades.ibge.gov.br/brasil/sp/sao-paulo/panorama
		
		IsHoliday isHoliday = new IsHoliday(data, spIbgeCode);
		System.out.println(data.toString() + " eh feriado em SP? -> " + isHoliday.isHoliday());
		
		int salvadorIbgeCode = 2927408; // codigo ibge de Salvador
		LocalDate feriadoMunicipal = LocalDate.of(2019, 12, 8);
		
		IsHoliday isHolidaySalvador = new IsHoliday(feriadoMunicipal, salvadorIbgeCode);
		System.out.println("");
		System.out.println(data.toString() + " eh feriado em Salvador? -> " + isHolidaySalvador.isHoliday());
		
		System.out.println(data.toString() + " eh feriado em Sao Paulo? -> " + isHoliday.changeDate(feriadoMunicipal).isHoliday());

	}
	
}
