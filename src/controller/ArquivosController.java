package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
		File arq = new File(arquivo);

		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			String linha = buffer.readLine();

			while (linha != null) {

				if (linha.contains(Integer.toString(codigo))) {
					return true;
				}

				linha = buffer.readLine();
			}
			fluxo.close();
			leitor.close();
			buffer.close();

		}
		return false;
	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {

		File arq = new File(arquivo);
		String[] conteudoArquivo;
		String mostraCadastro = "";
		int i;

		if (verificaCadastro(arquivo, codigo)) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			String linha = buffer.readLine();

			while (linha != null) {
				conteudoArquivo = linha.split(";");
				for (i = 0; i < conteudoArquivo.length; i++) {
					if (Integer.toString(codigo).equals(conteudoArquivo[i])) {
						conteudoArquivo = linha.split(";");
						for (i = 0; i < conteudoArquivo.length; i++) {
							if (i == 0) {
								mostraCadastro += "Código: " + conteudoArquivo[i] + "\n";
							} else if (i == 1) {
								mostraCadastro += "Nome: " + conteudoArquivo[i] + "\n";
							} else {
								mostraCadastro += "E-mail: " + conteudoArquivo[i] + "\n";
							}
						}
						JOptionPane.showMessageDialog(null, "---Dados do Cadastro--- \n" + mostraCadastro);
						break;
					}
				}

				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();

		} else {
			JOptionPane.showMessageDialog(null, "Cadastro não encontrado");
		}

	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		File arq = new File(arquivo);
		boolean existe = false;

		if (!verificaCadastro(arquivo, codigo)) {
			existe = true;
			linha = Integer.toString(codigo) + ";" + nome + ";" + email;
			buffer.append(linha);

			FileWriter escreveArquivo = new FileWriter(arquivo, existe);
			PrintWriter imprimeArquivo = new PrintWriter(escreveArquivo);
			imprimeArquivo.write(linha);

			imprimeArquivo.flush();
			imprimeArquivo.close();
			escreveArquivo.close();

			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Código já cadastrado!");
		}

	}

	private boolean verificaCadastro(String arquivo, int codigo) throws IOException {

		File arq = new File(arquivo);
		String linha;
		String conteudoArquivo[];
		int i = 0;

		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			linha = buffer.readLine();

			while (linha != null) {
				conteudoArquivo = linha.split(";");
				if (Integer.toString(codigo).equals(conteudoArquivo[i])) {
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
