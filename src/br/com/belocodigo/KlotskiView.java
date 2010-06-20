package br.com.belocodigo;

import java.util.HashMap;

import br.com.belocodigo.view.Bloco;
import br.com.belocodigo.view.Encache;
import br.com.belocodigo.view.Parede;
import br.com.belocodigo.view.Passante;
import br.com.belocodigo.view.Portao;
import br.com.belocodigo.view.Preso;
import android.app.Activity;
import android.graphics.Canvas;
import android.view.ViewGroup;

public class KlotskiView extends ViewGroup {

	private QuebraCabeca quebraCabeca;
	private Activity context;
	private HashMap<Character, Bloco> blocos;

	private int measuredWidth;
	private int measuredHeight;

	public KlotskiView(Activity context, QuebraCabeca quebraCabeca) {
		super(context);

		blocos = new HashMap<Character, Bloco>();
		this.context = context;
		this.quebraCabeca = quebraCabeca;
		setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		tabuleiro();
	}

	private void tabuleiro() {
		char linhas[];
		Bloco bloco;
		int quantidadeColunas = quebraCabeca.getWidth();
		int quantidadeLinhas = quebraCabeca.getHeight();

		for (int i = 0; i < quantidadeLinhas; i++) {
			linhas = quebraCabeca.getLinha(i);
			for (int j = 0; j < quantidadeColunas; j++) {
				bloco = getBloco(linhas[j]);
				bloco.desenharPosicao(j, i);
			}
		}
	}

	private Bloco getBloco(char tipo) {
		Bloco bloco;

		if (blocos.containsKey(tipo)) {
			return blocos.get(tipo);
		}

		switch (tipo) {
		case '#':
			bloco = new Parede(context);
			break;
		case ' ':
			bloco = new Bloco(context);
			break;
		case '.':
			bloco = new Encache(context);
			break;
		case '*':
			bloco = new Passante(context);
			break;
		case '-':
			bloco = new Portao(context);
			break;
		default:
			bloco = new Preso(context);
		}

		if (tipo != '#' && tipo != '-' && tipo != '.') {
			bloco.setOnTouchListener(new OnTouch());
		}

		blocos.put(tipo, bloco);

		addView(bloco);

		return bloco;
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
		DesenharPosicoes desenharPosicao;
		Bloco bloco;

		for (int i = 0; i < countChilds; i++) {
			bloco = (Bloco) getChildAt(i);
			desenharPosicao = bloco.getPosicoes();

			x = desenharPosicao.getPrimeiraColuna() * measuredWidth;
			y = desenharPosicao.getPrimeiraLinha() * measuredHeight;

			width = desenharPosicao.getTotalColunas() * measuredWidth;
			height = desenharPosicao.getTotalLinhas() * measuredHeight;
			bloco.layout(x, y, x + width, y + height);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		invalidate();
	}
}