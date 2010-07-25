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

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class FormatoPeca implements Iterator<Area> {

	private TreeMap<Integer, TreeMap<Integer, Integer>> linhas;

	// iterator
	private int posicaoLinha = 0;
	private int posicaoColuna = 0;
	private boolean hasNext = true;
	private Area posicaoAtual;

	public FormatoPeca() {
		linhas = new TreeMap<Integer, TreeMap<Integer, Integer>>();
	}

	public void add(int coluna, int linha) {
		TreeMap<Integer, Integer> treeLinha = linhas.get(linha);
		if (linhas.get(linha) == null) {
			treeLinha = new TreeMap<Integer, Integer>();
		}
		treeLinha.put(coluna, coluna);
		linhas.put(linha, treeLinha);
	}

	public int getPrimeiraColuna() {
		return linhas.get(linhas.firstKey()).firstKey();
	}

	public int getPrimeiraLinha() {
		return linhas.firstKey();
	}

	public int getUltimaColuna() {
		TreeMap<Integer, Integer> ultimaLinha = linhas.get(linhas.lastKey());
		return ultimaLinha.get(ultimaLinha.lastKey());
	}

	public int getUltimaLinha() {
		return linhas.lastKey();
	}

	public int getTotalColunas() {
		int totalColunas = 0;
		for (TreeMap<Integer, Integer> colunas : linhas.values()) {
			if (colunas.size() > totalColunas) {
				totalColunas = colunas.size();
			}
		}
		return totalColunas;
	}

	public int getTotalLinhas() {
		return linhas.size();
	}

	public boolean temVizinhoLeft() {
		return contemPosicao(posicaoAtual.getColuna() - 1, posicaoAtual
				.getLinha());
	}

	public boolean temVizinhoTop() {
		return contemPosicao(posicaoAtual.getColuna(),
				posicaoAtual.getLinha() - 1);
	}

	public boolean temVizinhoRight() {
		return contemPosicao(posicaoAtual.getColuna() + 1, posicaoAtual
				.getLinha());
	}

	public boolean temVizinhoBottom() {
		return contemPosicao(posicaoAtual.getColuna(),
				posicaoAtual.getLinha() + 1);
	}

	private boolean contemPosicao(int coluna, int linha) {
		TreeMap<Integer, Integer> treeLinha = linhas.get(linha);
		if (treeLinha == null) {
			return false;
		}
		return treeLinha.containsKey(coluna);
	}

	public void rewinder() {
		posicaoLinha = linhas.firstKey();
		posicaoColuna = linhas.get(posicaoLinha).firstKey();
	}

	@Override
	public Area next() {
		boolean foiEncontradoProximaColuna = false;
		boolean foiEncontradoProximaLinha = false;
		posicaoAtual = new Area(posicaoColuna, posicaoLinha);

		foiEncontradoProximaColuna = proximaColuna();

		if (!foiEncontradoProximaColuna) {
			foiEncontradoProximaLinha = proximaLinha();
			posicaoColuna = -1;
			proximaColuna();
		}

		hasNext = foiEncontradoProximaColuna || foiEncontradoProximaLinha;
		return posicaoAtual;
	}

	private boolean proximaColuna() {
		TreeMap<Integer, Integer> linha = linhas.get(posicaoLinha);
		Collection<Integer> valuesColunas = linha.values();

		for (Integer valor : valuesColunas) {
			if (valor > posicaoColuna) {
				posicaoColuna = valor;
				return true;
			}
		}
		return false;
	}

	private boolean proximaLinha() {
		Set<Integer> valuesLinhas = linhas.keySet();

		for (Integer valor : valuesLinhas) {
			if (valor > posicaoLinha) {
				posicaoLinha = valor;
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasNext() {
		return hasNext;
	}

	@Override
	public void remove() {
	}
}