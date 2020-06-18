package com.msilva.entities;

import java.util.Date;
import java.util.List;

public class Location {

	private User user;
	private List<Movie> movies;
	private Date dataLocacao;
	private Date dataRetorno;
	private Double valor;
	
	public User getUsuario() {
		return user;
	}
	public void setUsuario(User user) {
		this.user = user;
	}
	public Date getDataLocacao() {
		return dataLocacao;
	}
	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public List<Movie> getFilmes() {
		return movies;
	}
	public void setFilmes(List<Movie> movies) {
		this.movies = movies;
	}
	
}