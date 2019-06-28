package suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.servicos.CalculadoraTeste;
import br.ce.wcaquino.servicos.CalculoValorLocacaoTeste;
import br.ce.wcaquino.servicos.LocacaoServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	CalculadoraTeste.class,
	CalculoValorLocacaoTeste.class,
	LocacaoServiceTest.class
})
public class SuiteExecucao {
	
	//Remova se puder!
	
	@BeforeClass
	public static void before() {
		System.out.println("BeforeSuite");
	}
	
	@AfterClass
	public static void after() {
		System.out.println("AfterSuite");
	}
	
}
