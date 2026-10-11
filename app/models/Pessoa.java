package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Pessoa extends Model {

    public String login;
    public String senha;
    
    @Enumerated(EnumType.STRING)
	public Perfil perfil;
	

    public static Pessoa autenticar(String login, String senha) {
        return Pessoa.find("byLoginAndSenha", login, senha).first();
    }
}
