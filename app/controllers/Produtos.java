package controllers;

import java.util.List;

import models.Fornecedor;
import models.Produto;
import models.Status;
import play.mvc.Controller;

public class Produtos extends Controller{

	public static void listar(String texto) {

		if (texto==null) {
		List<Produto> lista = Produto.find("status != ?1", Status.INDISPONIVEL).fetch();
		render(lista);
		}
		else {
			List<Produto> lista = Produto.find("status != ?1 and (lower(nome) like ?2 or lower(categoria) like ?2 or lower(fornecedor.nome) like ?2)", Status.INDISPONIVEL, "%" + texto.toLowerCase() + "%").fetch();
			render(lista, texto);
		}
	}

	public static void salvar(Produto p) {
		p.save();
		listar(null);
	}

	public static void form() {
		Produto p = new Produto();
		List<Fornecedor> listaF = Fornecedor.find("status != ?1", Status.INDISPONIVEL).fetch();
		render(p, listaF);
	}

	public static void remover(Long id) {
		Produto produto = Produto.findById(id);
		produto.status = Status.INDISPONIVEL;
		produto.save();
		listar(null);
	}

	public static void editar(Long id) {
		Produto p = Produto.findById(id);
		List<Fornecedor> listaF = Fornecedor.find("status != ?1", Status.INDISPONIVEL).fetch();
		renderTemplate("Produtos/form.html", p, listaF);
	}
	
	public void detalhar(Long id, String url) {
		Produto p = Produto.findById(id);
		List<Produto> produtos = Produto.find("categoria = ?1 and status != ?2 and id != ?3", p.categoria, Status.INDISPONIVEL, id).fetch();
	    render(p, url, produtos);
	}
}
