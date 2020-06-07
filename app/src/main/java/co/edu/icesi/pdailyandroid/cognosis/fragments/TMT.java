package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import co.edu.icesi.pdailyandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TMT extends Fragment {
    public Canvas canvas;

    public TMT() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_t_m_t, container, false);

        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.rect);
        relativeLayout.addView(new Canvas(getActivity()));

        return rootView;
    }

    private class Canvas extends View {
        Paint paint = new Paint();
        boolean uno, a, dos, b, tres, c, cuatro, d, cinco, e;
        int radius;
        String color_primary, color_accent, col1, col2, col3, col4, col5, col6, col7, col8, col9, col0;

        public Canvas(Context context) {
            super(context);
            uno = false;
            a = false;
            dos = false;
            b = false;
            tres = false;
            c = false;
            cuatro = false;
            d = false;
            cinco = false;
            e = false;
            radius = 60;

            color_primary = "#C4C4C4";
            color_accent = "#00BCD1";

            col1 = "#C4C4C4";
            col2 = "#C4C4C4";
            col3 = "#C4C4C4";
            col4 = "#C4C4C4";
            col5 = "#C4C4C4";
            col6 = "#C4C4C4";
            col7 = "#C4C4C4";
            col8 = "#C4C4C4";
            col9 = "#C4C4C4";
            col0 = "#C4C4C4";
        }

        @Override
        protected void onDraw(android.graphics.Canvas canvas) {
            super.onDraw(canvas);

            int x = getWidth();
            int y = getHeight();
            paint.setStyle(Paint.Style.FILL);

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.GRAY);
            paint.setStrokeWidth(6);
//            paint.setColor(Color.parseColor(col1));
//            canvas.drawCircle((x / 2) - 230, (y / 2) - 150, radius, paint);
            drawCircle(canvas, paint, "1", color_primary, (x/2)-230, (y/2)-150);
            paint.setColor(Color.parseColor(col2));
            canvas.drawCircle((x / 2) + 200, (y / 2) - 600, radius, paint);
            paint.setColor(Color.parseColor(col3));
            canvas.drawCircle((x / 2) + 400, (y / 2) - 300, radius, paint);
            paint.setColor(Color.parseColor(col4));
            canvas.drawCircle((x / 2) + 100, (y / 2) - 300, radius, paint);
            paint.setColor(Color.parseColor(col5));
            canvas.drawCircle((x / 2) + 400, (y / 2) + 100, radius, paint);
            paint.setColor(Color.parseColor(col6));
            canvas.drawCircle((x / 2) - 250, (y / 2) + 320, radius, paint);
            paint.setColor(Color.parseColor(col7));
            canvas.drawCircle((x / 2), (y / 2) + 50, radius, paint);
            paint.setColor(Color.parseColor(col8));
            canvas.drawCircle((x / 2) - 400, (y / 2) + 50, radius, paint);
            paint.setColor(Color.parseColor(col9));
            canvas.drawCircle((x / 2) - 420, (y / 2) - 400, radius, paint);
            paint.setColor(Color.parseColor(col0));
            canvas.drawCircle((x / 2) - 250, (y / 2) - 650, radius, paint);


            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setStrokeWidth(0);
            paint.setTextSize(50);
            canvas.drawText("Inicio", (x / 2) - 230, (y / 2) - 40, paint);
            canvas.drawText("1", (x / 2) - 230, (y / 2) - 130, paint);
            canvas.drawText("A", (x / 2) + 200, (y / 2) - 580, paint);
            canvas.drawText("2", (x / 2) + 400, (y / 2) - 280, paint);
            canvas.drawText("B", (x / 2) + 100, (y / 2) - 280, paint);
            canvas.drawText("3", (x / 2) + 400, (y / 2) + 120, paint);
            canvas.drawText("C", (x / 2) - 250, (y / 2) + 340, paint);
            canvas.drawText("4", (x / 2), (y / 2) + 70, paint);
            canvas.drawText("D", (x / 2) - 400, (y / 2) + 70, paint);
            canvas.drawText("5", (x / 2) - 420, (y / 2) - 380, paint);
            canvas.drawText("E", (x / 2) - 250, (y / 2) - 630, paint);
            canvas.drawText("Fin", (x / 2) - 250, (y / 2) - 540, paint);

            Log.i("radio", String.valueOf(radius));

        }

        private void drawCircle(android.graphics.Canvas canvas, Paint paint, String text, String color, int x, int y) {
            paint.setStyle(Paint.Style.FILL);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.GRAY);
            paint.setStrokeWidth(6);
            paint.setColor(Color.parseColor(color));
            canvas.drawCircle(x, y, radius, paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setStrokeWidth(0);
            paint.setTextSize(50);
            canvas.drawText(text, x, y+20, paint);
            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int eventAction = event.getAction();
            int x = getWidth();
            int y = getHeight();

            int fingerX = (int) event.getX();
            int fingerY = (int) event.getY();

            int distUno, distA, distDos, distB, distTres, distC, distCuatro, distD, distCinco, distE;

            switch (eventAction) {
                case MotionEvent.ACTION_DOWN:
//                  distUno = (int) Math.sqrt(Math.pow(((x/2)-230) - fingerX, 2) + Math.pow(((y/2)-150) - fingerY, 2));
//                  distA = (int) Math.sqrt(Math.pow(((x / 2)+200) - fingerX, 2) + Math.pow(((y / 2)-600) - fingerY, 2));
//
//                  if (distUno < 50){
//                      uno=true;
//                      col1 = "#00BCD1";
//                      Log.i("asdfghjk", String.valueOf(distUno));
//                      invalidate();
//                  }
                    break;

                case MotionEvent.ACTION_UP:
                    break;

                case MotionEvent.ACTION_MOVE:

                    distUno = (int) Math.sqrt(Math.pow(((x / 2) - 230) - fingerX, 2) + Math.pow(((y / 2) - 150) - fingerY, 2));
                    distA = (int) Math.sqrt(Math.pow(((x / 2) + 200) - fingerX, 2) + Math.pow(((y / 2) - 600) - fingerY, 2));

                    if (distUno < 50) {
                        uno = true;
                        col1 = "#00BCD1";
                        Log.i("asdfghjk", String.valueOf(distUno));
                        invalidate();
                    }

                    break;
            }


            return super.onTouchEvent(event);
        }


    }
}



