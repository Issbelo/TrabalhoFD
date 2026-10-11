package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Fornecedor extends Model{
	
	@Required
	public String nome;
	@Required
	public String cnpj;

	@Enumerated(EnumType.STRING)
	public Status status;
	
	public Blob foto;

	public Fornecedor() {
		status = Status.DISPONIVEL;
	}
	

}
