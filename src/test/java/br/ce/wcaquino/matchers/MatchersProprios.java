package br.ce.wcaquino.matchers;

import java.util.Calendar;
import java.util.Date;

import br.ce.wcaquino.utils.DataUtils;

public class MatchersProprios {
	
	//Verifica se alguma data caiu em um certo dia da semana
	public static DiaSemanaMatcher caiEm(Integer diaSemanaInteger ) {
		return new DiaSemanaMatcher(diaSemanaInteger);
	}
	
	//Verifica se tal data cai numa segunda
	public static DiaSemanaMatcher caiNumaSegunda() {
		return new DiaSemanaMatcher(Calendar.MONDAY);
	}
	
	//Verifica se a data é o dia de hoje
	public static DateMatcher ehHoje() {
		return new DateMatcher(new Date());
	}
	
	//Verifica se a data é o dia de amanhã
	public static DateMatcher ehAmanha() {
		return new DateMatcher( DataUtils.adicionarDias(new Date(), 1) );
	}
	
}
