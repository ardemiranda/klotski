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

package br.com.belocodigo.view;

import br.com.belocodigo.OnTouch;

public enum TipoPeca {
	PAREDE('#') {
		public void executar(Peca peca) {
			peca.setColor(0xff74AC23);
		}
	},
	PORTAO('-') {
		public void executar(Peca peca) {
			peca.setColor(0xff74ff23);
		}
	},
	OBSTACULO('O') {
		public void executar(Peca peca) {
			peca.setColor(0xFFFFFF00);
			onTouch(peca);
		}
	},
	CHEGADA('.') {
		public void executar(Peca peca) {
			peca.setColor(0xfff4Af2f);
		}
	},
	PRESO('*') {
		public void executar(Peca peca) {
			peca.setColor(0xff74ACff);
			onTouch(peca);
		}
	};

	private char key;

	TipoPeca(char key) {
		this.key = key;
	}

	abstract public void executar(br.com.belocodigo.view.Peca peca);

	protected void onTouch(Peca peca) {
		peca.setOnTouchListener(new OnTouch());
	}

	public static TipoPeca find(char key) {
		for (TipoPeca tipoPeca : TipoPeca.values()) {
			if (tipoPeca.key == key) {
				return tipoPeca;
			}
		}

		TipoPeca tipoPeca = TipoPeca.OBSTACULO;
		return tipoPeca;
	}
}
