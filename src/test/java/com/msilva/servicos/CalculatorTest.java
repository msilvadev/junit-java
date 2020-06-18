package com.msilva.servicos;

import com.msilva.exceptions.CantDivideByZeroException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	
	private Calculator calc;
	
	@Before
	public void setup(){
		calc = new Calculator();
	}

	@Test
	public void deveSomarDoisValores(){
		//cenario
		int a = 5;
		int b = 3;
		
		//acao
		int resultado = calc.somar(a, b);
		
		//verificacao
		Assert.assertEquals(8, resultado);
		
	}
	
	@Test
	public void deveSubtrairDoisValores(){
		//cenario
		int a = 8;
		int b = 5;
		
		//acao
		int resultado = calc.subtrair(a, b);
		
		//verificacao
		Assert.assertEquals(3, resultado);
		
	}
	
	@Test
	public void deveDividirDoisValores() throws CantDivideByZeroException {
		//cenario
		int a = 6;
		int b = 3;
		
		//acao
		int resultado = calc.divide(a, b);
		
		//verificacao
		Assert.assertEquals(2, resultado);
	}
	
	@Test(expected = CantDivideByZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws CantDivideByZeroException {
		int a = 10;
		int b = 0;
		
		calc.divide(a, b);
	}
}
