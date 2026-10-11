package security;

import controllers.Logins;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	
	@Before
	static void verificarAutenticacao() {
		if (!session.contains("user")) {
			Logins.form();
		}
	}
	
	// Método utilitário que pode ser chamado dentro dos controladores
    public static boolean isAdmin() {
        return "admin".equals(session.get("user"));
    }

}
