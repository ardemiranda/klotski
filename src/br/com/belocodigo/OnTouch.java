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

import br.com.belocodigo.view.Peca;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnTouch implements OnTouchListener {

	final static public int TRAVADO = 0;
	final static public int LEFT = 1;
	final static public int TOP = 2;
	final static public int RIGHT = 3;
	final static public int BOTTOM = 4;

	private int measureWidth;
	private int measureHeight;
	private int left;
	private int top;
	private int right;
	private int bottom;
	private int diferencaTopBottom;


	private MapaPecas mapaPecas;

	private MotionEvent event;

	public OnTouch() {
		mapaPecas = MapaPecas.getInstance();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub

		Peca peca = (Peca) v;
		this.event = event;
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			peca.layout(left, top, right, bottom);
			movimentar(peca);

		} else if (event.getAction() == MotionEvent.ACTION_DOWN) {
			
			measureWidth = peca.getMeasuredWidth();
			measureHeight = peca.getMeasuredHeight();
			
			left = peca.getLeft();
			top = peca.getTop();
			right = peca.getRight();
			bottom = peca.getBottom();

			diferencaTopBottom = ((int) event.getRawY() - peca.getTop())
					- (int) event.getY();
		}

		return true;
	}

	private boolean movimentar(Peca pecaMovimentar) {
		if ((int) event.getRawX() < left && mapaPecas.leftLivre(pecaMovimentar)) {
			right -= measureWidth;
			left -= measureWidth;
		}

		if (((int) event.getRawY() - diferencaTopBottom) < top && mapaPecas.topLivre(pecaMovimentar)) {
			bottom -= measureHeight;
			top -= measureHeight;
		}

		if ((int) event.getRawX() > right && mapaPecas.rightLivre(pecaMovimentar)) {
			left += measureWidth;
			right += measureWidth;
		}

		if (((int) event.getRawY() - diferencaTopBottom) > bottom && mapaPecas.bottomLivre(pecaMovimentar)) {
			top += measureHeight;
			bottom += measureHeight;
		}

		return true;
	}
}
