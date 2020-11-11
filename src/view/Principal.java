package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {

		IArquivosController arquivosCont = new ArquivosController();

		try {
			arquivosCont.verificaDirTemp();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
