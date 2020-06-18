package com.msilva.servicos;

import com.msilva.exceptions.CantDivideByZeroException;

public class Calculator {

	public int somar(int a, int b) {
		return a + b;
	}

	public int subtrair(int a, int b) {
		return a - b;
	}

	public int divide(int a, int b) throws CantDivideByZeroException {
		if(b == 0) {
			throw new CantDivideByZeroException();
		}
		return a / b;
	}

}
