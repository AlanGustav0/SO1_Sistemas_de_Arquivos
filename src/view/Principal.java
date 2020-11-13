package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {

		IArquivosController arquivosCont = new ArquivosController();
		String nome = "arquivo.csv";
		int codigo = 235;

		try {
			arquivosCont.verificaDirTemp();
			arquivosCont.imprimeCadastro(nome,codigo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
