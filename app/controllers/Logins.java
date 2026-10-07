package controllers;

import models.Pessoa;
import play.mvc.Controller;

public class Logins extends Controller {

    public static void form() {
        render();
    }

    public static void logar(String login, String senha) {
        Pessoa pessoa = Pessoa.autenticar(login, senha);

        if (pessoa == null) {
            flash.error("Login ou senha inválidos!");
            form(); 
        } else {
            session.put("user", pessoa.login); 
            flash.success("Bem-vindo(a), " + pessoa.nome + "!");
            Application.index(); 
        }
    }

    public static void logout() {
        session.clear();
        flash.success("Você saiu do sistema.");
        form();
    }
}