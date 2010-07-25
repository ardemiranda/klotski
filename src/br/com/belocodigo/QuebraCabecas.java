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

public class QuebraCabecas {

	private static QuebraCabecas  instance;

	public static QuebraCabecas getInstance() {
		if (instance == null) {
			instance = new QuebraCabecas ();
		}
		return instance;
	}

	private QuebraCabeca quebraCabecaCarregado; 

	private QuebraCabecas () {

	}

	public void carregarQuebraCebeca(int numero) {
		switch (numero) {
		case 0:
			quebraCabecaCarregado = new QuebraCabeca("Only 18 Steps", 6, 9, 18);
			quebraCabecaCarregado
				.addLinha("######")
				.addLinha("#a**b#")
				.addLinha("#m**n#")
				.addLinha("#cdef#")
				.addLinha("#ghij#")
				.addLinha("#k  l#")
				.addLinha("##--##")
				.addLinha("    ..")
				.addLinha("    ..");
			break;
		case 1:
			quebraCabecaCarregado = new QuebraCabeca("Daisy", 6, 9, 28);
			quebraCabecaCarregado
				.addLinha("######")
				.addLinha("#a**b#")
				.addLinha("#a**b#")
				.addLinha("#cdef#")
				.addLinha("#zghi#")
				.addLinha("#j  k#")
				.addLinha("##--##")
				.addLinha("    ..")
				.addLinha("    ..");
			break;
		case 2:
			quebraCabecaCarregado = new QuebraCabeca("Violet", 6, 9, 27);
			quebraCabecaCarregado
					.addLinha("######")
					.addLinha("#a**b#")
					.addLinha("#a**b#")
					.addLinha("#cdef#")
					.addLinha("#cghi#")
					.addLinha("#j  k#")
					.addLinha("##--##")
					.addLinha("    ..")
					.addLinha("    ..");

			break;
		case 3:
			quebraCabecaCarregado = new QuebraCabeca("Violet", 17, 22, 345);
			quebraCabecaCarregado
				.addLinha("       ...       ")
				.addLinha("      .. ..      ")
				.addLinha("      .   .      ")
				.addLinha("      .. ..      ")
				.addLinha("       ...       ")
				.addLinha("######-----######")
				.addLinha("#hh0iilltmmpp;qq#")
				.addLinha("#hh,iill mmpp:qq#")
				.addLinha("#2y{45v s w89x/z#")
				.addLinha("#jj6kkaa nnoo<rr#")
				.addLinha("#jj7kkaaunnoo>rr#")
				.addLinha("#33333TTJWW11111#")
				.addLinha("#33333TTJWW11111#")
				.addLinha("#33333GG HH11111#")
				.addLinha("#33333YYIgg11111#")
				.addLinha("#33333YYIgg11111#")
				.addLinha("#ddFeeA***BffOZZ#")
				.addLinha("#ddFee** **ffOZZ#")
				.addLinha("#MMKQQ*   *PPS^^#")
				.addLinha("#VVLXX** **bbRcc#")
				.addLinha("#VVLXXD***EbbRcc#")
				.addLinha("#################");
			break;
			default:
				quebraCabecaCarregado = null;
		}
	}

	public QuebraCabeca getQuebraCabeca() {
		return quebraCabecaCarregado;
	}
}
