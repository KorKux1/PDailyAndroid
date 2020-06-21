package co.edu.icesi.pdailyandroid.cognosis.canvas;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TMT;

public class TmtDraw extends View {
    DataScore dataScore = DataScore.getInstance();

    FragmentListener listener;

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
    private ArrayList<String> aTotal;
    private ArrayList<String> aTotalComparable;

    boolean b_one, b_two, b_three, b_four, b_five, b_a, b_b, b_c, b_d, b_e, end;
    public String s_one, s_two, s_three, s_four, s_five, s_a, s_b, s_c, s_d, s_e, score;


    int radius;
    String color_primary, color_accent;

    public TmtDraw(Context context) {
        super(context);

        listener = null;

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
        end = false;

        s_one = "1";
        s_two = "2";
        s_three = "3";
        s_four = "4";
        s_five = "5";
        s_a = "A";
        s_b = "B";
        s_c = "C";
        s_d = "D";
        s_e = "E";

        score = "vacio";

        aTotal = new ArrayList<String>();
        aTotalComparable = new ArrayList<>(Arrays.asList(s_one, s_a, s_two, s_b, s_three, s_c, s_four, s_d, s_five, s_e));

        color_primary = "#C4C4C4";
        color_accent = "#731DD8";
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);

        int scaleX = 6;
        int scaleY = 12;

        int width = getWidth();
        int height = getHeight();

        int x = width / scaleX;
        int y = height / scaleY;

        radius = (int) (y * 0.5);

        p_one.x = (int) (x * 2);
        p_one.y = (int) (y * 5);

        p_a.x = (int) (x * 4);
        p_a.y = (int) (y);

        p_two.x = (int) (x * 5);
        p_two.y = (int) (y * 3);

        p_b.x = (int) (x * 3.5);
        p_b.y = (int) (y * 3);

        p_three.x = (int) (x * 5);
        p_three.y = (int) (y * 8);

        p_c.x = (int) (x * 2);
        p_c.y = (int) (y * 9);

        p_four.x = (int) (x * 3);
        p_four.y = (int) (y * 7);

        p_d.x = (int) (x);
        p_d.y = (int) (y * 7);

        p_five.x = (int) (x * 0.75);
        p_five.y = (int) (y * 3);

        p_e.x = (int) (x * 2);
        p_e.y = (int) (y);

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

        if (!b_one) {
            drawCircle(canvas, paint, s_one, color_primary, p_one.x, p_one.y);
        } else {
            drawCircle(canvas, paint, s_one, color_accent, p_one.x, p_one.y);
        }

        if (!b_a) {
            drawCircle(canvas, paint, s_a, color_primary, p_a.x, p_a.y);
        } else {
            drawCircle(canvas, paint, s_a, color_accent, p_a.x, p_a.y);
        }

        if (!b_two) {
            drawCircle(canvas, paint, s_two, color_primary, p_two.x, p_two.y);
        } else {
            drawCircle(canvas, paint, s_two, color_accent, p_two.x, p_two.y);
        }

        if (!b_b) {
            drawCircle(canvas, paint, s_b, color_primary, p_b.x, p_b.y);
        } else {
            drawCircle(canvas, paint, s_b, color_accent, p_b.x, p_b.y);
        }

        if (!b_three) {
            drawCircle(canvas, paint, s_three, color_primary, p_three.x, p_three.y);
        } else {
            drawCircle(canvas, paint, s_three, color_accent, p_three.x, p_three.y);
        }

        if (!b_c) {
            drawCircle(canvas, paint, s_c, color_primary, p_c.x, p_c.y);
        } else {
            drawCircle(canvas, paint, s_c, color_accent, p_c.x, p_c.y);
        }

        if (!b_four) {
            drawCircle(canvas, paint, s_four, color_primary, p_four.x, p_four.y);
        } else {
            drawCircle(canvas, paint, s_four, color_accent, p_four.x, p_four.y);
        }

        if (!b_d) {
            drawCircle(canvas, paint, s_d, color_primary, p_d.x, p_d.y);
        } else {
            drawCircle(canvas, paint, s_d, color_accent, p_d.x, p_d.y);
        }

        if (!b_five) {
            drawCircle(canvas, paint, s_five, color_primary, p_five.x, p_five.y);
        } else {
            drawCircle(canvas, paint, s_five, color_accent, p_five.x, p_five.y);
        }

        if (!b_e) {
            drawCircle(canvas, paint, s_e, color_primary, p_e.x, p_e.y);
        } else {
            drawCircle(canvas, paint, s_e, color_accent, p_e.x, p_e.y);
        }

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeWidth(0);
        paint.setTextSize(50);
        canvas.drawText("Inicio", p_one.x, (int) (p_one.y + y), paint);
        canvas.drawText("Fin", p_e.x, (int) (p_e.y + y), paint);

        if (b_a && b_b && b_c && b_d && b_e && b_one && b_two && b_three && b_four && b_five) {
            end = true;

            if (aTotal.equals(aTotalComparable)) {
                score = "Yes";
            } else {
                score = "No";
            }

            dataScore.setMoca_answers_tmt(aTotal);
            dataScore.setMoca_score_tmt(score);

            Log.i("MOCA_SCORE_TMT", dataScore.getMoca_score_tmt());

            if(listener != null){
                listener.onScoreUpdated(dataScore.getMoca_score_tmt());
                Log.i("TALKING FROM TMTDRAW", dataScore.getMoca_score_tmt());
            }
        }
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
        canvas.drawText(text, x, (int) (y + (radius * 0.4)), paint);
        invalidate();
    }

    public interface FragmentListener {
        void onScoreUpdated(String score_tmt);
    }

    public void setListener(FragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();

        int x = getWidth();
        int y = getHeight();

        int fingerX = (int) event.getX();
        int fingerY = (int) event.getY();

        int distUno, distA, distDos, distB, distTres, distC, distCuatro, distD, distCinco, distE;

        distUno = (int) Math.sqrt(Math.pow((p_one.x) - event.getX(), 2) + Math.pow((p_one.y) - event.getY(), 2));
        distA = (int) Math.sqrt(Math.pow((p_a.x) - event.getX(), 2) + Math.pow((p_a.y) - event.getY(), 2));
        distDos = (int) Math.sqrt(Math.pow((p_two.x) - fingerX, 2) + Math.pow((p_two.y) - fingerY, 2));
        distB = (int) Math.sqrt(Math.pow((p_b.x) - fingerX, 2) + Math.pow((p_b.y) - fingerY, 2));
        distTres = (int) Math.sqrt(Math.pow((p_three.x) - fingerX, 2) + Math.pow((p_three.y) - fingerY, 2));
        distC = (int) Math.sqrt(Math.pow((p_c.x) - fingerX, 2) + Math.pow((p_c.y) - fingerY, 2));
        distCuatro = (int) Math.sqrt(Math.pow((p_four.x) - fingerX, 2) + Math.pow((p_four.y) - fingerY, 2));
        distD = (int) Math.sqrt(Math.pow((p_d.x) - fingerX, 2) + Math.pow((p_d.y) - fingerY, 2));
        distCinco = (int) Math.sqrt(Math.pow((p_five.x) - fingerX, 2) + Math.pow((p_five.y) - fingerY, 2));
        distE = (int) Math.sqrt(Math.pow((p_e.x) - fingerX, 2) + Math.pow((p_e.y) - fingerY, 2));

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

//                Log.i("END", String.valueOf(end));
//                Log.i("SCORE", score);


            if (!b_one) {
                if (distUno < radius) {
                    b_one = true;
                    aTotal.add(s_one);
                    invalidate();
                }
            }

            if (!b_a) {
                if (distA < radius) {
                    b_a = true;
                    aTotal.add(s_a);
                    invalidate();
                }
            }

            if (!b_two) {
                if (distDos < radius) {
                    b_two = true;
                    aTotal.add(s_two);
                    invalidate();
                }
            }

            if (!b_b) {
                if (distB < radius) {
                    b_b = true;
                    aTotal.add(s_b);
                    invalidate();
                }
            }

            if (!b_three) {
                if (distTres < radius) {
                    b_three = true;
                    aTotal.add(s_three);
                    invalidate();
                }
            }

            if (!b_c) {
                if (distC < radius) {
                    b_c = true;
                    aTotal.add(s_c);
                    invalidate();
                }
            }

            if (!b_four) {
                if (distCuatro < radius) {
                    b_four = true;
                    aTotal.add(s_four);
                    invalidate();
                }
            }

            if (!b_d) {
                if (distD < radius) {
                    b_d = true;
                    aTotal.add(s_d);
                    invalidate();
                }
            }

            if (!b_five) {
                if (distCinco < radius) {
                    b_five = true;
                    aTotal.add(s_five);
                    invalidate();
                }
            }

            if (!b_e) {
                if (distE < radius) {
                    b_e = true;
                    aTotal.add(s_e);
                    invalidate();
                }
            }

            return true;
        }
        return super.onTouchEvent(event);
    }

}