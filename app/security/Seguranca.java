package security;

import controllers.Logins;
import models.Perfil;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	
	@Before
	static void verificarAutenticacao() {
		if (!session.contains("usuarioLogado")) {
			Logins.form();
		}
	}
	
	@Before
	static void verificarAdministrador() {
		String perfil = session.get("perfilUsuario");
		Administrator possuiAnotacaoAdministrador = getActionAnnotation(Administrator.class);
		if (possuiAnotacaoAdministrador != null && !Perfil.ADMIN.name().equals(perfil)) {
			forbidden("Acesso restrito aos administradores do sistema");
		}
	}

}
