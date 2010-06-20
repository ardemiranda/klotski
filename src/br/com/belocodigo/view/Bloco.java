package br.com.belocodigo.view;

import br.com.belocodigo.DesenharPosicao;
import br.com.belocodigo.DesenharPosicoes;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;

public class Bloco extends View {

	public char tipoBloco;

	protected ShapeDrawable desenho;
	private int color;

	private int measuredWidth;
	private int measuredHeight;

	private Path path;

	private DesenharPosicoes desenharPosicoes;

	public Bloco(Activity context) {
		super(context);
		desenharPosicoes = new DesenharPosicoes();
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void desenharPosicao(int coluna, int linha) {
		desenharPosicoes.add(coluna, linha);
	}

	public DesenharPosicoes getPosicoes() {
		return desenharPosicoes;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
		measuredHeight = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// desenharBlocos();
		// desenharBordas();
		// desenharBlocos2(canvas);
		// desenho.draw(canvas);

		System.out.println("l:" + getLeft() + " r:" + getRight());
		canvas.drawPath(getPath(), paintBlocos());
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

	private Path getPath() {
		if (path == null) {
			path = gerarPath();
		}
		return path;
	}

	private Path gerarPath() {
		int x;
		int y;
		Path path = new Path();
		DesenharPosicao posicao;

		desenharPosicoes.rewinder();
		while (desenharPosicoes.hasNext()) {
			posicao = desenharPosicoes.next();

			x = posicao.getColuna() * measuredWidth - getLeft();
			y = posicao.getLinha() * measuredHeight - getTop();

			if (!desenharPosicoes.temVizinhoLeft()) {
				path.moveTo(x, y);
				path.lineTo(x, y + measuredHeight);
			}
			if (!desenharPosicoes.temVizinhoTop()) {
				path.moveTo(x, y);
				path.lineTo(x + measuredWidth, y);
			}
			if (!desenharPosicoes.temVizinhoRight()) {
				path.moveTo(x + measuredWidth, y);
				path.lineTo(x + measuredWidth, y + measuredHeight);
			}
			if (!desenharPosicoes.temVizinhoBootom()) {
				path.moveTo(x, y + measuredHeight);
				path.lineTo(x + measuredWidth, y + measuredHeight);
			}
		}
		return path;
	}
}