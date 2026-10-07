package models;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Produto extends Model{
	public String nome;
	public double preco;
	public int quantidade;
	public String descricao;
	public String categoria;

	@Enumerated(EnumType.STRING)
	public Status status;

	@ManyToOne
	public Fornecedor fornecedor;

	public Produto() {
		status = Status.DISPONIVEL;
	}


}