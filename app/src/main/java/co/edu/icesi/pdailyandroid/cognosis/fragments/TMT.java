package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TMT extends Fragment {
    public android.graphics.Canvas canvas;

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
        Path path = new Path();
        ArrayList<Point> points = new ArrayList<Point>();

        ArrayList<Boolean> aTotal = new ArrayList<Boolean>()
        ;

        boolean uno, a, dos, b, tres, c, cuatro, d, cinco, e;
        int radius;
        int i,ii,iii,iv,v,vi,vii,viii,ix,x;
        String color_primary, color_accent;

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
        }

        @Override
        protected void onDraw(android.graphics.Canvas canvas) {
            super.onDraw(canvas);

            int x = getWidth();
            int y = getHeight();

            boolean first = true;

            for (Point point : points){
                paint.setAntiAlias(true);
                paint.setDither(true);
                paint.setColor(Color.parseColor(color_accent));
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeJoin(Paint.Join.ROUND);
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setStrokeWidth(12);
                if(first){
                    first = false;
                    path.moveTo(point.x, point.y);
                }else{
                    path.lineTo(point.x, point.y);
                }
            }
            canvas.drawPath(path, paint);

                Log.i("UNO", String.valueOf(uno));
            if (!uno){
                drawCircle(canvas, paint, "1", color_primary, (x/2)-230, (y/2)-150);
            } else {
                drawCircle(canvas, paint, "1", color_accent, (x/2)-230, (y/2)-150);

            }

            if (!a){
                drawCircle(canvas, paint, "A", color_primary, (x / 2) + 200, (y / 2) - 600);
            } else {
                drawCircle(canvas, paint, "A", color_accent, (x / 2) + 200, (y / 2) - 600);

            }

            if (!dos){
                drawCircle(canvas, paint, "2", color_primary, (x / 2) + 400, (y / 2) - 300);
            } else {
                drawCircle(canvas, paint, "2", color_accent, (x / 2) + 400, (y / 2) - 300);

            }

            if (!b){
                drawCircle(canvas, paint, "B", color_primary, (x / 2) + 100, (y / 2) - 300);
            } else {
                drawCircle(canvas, paint, "B", color_accent, (x / 2) + 100, (y / 2) - 300);

            }

            if (!tres){
                drawCircle(canvas, paint, "3", color_primary, (x / 2) + 400, (y / 2) + 100);
            } else {

            }

            if (!c){
                drawCircle(canvas, paint, "C", color_primary, (x / 2) - 250, (y / 2) + 320);
            } else {
                drawCircle(canvas, paint, "C", color_accent, (x / 2) - 250, (y / 2) + 320);

            }

            if (!cuatro){
                drawCircle(canvas, paint, "4", color_primary, (x / 2), (y / 2) + 50);
            } else {
                drawCircle(canvas, paint, "4", color_accent, (x / 2), (y / 2) + 50);

            }

            if (!d){
                drawCircle(canvas, paint, "D", color_primary, (x / 2) - 400, (y / 2) + 50);
            } else {
                drawCircle(canvas, paint, "D", color_accent, (x / 2) - 400, (y / 2) + 50);

            }

            if (!cinco){
                drawCircle(canvas, paint, "5", color_primary, (x / 2) - 420, (y / 2) - 400);
            } else {
                drawCircle(canvas, paint, "5", color_accent, (x / 2) - 420, (y / 2) - 400);

            }

            if (!e){
                drawCircle(canvas, paint, "E", color_primary, (x / 2) - 250, (y / 2) - 650);
            } else {
                drawCircle(canvas, paint, "E", color_accent, (x / 2) - 250, (y / 2) - 650);
            }

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setStrokeWidth(0);
            paint.setTextSize(50);
            canvas.drawText("Inicio", (x / 2) - 230, (y / 2) - 40, paint);

            canvas.drawText("Fin", (x / 2) - 250, (y / 2) - 540, paint);


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
        public boolean onTouchEvent( MotionEvent event) {


            int eventAction = event.getAction();
            int x = getWidth();
            int y = getHeight();

            int fingerX = (int) event.getX();
            int fingerY = (int) event.getY();

            int distUno, distA, distDos, distB, distTres, distC, distCuatro, distD, distCinco, distE;

            distUno = (int) Math.sqrt(Math.pow(((x/2)-230) - event.getX(), 2) + Math.pow(((y/2)-150) - event.getY(), 2));
            distA = (int) Math.sqrt(Math.pow(((x / 2)+200) - event.getX(), 2) + Math.pow(((y / 2)-600) - event.getY(), 2));
            distDos = (int) Math.sqrt(Math.pow(((x / 2) + 400) - fingerX, 2) + Math.pow(((y / 2) - 300) - fingerY, 2));
            distB = (int) Math.sqrt(Math.pow(((x / 2) + 100) - fingerX, 2) + Math.pow(((y / 2) - 300) - fingerY, 2));
            distTres = (int) Math.sqrt(Math.pow(((x / 2) + 400) - fingerX, 2) + Math.pow(((y / 2) + 100) - fingerY, 2));
            distC = (int) Math.sqrt(Math.pow(( (x / 2) - 250) - fingerX, 2) + Math.pow(((y / 2) + 320) - fingerY, 2));
            distCuatro = (int) Math.sqrt(Math.pow(((x / 2) ) - fingerX, 2) + Math.pow(((y / 2) + 50) - fingerY, 2));
            distD = (int) Math.sqrt(Math.pow(((x / 2) - 400) - fingerX, 2) + Math.pow(((y / 2) + 50) - fingerY, 2));
            distCinco = (int) Math.sqrt(Math.pow(((x / 2) - 420) - fingerX, 2) + Math.pow(((y / 2) - 400) - fingerY, 2));
            distE = (int) Math.sqrt(Math.pow(((x / 2) - 250) - fingerX, 2) + Math.pow(((y / 2) - 650) - fingerY, 2));

            Log.i("asdfghjk", Integer.valueOf(x).toString());

            if(event.getAction() != MotionEvent.ACTION_UP) {
                paint.setAntiAlias(true);
                paint.setDither(true);
                paint.setColor(Color.parseColor(color_accent));
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeJoin(Paint.Join.ROUND);
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setStrokeWidth(12);
                Point point = new Point();
                point.x = (int) event.getX();
                point.y = (int) event.getY();
                points.add(point);
                invalidate();
                Log.d(TAG, "POINT: " + point);
                Log.i("ARRAY", String.valueOf(aTotal.size()));

                if (!uno){
                    if (distUno < 50){
                        uno=true;
                        aTotal.add(uno);
                        invalidate();
                    }
                }

                if (!a){
                    if (distA < 50) {
                        a = true;
                        aTotal.add(a);
                        invalidate();
                    }
                }

                if (!dos) {
                    if (distDos < 50) {
                        dos = true;
                        aTotal.add(dos);
                        invalidate();
                    }
                }

                if (!b) {
                    if (distB < 50) {
                        b = true;
                        aTotal.add(b);
                        invalidate();
                    }
                }

                if (!tres) {
                    if (distTres < 50) {
                        tres = true;
                        aTotal.add(tres);
                        invalidate();
                    }
                }

                if (!c) {
                    if (distC < 50) {
                        c = true;
                        aTotal.add(c);
                        invalidate();
                    }
                }

                if (!cuatro) {
                    if (distCuatro < 50) {
                        cuatro = true;
                        aTotal.add(cuatro);
                        invalidate();
                    }
                }

                if (!d) {
                    if (distD < 50) {
                        d = true;
                        aTotal.add(d);
                        invalidate();
                    }
                }

                if (!cinco) {
                    if (distCinco < 50) {
                        cinco = true;
                        aTotal.add(cinco);
                        invalidate();
                    }
                }

                if (!e) {
                    if (distE < 50) {
                        e = true;
                        aTotal.add(e);
                        invalidate();
                    }
                }
                return true;
            }


            return super.onTouchEvent(event);
        }


    }
}



