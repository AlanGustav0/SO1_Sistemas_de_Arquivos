package controller;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController {

	@Override
	public void verificaDirTemp() throws IOException {
		File caminho = new File("C:\\TEMP");
		if(caminho.exists() && caminho.isDirectory()){
			JOptionPane.showMessageDialog(null,"ERRO - O diretório já existe!.");
		}else {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
