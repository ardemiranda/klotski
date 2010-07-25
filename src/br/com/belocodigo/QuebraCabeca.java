/*
 * Copyright 2010-2010 André Ribeiro de Miranda
 * 
 * Klotski is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Klotski is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Less General Public License for more details.
 * 
 * You should have received a copy of the GNU Less General Public License
 * along with Klotski.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
		linhas.add(linha.toLowerCase().toCharArray());
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
