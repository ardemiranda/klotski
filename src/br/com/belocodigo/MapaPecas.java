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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import br.com.belocodigo.view.Peca;

public class MapaPecas {

	private static MapaPecas instance;

	public static MapaPecas getInstance() {
		if (instance == null) {
			instance = new MapaPecas();
		}

		return instance;
	}

	private HashMap<Character, Peca> pecas = new HashMap<Character, Peca>();

	private MapaPecas() {
	}

	public void put(Peca peca) {
		System.out.println("setatipopeca: " + peca.getTipoPeca());
		pecas.put(peca.getTipoPeca(), peca);
	}

	public boolean containsKey(char tipo) {
		return pecas.containsKey(tipo);
	}

	public Peca get(char tipo) {
		return pecas.get(tipo);
	}

	public boolean leftLivre(Peca pecaMovimentar) {
		return rastrearCoordeandas(CoordenadasPeca.LEFT, pecaMovimentar);
	}

	public boolean topLivre(Peca pecaMovimentar) {
		return rastrearCoordeandas(CoordenadasPeca.TOP, pecaMovimentar);
	}

	public boolean rightLivre(Peca pecaMovimentar) {
		return rastrearCoordeandas(CoordenadasPeca.RIGHT, pecaMovimentar);
	}

	public boolean bottomLivre(Peca pecaMovimentar) {
		return rastrearCoordeandas(CoordenadasPeca.BOTTOM, pecaMovimentar);
	}

	private boolean rastrearCoordeandas(int ladoCoordeanda, Peca pecaMovimentar) {
		int coordenadasBloqueadas = 0;
		int ladoCoordeandaContrario = ladoCoordeandasContraria(ladoCoordeanda);

		Iterator<Coordenada> coordenadasMovimentar = obterCoordenadas(
				ladoCoordeanda, pecaMovimentar.getCoordenadas());
		Coordenada coordenadaMovimentar;

		Iterator<Coordenada> coordenadasArmazenada;
		Coordenada coordenadaArmazenada;

		char tipoPecaArmazenada;
		HashMap<Character, Integer> tipoPecasBloqueados = new HashMap<Character, Integer>();

		while (coordenadasMovimentar.hasNext()) {
			coordenadaMovimentar = coordenadasMovimentar.next();

			for (Peca pecaArmazenada : pecas.values()) {
				tipoPecaArmazenada = pecaArmazenada.getTipoPeca();
				if (pecaMovimentar.getTipoPeca() == tipoPecaArmazenada
						|| tipoPecaArmazenada == '-'
						|| tipoPecaArmazenada == '.') {
					continue;
				}

				coordenadasArmazenada = obterCoordenadas(
						ladoCoordeandaContrario, pecaArmazenada
								.getCoordenadas());

				while (coordenadasArmazenada.hasNext()) {
					coordenadaArmazenada = coordenadasArmazenada.next();
					if (coordenadaMovimentar.equals(coordenadaArmazenada)) {
						if (tipoPecasBloqueados
								.containsKey(tipoPecasBloqueados)) {
							tipoPecasBloqueados
									.put(
											tipoPecaArmazenada,
											tipoPecasBloqueados
													.get(tipoPecaArmazenada) + 1);
						} else {
							tipoPecasBloqueados.put(tipoPecaArmazenada, 0);
						}

						System.out.println("igual movimentar: "
								+ pecaMovimentar.getTipoPeca()
								+ " armazenada: "
								+ pecaArmazenada.getTipoPeca());
						coordenadasBloqueadas++;
					}
				}
			}
		}

		if (tipoPecasBloqueados.size() == 1
				&& tipoPecasBloqueados.containsKey('#')
				&& coordenadasBloqueadas == 2
				&& pecaMovimentar.getTipoPeca() == '*') {
			return true;
		}

		return !(coordenadasBloqueadas - tipoPecasBloqueados.size() > 0);
	}

	private int ladoCoordeandasContraria(int posicao) {
		switch (posicao) {
		case CoordenadasPeca.LEFT:
			return CoordenadasPeca.RIGHT;
		case CoordenadasPeca.TOP:
			return CoordenadasPeca.BOTTOM;
		case CoordenadasPeca.RIGHT:
			return CoordenadasPeca.LEFT;
		case CoordenadasPeca.BOTTOM:
			return CoordenadasPeca.TOP;
		default:
			return -1;
		}
	}

	private Iterator<Coordenada> obterCoordenadas(int tipo,
			CoordenadasPeca coordenadas) {
		switch (tipo) {
		case CoordenadasPeca.LEFT:
			return coordenadas.getLeft();
		case CoordenadasPeca.TOP:
			return coordenadas.getTop();
		case CoordenadasPeca.RIGHT:
			return coordenadas.getRight();
		case CoordenadasPeca.BOTTOM:
			return coordenadas.getBottom();
		default:
			return null;
		}
	}
}