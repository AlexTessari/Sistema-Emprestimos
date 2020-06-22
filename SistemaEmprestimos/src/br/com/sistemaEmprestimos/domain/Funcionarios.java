package br.com.testecoperalfa.domain;

import java.util.Date;

public class Funcionarios {
	private Long idfunc;
	private String nome;
	private String cpf;
	private String setor;
	private String cargo;
	private Date dataadm;
	
	
	
	public Long getIdfunc() {
		return idfunc;
	}
	public void setIdfunc(Long idfunc) {
		this.idfunc = idfunc;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Date getDataadm() {
		return dataadm;
	}
	public void setDataadm(Date dataadm) {
		this.dataadm = dataadm;
	}
	@Override
	public String toString() {
		String saida = idfunc + " - " + nome;
		return saida;
	}

}
