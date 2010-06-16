package br.com.belocodigo;

import java.util.ArrayList;

public class QuebraCabeca {

	private String nome;
	private int width;
	private int height;
	private int minimoMovimentos;
	private ArrayList<char[]> linhas = new ArrayList<char[]>();

	public QuebraCabeca(String nome, int width, int height, int minimoMovimentos) {
		this.nome = nome;
		this.width = width;
		this.height = height;
		this.minimoMovimentos = minimoMovimentos;
	}

	public QuebraCabeca addLinha(String linha) {
		linhas.add(linha.toCharArray());
		return this;
	}

	public ArrayList<char[]> getQuebraCabeca() {
		return linhas;
	}

	public char[] getLinha(int linha) {
		return linhas.get(linha);
	}

	public String getNome() {
		return nome;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getMinimoMovimentos() {
		return minimoMovimentos;
	}
}
