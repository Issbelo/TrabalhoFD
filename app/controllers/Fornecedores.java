package controllers;

import java.util.List;

import models.Fornecedor;
import models.Produto;
import models.Status;
import play.mvc.Controller;
import play.mvc.results.Result;

public class Fornecedores extends Controller{

	public static void listar(String texto) {

		if (texto==null) {
		List<Fornecedor> lista = Fornecedor.find("status != ?1", Status.INDISPONIVEL).fetch();
		render(lista);
		}
		else {
			List<Fornecedor> lista = Fornecedor.find("status != ?1 and lower(nome) like ?2", Status.INDISPONIVEL, "%" + texto.toLowerCase() + "%").fetch();
			render(lista, texto);
		}
	}

	public static void salvar(Fornecedor f) {
		f.save();
		listar(null);
	}

	public static void form() {
		Fornecedor f = new Fornecedor();
		render(f);
	}

	public static void remover(Long id) {
		Fornecedor fornecedor = Fornecedor.findById(id);
		fornecedor.status = Status.INDISPONIVEL;
		fornecedor.save();
		listar(null);
	}

	public static void editar(Long id) {
		Fornecedor f = Fornecedor.findById(id);
		renderTemplate("Fornecedores/form.html", f);
	}

	public void detalhar(Long id, String url) {
		Fornecedor f = Fornecedor.findById(id);
		List<Produto> produtos = Produto.find("fornecedor = ?1 and status != ?2", f, Status.INDISPONIVEL).fetch();
	    render(f, url, produtos);
	}
}
