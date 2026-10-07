package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Pessoa extends Model {

    public String nome;
    public String email;
    public String login;
    public String senha;

    public static Pessoa autenticar(String login, String senha) {
        return Pessoa.find("byLoginAndSenha", login, senha).first();
    }
}
