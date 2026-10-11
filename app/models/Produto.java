package models;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Produto extends Model{
	
	@Required(message = "O nome é obrigatório!")
	public String nome;
	public double preco;
	public int quantidade;
	public String descricao;
	public String categoria;

	@Enumerated(EnumType.STRING)
	public Status status;

	@ManyToOne
	public Fornecedor fornecedor;
	
	public Blob foto;
	
	public Produto() {
		status = Status.DISPONIVEL;
	}


}