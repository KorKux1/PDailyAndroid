package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
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
        private Paint paint = new Paint();
        private Path path = new Path();

        Point p_one = new Point();
        Point p_two = new Point();
        Point p_three = new Point();
        Point p_four = new Point();
        Point p_five = new Point();
        Point p_a = new Point();
        Point p_b = new Point();
        Point p_c = new Point();
        Point p_d = new Point();
        Point p_e = new Point();

        private ArrayList<Point> points = new ArrayList<Point>();
        private ArrayList<Boolean> aTotal = new ArrayList<Boolean>();

        boolean b_one, b_two, b_three, b_four, b_five, b_a, b_b, b_c, b_d, b_e;

        int radius;
        String color_primary, color_accent;

        public Canvas(Context context) {
            super(context);

            b_one = false;
            b_two = false;
            b_three = false;
            b_four = false;
            b_five = false;
            b_a = false;
            b_b = false;
            b_c = false;
            b_d = false;
            b_e = false;

            radius = 60;

            color_primary = "#C4C4C4";
            color_accent = "#00BCD1";
        }

        @Override
        protected void onDraw(android.graphics.Canvas canvas) {
            super.onDraw(canvas);

            int x = getWidth();
            int y = getHeight();

            p_one.x = (int) (x / 2) - 230;
            p_one.y = (int) (y / 2) - 150;

            p_a.x = (int) (x / 2) + 200;
            p_a.y = (int) (y / 2) - 600;

            p_two.x = (int) (x / 2) + 400;
            p_two.y = (int) (y / 2) - 300;

            p_b.x = (int) (x / 2) + 100;
            p_b.y = (int) (y / 2) - 300;

            p_three.x = (int) (x / 2) + 400;
            p_three.y = (int) (y / 2) + 100;

            p_c.x = (int) (x / 2) - 250;
            p_c.y = (int) (y / 2) + 320;

            p_four.x = (int) (x / 2);
            p_four.y = (int) (y / 2) + 50;

            p_d.x = (int) (x / 2) - 400;
            p_d.y = (int) (y / 2) + 50;

            p_five.x = (int) (x / 2) - 420;
            p_five.y = (int) (y / 2) - 650;

            p_e.x = (int) (x / 2) - 250;
            p_e.y = (int) (y / 2) - 650;

            boolean isDrawing = true;

            for (Point point : points) {
                paint.setAntiAlias(true);
                paint.setDither(true);
                paint.setColor(Color.parseColor(color_accent));
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeJoin(Paint.Join.ROUND);
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setStrokeWidth(12);
                if (isDrawing) {
                    isDrawing = false;
                    path.moveTo(point.x, point.y);
                } else {
                    path.lineTo(point.x, point.y);
                }
            }
            canvas.drawPath(path, paint);

            Log.i("B_ONE: ", String.valueOf(b_one));

            if (!b_one) {
                drawCircle(canvas, paint, "1", color_primary, p_one.x, p_one.y);
            } else {
                drawCircle(canvas, paint, "1", color_accent, p_one.x, p_one.y);
            }

            if (!b_a) {
                drawCircle(canvas, paint, "A", color_primary, p_a.x, p_a.y);
            } else {
                drawCircle(canvas, paint, "A", color_accent, p_a.x, p_a.y);
            }

            if (!b_two) {
                drawCircle(canvas, paint, "2", color_primary, p_two.x, p_two.y);
            } else {
                drawCircle(canvas, paint, "2", color_accent, p_two.x, p_two.y);
            }

            if (!b_b) {
                drawCircle(canvas, paint, "B", color_primary, p_b.x, p_b.y);
            } else {
                drawCircle(canvas, paint, "B", color_accent, p_b.x, p_b.y);
            }

            if (!b_three) {
                drawCircle(canvas, paint, "3", color_primary, p_three.x, p_three.y);
            } else {
                drawCircle(canvas, paint, "3", color_accent, p_three.x, p_three.y);
            }

            if (!b_c) {
                drawCircle(canvas, paint, "C", color_primary, p_c.x, p_c.y);
            } else {
                drawCircle(canvas, paint, "C", color_accent, p_c.x, p_c.y);
            }

            if (!b_four) {
                drawCircle(canvas, paint, "4", color_primary, p_four.x, p_four.y);
            } else {
                drawCircle(canvas, paint, "4", color_accent, p_four.x, p_four.y);
            }

            if (!b_d) {
                drawCircle(canvas, paint, "D", color_primary, p_d.x, p_d.y);
            } else {
                drawCircle(canvas, paint, "D", color_accent, p_d.x, p_d.y);
            }

            if (!b_five) {
                drawCircle(canvas, paint, "5", color_primary, p_five.x, p_five.y);
            } else {
                drawCircle(canvas, paint, "5", color_accent, p_five.x, p_five.y);
            }

            if (!b_e) {
                drawCircle(canvas, paint, "E", color_primary, p_e.x, p_e.y);
            } else {
                drawCircle(canvas, paint, "E", color_accent, p_e.x, p_e.y);
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
            canvas.drawText(text, x, y + 20, paint);
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

            distUno = (int) Math.sqrt(Math.pow(((x / 2) - 230) - event.getX(), 2) + Math.pow(((y / 2) - 150) - event.getY(), 2));
            distA = (int) Math.sqrt(Math.pow(((x / 2) + 200) - event.getX(), 2) + Math.pow(((y / 2) - 600) - event.getY(), 2));
            distDos = (int) Math.sqrt(Math.pow(((x / 2) + 400) - fingerX, 2) + Math.pow(((y / 2) - 300) - fingerY, 2));
            distB = (int) Math.sqrt(Math.pow(((x / 2) + 100) - fingerX, 2) + Math.pow(((y / 2) - 300) - fingerY, 2));
            distTres = (int) Math.sqrt(Math.pow(((x / 2) + 400) - fingerX, 2) + Math.pow(((y / 2) + 100) - fingerY, 2));
            distC = (int) Math.sqrt(Math.pow(((x / 2) - 250) - fingerX, 2) + Math.pow(((y / 2) + 320) - fingerY, 2));
            distCuatro = (int) Math.sqrt(Math.pow(((x / 2)) - fingerX, 2) + Math.pow(((y / 2) + 50) - fingerY, 2));
            distD = (int) Math.sqrt(Math.pow(((x / 2) - 400) - fingerX, 2) + Math.pow(((y / 2) + 50) - fingerY, 2));
            distCinco = (int) Math.sqrt(Math.pow(((x / 2) - 420) - fingerX, 2) + Math.pow(((y / 2) - 400) - fingerY, 2));
            distE = (int) Math.sqrt(Math.pow(((x / 2) - 250) - fingerX, 2) + Math.pow(((y / 2) - 650) - fingerY, 2));

            Log.i("POS_X: ", Integer.valueOf(x).toString());

            if (event.getAction() != MotionEvent.ACTION_UP) {
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
                Log.i("ARRAY_SIZE: ", String.valueOf(aTotal.size()));

                if (!b_one) {
                    if (distUno < 50) {
                        b_one = true;
                        aTotal.add(b_one);
                        invalidate();
                    }
                }

                if (!b_a) {
                    if (distA < 50) {
                        b_a = true;
                        aTotal.add(b_a);
                        invalidate();
                    }
                }

                if (!b_two) {
                    if (distDos < 50) {
                        b_two = true;
                        aTotal.add(b_two);
                        invalidate();
                    }
                }

                if (!b_b) {
                    if (distB < 50) {
                        b_b = true;
                        aTotal.add(b_b);
                        invalidate();
                    }
                }

                if (!b_three) {
                    if (distTres < 50) {
                        b_three = true;
                        aTotal.add(b_three);
                        invalidate();
                    }
                }

                if (!b_c) {
                    if (distC < 50) {
                        b_c = true;
                        aTotal.add(b_c);
                        invalidate();
                    }
                }

                if (!b_four) {
                    if (distCuatro < 50) {
                        b_four = true;
                        aTotal.add(b_four);
                        invalidate();
                    }
                }

                if (!b_d) {
                    if (distD < 50) {
                        b_d = true;
                        aTotal.add(b_d);
                        invalidate();
                    }
                }

                if (!b_five) {
                    if (distCinco < 50) {
                        b_five = true;
                        aTotal.add(b_five);
                        invalidate();
                    }
                }

                if (!b_e) {
                    if (distE < 50) {
                        b_e = true;
                        aTotal.add(b_e);
                        invalidate();
                    }
                }
                return true;
            }
            return super.onTouchEvent(event);
        }
    }
}



