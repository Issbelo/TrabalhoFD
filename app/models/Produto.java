package models;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Produto extends Model{
	
	@Required
	public String nome;
	@Required
	@Min(0.01)
	public double preco;
	@Min(0)
	public int quantidade;
	public String descricao;
	@Required
	public String categoria;

	@Enumerated(EnumType.STRING)
	public Status status;

	@ManyToOne
	@Required
	public Fornecedor fornecedor;
	
	public Blob foto;
	
	public Produto() {
		status = Status.DISPONIVEL;
	}


}