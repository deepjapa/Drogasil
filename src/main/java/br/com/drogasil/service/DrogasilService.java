package br.com.drogasil.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.drogasil.domain.Funcionario;

@Service
public class DrogasilService {
		
	public List<String> exibe(int arrayNumbers[] ) {
		
		List<String> listResult = new ArrayList<String>();
		
		int j = arrayNumbers[0] - 1;

		for(int i=0; i < arrayNumbers.length; i++) {

			j++;
			
			if (j == arrayNumbers[i]) {
				listResult.add("Array na posição ["+i+"] = " + arrayNumbers[i]);
			} else {
				listResult.add("Array na posição ["+i+"] = " + arrayNumbers[i] + " não é sequencial");
				j = arrayNumbers[i];
			}	
	    }
		
		return listResult;
	}
	
	public void calculaNovoSalario(Funcionario funcionario) {
		
		if (funcionario.getReajuste() > 0) {
			
			Double salarioNovo = funcionario.getSalario() + (funcionario.getSalario() * (funcionario.getReajuste() / 100));        	
        	if (salarioNovo % 2 == 0) {
        		salarioNovo = salarioNovo + 1;
        	}
        		
        	funcionario.setNovoSalario(salarioNovo);
        		
		} 
	}
	
}


