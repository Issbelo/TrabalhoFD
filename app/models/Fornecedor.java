package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import play.db.jpa.Model;

@Entity
public class Fornecedor extends Model{
	public String nome;
	public String cnpj;
	public String login;
	public String senha;
	
	public String autenticar() {
        Fornecedor fornecedor = Fornecedor.find("login = ?1 and senha = ?2", this.login, this.senha).first();
        if (fornecedor != null) {
            return fornecedor.login;
        }
        return null;
    }

	@Enumerated(EnumType.STRING)
	public Status status;

	public Fornecedor() {
		status = Status.DISPONIVEL;
	}

}
