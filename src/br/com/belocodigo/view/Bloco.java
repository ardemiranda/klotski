package br.com.belocodigo.view;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.view.View;

public class Bloco extends View {

	protected ShapeDrawable desenho;
	private int color;

	private int measuredWidth;
	private int measuredHeight;

	private ArrayList<Integer[]> desenharPosicao;

	public Bloco(Activity context) {
		super(context);
		desenharPosicao = new ArrayList<Integer[]>();
	}

	public void compor(int linha, int coluna) {

	}

	public void setColor(int color) {
		this.color = color;
	}

	public void desenharPosicao(int linha, int coluna) {
		Integer posicao[] = { linha, coluna };
		desenharPosicao.add(posicao);
	}

	public ArrayList<Integer[]> getPosicoes() {
		return desenharPosicao;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
		measuredHeight = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		desenharBlocos();
		desenharBordas();

		desenho.draw(canvas);
	}

	private void desenharBlocos() {
		PathShape shape = new PathShape(getPath(), getWidth(), getHeight());
		desenho = new ShapeDrawable(shape);
		desenho.getPaint().setColor(color);
	}

	private void desenharBordas() {

	}

	private Path getPath() {
		int left;
		int top;
		int right;
		int bottom;

		Path pathHorizotal1 = new Path();
		Path pathHorizotal2 = new Path();
		Path pathVertical1 = new Path();
		Path pathVertical2 = new Path();
		path.moveTo(getLeft(), getTop());
		
		for (Integer posicao[] : desenharPosicao) {
			left = posicao[0] * measuredWidth;
			top = posicao[0] * measuredHeight;
			right = left + measuredWidth;
			bottom = top + measuredHeight;
			
			path.lineTo(right, bottom);
		}
		return path;
	}
}
