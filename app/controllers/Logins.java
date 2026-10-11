package controllers;

import models.Pessoa;
import play.mvc.Controller;

public class Logins extends Controller {

    public static void form() {
        render();
    }

    public static void logar(String login, String senha) {
		Pessoa pessoaBanco = Pessoa.autenticar(login, senha);
		if (pessoaBanco == null) {
			flash.error("Usuário ou senha inválido. Tente novamente!");
			form();
		}
		
		session.put("usuarioLogado", login);
		session.put("perfilUsuario", pessoaBanco.perfil.name());
		flash.success("Login realizado com sucesso");
		Application.index();
	}

    public static void logout() {
        session.clear();
        flash.success("Você saiu do sistema.");
        form();
    }
}
