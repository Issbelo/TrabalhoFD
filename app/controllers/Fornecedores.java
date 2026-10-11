package controllers;

import java.util.List;

import models.Fornecedor;
import models.Produto;
import models.Status;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;
import security.Seguranca;

@With(Seguranca.class)
public class Fornecedores extends Controller {

	public static void listar(String texto) {

		if (texto == null) {
			List<Fornecedor> lista = Fornecedor.find("status != ?1", Status.INDISPONIVEL).fetch();
			render(lista);
		} else {
			List<Fornecedor> lista = Fornecedor
					.find("status != ?1 and lower(nome) like ?2", Status.INDISPONIVEL, "%" + texto.toLowerCase() + "%")
					.fetch();
			render(lista, texto);
		}
	}

	public static void verFoto(Long id) {
		Fornecedor f = Fornecedor.findById(id);
		response.setContentTypeIfNotSet(f.foto.type());
		renderBinary(f.foto.get());
	}

	public static void salvar(Fornecedor f) {
		// if (validation.hasErrors()) {
		// Fornecedor erro = f;
		// renderTemplate("Fornecedores/form.html", erro);
		// }
		f.save();
		listar(null);
	}

	public static void form() {
		Fornecedor f = new Fornecedor();
		render(f);
	}

	public static void remover(Long id) {
		// Obtém o utilizador atualmente autenticado na sessão
	    String usuarioLogado = session.get("user");

	    // Área restrita ao admin
	    if (!Seguranca.isAdmin()) {
            flash.error("Acesso negado: Apenas administradores podem realizar esta operação.");
            listar(null);
            return; // O 'return' garante que o código abaixo NÃO será executado
        }

	    Fornecedor fornecedor = Fornecedor.findById(id);
	    if (fornecedor != null) {
	        fornecedor.status = Status.INDISPONIVEL;
	        fornecedor.save();
	        flash.success("Fornecedor removido com sucesso!");
	    } else {
	        flash.error("Fornecedor não encontrado!");
	    }

	    listar(null);
	}

	public static void editar(Long id) {
		Fornecedor f = Fornecedor.findById(id);
		renderTemplate("Fornecedores/form.html", f);
	}

	public void detalhar(Long id, String url) {
		Fornecedor f = Fornecedor.findById(id);
		if (f == null) {
			flash.error("Fornecedor não encontrado!");
			listar(null);
			return;
		}
		List<Produto> produtos = Produto.find("fornecedor = ?1 and status != ?2", f, Status.INDISPONIVEL).fetch();
		render(f, url, produtos);
	}
}
