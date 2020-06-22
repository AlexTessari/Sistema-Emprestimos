package br.com.testecoperalfa.domain;

import java.util.*;

public class Emprestimos {
	private Long idemp;
	private Long parcela;
	private Date vencimento;
	private Double valorparc;
	private Double valorpag;
	private Long idfunc;
	private Date dataoperacao;
	private String observacao;
	private Date datainicial;
	private Date datafinal;
	private Funcionarios funcionarios = new Funcionarios();
	
	
	
	public Date getDatainicial() {
		return datainicial;
	}
	public void setDatainicial(Date datainicial) {
		this.datainicial = datainicial;
	}
	public Date getDatafinal() {
		return datafinal;
	}
	public void setDatafinal(Date datafinal) {
		this.datafinal = datafinal;
	}
	public Long getIdemp() {
		return idemp;
	}
	public void setIdemp(Long idemp) {
		this.idemp = idemp;
	}
	public Long getparcela() {
		return parcela;
	}
	public void setparcela(Long parcela) {
		this.parcela = parcela;
	}
	
	public Date getvencimento() {
		return vencimento;
	}
	public void setvencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	public Double getvalorparc() {
		return valorparc;
	}
	public void setvalorparc(Double valorparc) {
		this.valorparc = valorparc;
	}
	public Double getvalorpag() {
		return valorpag;
	}
	public void setvalorpag(Double valorpag) {
		this.valorpag = valorpag;
	}
	public Long getidfunc() {
		return idfunc;
	}
	public void setidfunc(Long idfunc) {
		this.idfunc = idfunc;
	}
	
	public Date getDataoperacao() {
		return dataoperacao;
	}
	public void setDataoperacao(Date dataoperacao) {
		this.dataoperacao = dataoperacao;
	}
	
	
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	@Override
	public String toString() {
		String saida = idfunc + " - " + parcela;
		return saida;
	}
	public Funcionarios getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(Funcionarios funcionarios) {
		this.funcionarios = funcionarios;
	}

}
