package suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/*@RunWith(Suite.class)
@SuiteClasses({
	CalculadoraTeste.class,
	CalculoValorLocacaoTeste.class,
	LocacaoServiceTest.class
})*/
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
