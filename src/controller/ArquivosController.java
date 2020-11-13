package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController {

	@Override
	public void verificaDirTemp() throws IOException {
		File caminho = new File("C:\\TEMP");
		if (caminho.exists() && caminho.isDirectory()) {
			JOptionPane.showMessageDialog(null, "O diretório já existe!.");
		} else {
			JOptionPane.showMessageDialog(null, "Diretório Criado com sucesso.");
			caminho.mkdir();
		}

	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {

		File arq = new File(arquivo);
		String[] conteudoArquivo;

		if (verificaCadastro(arquivo, codigo)) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			String linha = buffer.readLine();

			
			while (linha != null) {

				if (linha.contains(Integer.toString(codigo))) {
					conteudoArquivo = linha.split(";");
					for (int i = 0; i < conteudoArquivo.length; i++) {
						if (i == 0) {
							System.out.println("Código: " + conteudoArquivo[i]);
						} else if (i == 1) {
							System.out.println("Nome: " + conteudoArquivo[i]);
						} else {
							System.out.println("E-mail: " + conteudoArquivo[i]);
						}
					}
				}

				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();

		}

	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		// TODO Auto-generated method stub

	}

	private boolean verificaCadastro(String arquivo, int codigo) throws IOException {

		File arq = new File(arquivo);
		String linha;
		String codigoArquivo = Integer.toString(codigo);

		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			linha = buffer.readLine();

			while (linha != null) {

				if (linha.contains(codigoArquivo)) {
					return true;
				}

				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();
		}

		return false;

	}

}
