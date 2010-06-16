package br.com.belocodigo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

public class KlotskiActivity extends Activity {

	private static List<QuebraCabeca> quebraCabecas = new ArrayList<QuebraCabeca>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		loadQuebraCebecas();

		KlotskiView klotskiView = new KlotskiView(this, quebraCabecas.get(0));

		setContentView(klotskiView);
	}

	private void loadQuebraCebecas() {
		QuebraCabeca quebraCabeca;

		quebraCabeca = new QuebraCabeca("Only 18 Steps", 6, 9, 18);
		quebraCabeca
			.addLinha("######")
			.addLinha("#a**b#")
			.addLinha("#m**n#")
			.addLinha("#cdef#")
			.addLinha("#ghij#")
			.addLinha("#k  l#")
			.addLinha("##--##")
			.addLinha("    ..")
			.addLinha("    ..");
		quebraCabecas.add(quebraCabeca);

		quebraCabeca = new QuebraCabeca("Daisy", 6, 9, 28);
		quebraCabeca.addLinha("######").addLinha("#a**b#").addLinha("#a**b#")
				.addLinha("#cdef#").addLinha("#zghi#").addLinha("#j  k#")
				.addLinha("##--##").addLinha("    ..").addLinha("    ..");
		quebraCabecas.add(quebraCabeca);

		quebraCabeca = new QuebraCabeca("Violet", 6, 9, 27);
		quebraCabeca.addLinha("######").addLinha("#a**b#").addLinha("#a**b#")
				.addLinha("#cdef#").addLinha("#cghi#").addLinha("#j  k#")
				.addLinha("##--##").addLinha("    ..").addLinha("    ..");
		quebraCabecas.add(quebraCabeca);

		quebraCabeca = new QuebraCabeca("Sunshine", 17, 22, 345);
		quebraCabeca
			.addLinha("       ...       ")
			.addLinha("      .. ..      ")
			.addLinha("      .   .      ")
			.addLinha("      .. ..      ")
			.addLinha("       ...       ")
			.addLinha("######-----######")
			.addLinha("#hh0iilltmmpp;qq#")
			.addLinha("#hh,iill mmpp:qq#")
			.addLinha("#2y{45v s w89x/z#")
			.addLinha("#jj6kkaa nnoo<rr#")
			.addLinha("#jj7kkaaunnoo>rr#")
			.addLinha("#33333TTJWW11111#")
			.addLinha("#33333TTJWW11111#")
			.addLinha("#33333GG HH11111#")
			.addLinha("#33333YYIgg11111#")
			.addLinha("#33333YYIgg11111#")
			.addLinha("#ddFeeA***BffOZZ#")
			.addLinha("#ddFee** **ffOZZ#")
			.addLinha("#MMKQQ*   *PPS^^#")
			.addLinha("#VVLXX** **bbRcc#")
			.addLinha("#VVLXXD***EbbRcc#")
			.addLinha("#################");
		quebraCabecas.add(quebraCabeca);
	}
}