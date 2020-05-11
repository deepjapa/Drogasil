package br.com.drogasil.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.drogasil.domain.Funcionario;
import br.com.drogasil.service.DrogasilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/drogasil")
@Api(value = "Drogasil")
public class DrogasilController {
	
	@Autowired
	private DrogasilService drogasilService;
		
	//int[] arrayNumbers = {1, 2, 5, 8, 9}; 
	
	@ApiOperation(value = "Exibe Lista de Array")
	@GetMapping("/exibearray")
	@ResponseStatus(HttpStatus.OK)
	public List<String> exibirArray(@RequestBody int[] arrayNumbers) {
				
		return drogasilService.exibe(arrayNumbers);
	}
	
	@ApiOperation(value = "Calcula Novo Salário")
	@PutMapping("/calculasalario")
	public ResponseEntity<Map<String, Object>> calculaSalario(@Valid @RequestBody Funcionario funcionario) {

		if (funcionario.getReajuste() == 0) {
			Map<String, Object> resposta = new HashMap<>();
			resposta.put("O seu Salário não sofreu Reajuste:", funcionario);
			return new ResponseEntity<>(resposta, HttpStatus.OK);		
		} else {
			Funcionario func = drogasilService.calculaNovoSalario(funcionario);
			Map<String, Object> resposta = new HashMap<>();
			resposta.put("O seu Salário sofreu Reajuste:", func);
			return new ResponseEntity<>(resposta, HttpStatus.OK);		
		}									
	}
	

}
