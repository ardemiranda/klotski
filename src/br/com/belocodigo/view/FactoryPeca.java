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

import android.app.Activity;

public class FactoryPeca {

	public static Peca criar(Activity context, char tipo) {
		TipoPeca tipoPeca = TipoPeca.find(tipo);
		if (tipoPeca == null) {
			return null;
		}

		Peca peca = new Peca(context, tipo);
		tipoPeca.executar(peca);
		return peca;
	}

}
