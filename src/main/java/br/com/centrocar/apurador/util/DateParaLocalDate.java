package br.com.centrocar.apurador.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Classe utilitária para conversão de Date em LocalDate.
 * 
 * @author Williams Gomes
 * @version 1.0.0
 */

public class DateParaLocalDate {

	/**
	 * Método estático que recebe um parâmetro do tipo {@link Date} e converte para
	 * o tipo {@link LocalDate} usando o {@link Instant#ofEpochMilli(long)} e o
	 * {@link LocalDateTime#ofInstant(Instant, ZoneId)}
	 * 
	 * @param data que será convertido.
	 * @return um {@link LocalDate}
	 * 
	 * @see LocalDate
	 * @see LocalDateTime
	 * @see java.util.Date
	 * @see Instant
	 */
	public static LocalDate paraDate(Date data) {
		Instant instant = Instant.ofEpochMilli(data.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	}
}