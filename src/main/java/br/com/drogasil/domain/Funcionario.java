package br.com.drogasil.domain;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Funcionario {
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Double salario;
	
	@NotNull
	@DecimalMin("0")
	private Double reajuste;
	
	private Double novoSalario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Double getReajuste() {
		return reajuste;
	}

	public void setReajuste(Double reajuste) {
		this.reajuste = reajuste;
	}

	public Double getNovoSalario() {
		return novoSalario;
	}

	public void setNovoSalario(Double novoSalario) {
		this.novoSalario = novoSalario;
	}
			
}
