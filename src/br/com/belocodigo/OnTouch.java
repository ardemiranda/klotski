package br.com.belocodigo;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnTouch implements OnTouchListener {

	int diferencaX;
	int diferencaY;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub

		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			int x = (int) event.getRawX() - diferencaX;
			int y = (int) event.getRawY() - diferencaY;

			v.layout(x, y, x + v.getWidth(), y + v.getHeight());
		} else if (event.getAction() == MotionEvent.ACTION_DOWN) {
			diferencaX = (int) event.getRawX() - v.getLeft();
			diferencaY = (int) event.getRawY() - v.getTop();
		}
		return true;
	}
}
