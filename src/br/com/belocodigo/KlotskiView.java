package br.com.belocodigo;

import java.util.ArrayList;
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
		int larguraAltura[];
		Integer xyPosicaoInicial[];
		ArrayList<Integer[]> desenharPosicao;
		Bloco bloco;

		for (int i = 0; i < countChilds; i++) {
			bloco = (Bloco) getChildAt(i);
			desenharPosicao = bloco.getPosicoes();

			xyPosicaoInicial = desenharPosicao.get(0);

			x = xyPosicaoInicial[0] * measuredWidth;
			y = xyPosicaoInicial[1] * measuredHeight;
			larguraAltura = obterLarguraAltura(desenharPosicao);
			bloco.layout(x, y, x + larguraAltura[0], y + larguraAltura[1]);
		}
	}

	private int[] obterLarguraAltura(ArrayList<Integer[]> desenharPosicao) {
		ArrayList<Integer> posicaoAnteriorX = new ArrayList<Integer>();
		ArrayList<Integer> posicaoAnteriorY = new ArrayList<Integer>();
		int width = 0;
		int height = 0;
		for (Integer posicao[] : desenharPosicao) {
			if (!posicaoAnteriorX.contains(posicao[0])) {
				width += measuredWidth;
			}
			if (!posicaoAnteriorY.contains(posicao[1])) {
				height += measuredHeight;
			}

			posicaoAnteriorX.add(posicao[0]);
			posicaoAnteriorY.add(posicao[1]);
		}

		int retorno[] = { width, height };
		return retorno;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		invalidate();
	}
}