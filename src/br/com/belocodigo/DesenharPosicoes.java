package br.com.belocodigo;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class DesenharPosicoes implements Iterator<DesenharPosicao> {

	int posicaoLinha = 0;
	int posicaoColuna = 0;
	boolean hasNext = true;
	DesenharPosicao posicaoAtual;

	TreeMap<Integer, TreeMap<Integer, Integer>> linhas;
	Set<Integer> keys;

	public DesenharPosicoes() {
		linhas = new TreeMap<Integer, TreeMap<Integer, Integer>>();
		keys = linhas.keySet();
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

	public boolean temVizinhoBootom() {
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
	public DesenharPosicao next() {
		boolean foiEncontradoProximaColuna = false;
		boolean foiEncontradoProximaLinha = false;
		posicaoAtual = new DesenharPosicao(posicaoColuna, posicaoLinha);

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