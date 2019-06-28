package br.ce.wcaquino.matchers;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.ce.wcaquino.utils.DataUtils;

/**
 * @author B1400143
 *
 */
public class DateMatcher extends TypeSafeMatcher<Date> {

	private Date data;
	
	public DateMatcher(Date data) {
		this.data = data;
	}
	
	//Aqui eu coloco o texto que vai aparecer depois do "Expected: " caso de falha no teste
	public void describeTo(Description description) {
		description.appendText(data.toString());
	}
	
	//Aqui eu faço a comparação se é verdadeira ou falsa
	@Override
	protected boolean matchesSafely(Date data1) {
		return DataUtils.isMesmaData(data, data1);
	}

}
