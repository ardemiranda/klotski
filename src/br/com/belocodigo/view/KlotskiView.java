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

import br.com.belocodigo.FormatoPeca;
import br.com.belocodigo.MapaPecas;
import br.com.belocodigo.QuebraCabeca;
import android.app.Activity;
import android.graphics.Canvas;
import android.view.ViewGroup;

public class KlotskiView extends ViewGroup {

	private QuebraCabeca quebraCabeca;
	private Activity context;
	private MapaPecas pecas;

	private int measuredWidth;
	private int measuredHeight;

	public KlotskiView(Activity context, QuebraCabeca quebraCabeca) {
		super(context);

		pecas = MapaPecas.getInstance();
		this.context = context;
		this.quebraCabeca = quebraCabeca;
		setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		tabuleiro();
	}

	private void tabuleiro() {
		char linhas[];
		Peca peca;
		int quantidadeColunas = quebraCabeca.getWidth();
		int quantidadeLinhas = quebraCabeca.getHeight();

		for (int i = 0; i < quantidadeLinhas; i++) {
			linhas = quebraCabeca.getLinha(i);
			for (int j = 0; j < quantidadeColunas; j++) {
				if (linhas[j] != ' ') {
					peca = getBloco(linhas[j]);
					peca.desenharPosicao(j, i);
				}
			}
		}
	}

	private Peca getBloco(char tipo) {
		if (pecas.containsKey(tipo)) {
			return pecas.get(tipo);
		}

		Peca peca = FactoryPeca.criar(context, tipo);
		pecas.put(peca);
		addView(peca);
		return peca;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		widthMeasureSpec = MeasureSpec.getSize(widthMeasureSpec);
		heightMeasureSpec = MeasureSpec.getSize(heightMeasureSpec);

		measuredWidth = widthMeasureSpec / quebraCabeca.getWidth();
		measuredHeight = heightMeasureSpec / quebraCabeca.getHeight();

		int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth,
				MeasureSpec.EXACTLY);
		int newHeightMeasureSpce = MeasureSpec.makeMeasureSpec(measuredHeight,
				MeasureSpec.EXACTLY);

		measureChildren(newWidthMeasureSpec, newHeightMeasureSpce);
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {

		int countChilds = getChildCount();
		int x;
		int y;
		int width;
		int height;
		FormatoPeca desenharPosicao;
		Peca peca;

		for (int i = 0; i < countChilds; i++) {
			peca = (Peca) getChildAt(i);
			desenharPosicao = peca.getPosicoes();

			x = desenharPosicao.getPrimeiraColuna() * measuredWidth;
			y = desenharPosicao.getPrimeiraLinha() * measuredHeight;

			width = desenharPosicao.getTotalColunas() * measuredWidth;
			height = desenharPosicao.getTotalLinhas() * measuredHeight;
			peca.layout(x, y, x + width, y + height);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		invalidate();
	}
}