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

import br.com.belocodigo.Area;
import br.com.belocodigo.CoordenadasPeca;
import br.com.belocodigo.FormatoPeca;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;

public class Peca extends View {

	private char tipoPeca;

	protected ShapeDrawable desenho;
	private int color;

	private int measuredWidth;
	private int measuredHeight;

	private Path path;

	private FormatoPeca formatoPeca;
	private CoordenadasPeca coordenadasPeca;

	private int leftParent;
	private int topParent;

	public Peca(Activity context, char tipoPeca) {
		super(context);
		formatoPeca = new FormatoPeca();
		coordenadasPeca = new CoordenadasPeca();
		this.tipoPeca = tipoPeca;
	}

	public char getTipoPeca() {
		return tipoPeca;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void desenharPosicao(int coluna, int linha) {
		formatoPeca.add(coluna, linha);
	}

	public FormatoPeca getPosicoes() {
		return formatoPeca;
	}

	public CoordenadasPeca getCoordenadas() {
		return coordenadasPeca;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
		measuredHeight = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {

		if (patchCriado()) {
			int diferencaLeft = left - leftParent;
			int diferencaTop = top - topParent;

			leftParent = left;
			topParent = top;
			coordenadasPeca.reposicionar(diferencaLeft, diferencaTop);
		} else {
			leftParent = left;
			topParent = top;
			getPath();
		}

		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(getPath(), paintBlocos());
	}

	private Path getPath() {
		if (!patchCriado()) {
			path = gerarPath();
		}
		return path;
	}

	private boolean patchCriado() {
		return !(path == null);
	}

	private Path gerarPath() {
		int x;
		int y;
		int left = getLeft();
		int top = getTop();
		Path path = new Path();
		Area posicao;

		formatoPeca.rewinder();
		while (formatoPeca.hasNext()) {
			posicao = formatoPeca.next();

			x = posicao.getColuna() * measuredWidth - left;
			y = posicao.getLinha() * measuredHeight - top;

			// desenha as barra vertical do lado esquerdo do desenho
			if (!formatoPeca.temVizinhoLeft()) {
				path.moveTo(x, y);
				coordenadasPeca.left(x + leftParent, y + topParent);

				path.lineTo(x, y + measuredHeight);
				coordenadasPeca.left(x + leftParent, y + measuredHeight
						+ topParent);
			}
			// desenha as barra horizontal no topo do desenho
			if (!formatoPeca.temVizinhoTop()) {
				path.moveTo(x, y);
				coordenadasPeca.top(x + leftParent, y + topParent);

				path.lineTo(x + measuredWidth, y);
				coordenadasPeca.top(x + measuredWidth + leftParent, y
						+ topParent);
			}
			// desenha as barra vertical do lado direito do desenho
			if (!formatoPeca.temVizinhoRight()) {
				path.moveTo(x + measuredWidth, y);
				coordenadasPeca.right(x + measuredWidth + leftParent, y
						+ topParent);

				path.lineTo(x + measuredWidth, y + measuredHeight);
				coordenadasPeca.right(x + measuredWidth + leftParent, y
						+ measuredHeight + topParent);
			}
			// desenha as barra horizontal no fundo do desenho
			if (!formatoPeca.temVizinhoBottom()) {
				path.moveTo(x, y + measuredHeight);
				coordenadasPeca.bottom(x + leftParent, y + measuredHeight
						+ topParent);

				path.lineTo(x + measuredWidth, y + measuredHeight);
				coordenadasPeca.bottom(x + measuredWidth + leftParent, y
						+ measuredHeight + topParent);
			}
		}
		return path;
	}

	private Paint paintBlocos() {
		Paint mPaint = new Paint();
		mPaint.setDither(true);
		mPaint.setColor(color);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(3);
		return mPaint;
	}

	private Paint paintBorda() {
		Paint mPaint = new Paint();
		mPaint.setDither(true);
		mPaint.setColor(0xFFFFFF00);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(3);
		return mPaint;
	}
}