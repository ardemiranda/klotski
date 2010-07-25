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
import java.util.Iterator;

public class CoordenadasPeca {

	public static final int LEFT = 1;
	public static final int TOP = 2;
	public static final int RIGHT = 3;
	public static final int BOTTOM = 4;

	private ArrayList<Coordenada> left;
	private ArrayList<Coordenada> top;
	private ArrayList<Coordenada> right;
	private ArrayList<Coordenada> bottom;

	public CoordenadasPeca() {
		left = new ArrayList<Coordenada>();
		top = new ArrayList<Coordenada>();
		right = new ArrayList<Coordenada>();
		bottom = new ArrayList<Coordenada>();
	}

	public void left(int x, int y) {
		left.add(criarCoordenada(x, y));
	}

	public int sizeLeft() {
		return left.size();
	}

	public void top(int x, int y) {
		top.add(criarCoordenada(x, y));
	}

	public int sizeTop() {
		return top.size();
	}

	public void right(int x, int y) {
		right.add(criarCoordenada(x, y));
	}

	public int sizeRight() {
		return right.size();
	}

	public void bottom(int x, int y) {
		bottom.add(criarCoordenada(x, y));
	}

	public int sizeBottom() {
		return bottom.size();
	}

	private Coordenada criarCoordenada(int x, int y) {
		return new Coordenada(x, y);
	}

	public Iterator<Coordenada> getLeft() {
		return left.iterator();
	}

	public Iterator<Coordenada> getTop() {
		return top.iterator();
	}

	public Iterator<Coordenada> getRight() {
		return right.iterator();
	}

	public Iterator<Coordenada> getBottom() {
		return bottom.iterator();
	}

	public void reposicionar(int diferencaLeft, int diferencaTop) {
		aplicarReposicionamento(left, diferencaLeft, diferencaTop);
		aplicarReposicionamento(top, diferencaLeft, diferencaTop);
		aplicarReposicionamento(right, diferencaLeft, diferencaTop);
		aplicarReposicionamento(bottom, diferencaLeft, diferencaTop);
	}
	
	private void aplicarReposicionamento(ArrayList<Coordenada> valores, int diferencaLeft, int diferencaTop) {
		for (Coordenada coordeanda : valores) {
			coordeanda.alterar(diferencaLeft, diferencaTop);
		}
	}
}
