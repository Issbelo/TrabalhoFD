package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import play.db.jpa.Model;

@Entity
public class Fornecedor extends Model{
	public String nome;
	public String cnpj;

	@Enumerated(EnumType.STRING)
	public Status status;

	public Fornecedor() {
		status = Status.DISPONIVEL;
	}

}
