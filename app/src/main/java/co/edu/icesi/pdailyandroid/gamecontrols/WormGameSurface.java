package co.edu.icesi.pdailyandroid.gamecontrols;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.app.App;

public class WormGameSurface extends SurfaceView implements Runnable {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private Thread thread;
    private SurfaceHolder holder;
    private boolean playing = true;
    private int[] handFrames, handFramesRight;
    private static final double ASPECT_RATIO = 1.77;
    private int handFrame = 0;
    private int side = LEFT;

    public WormGameSurface(Context context) {
        super(context);
        init();
    }

    public WormGameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WormGameSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WormGameSurface(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        holder = this.getHolder();
        handFrames = new int[86];
        handFramesRight = new int[86];
        loadSprites();
    }

    public void start(){
        handFrame = 0;
        this.playing = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
        while(!draw()){}

        this.setAlpha(1);

        sleep(400);
        while(playing){
             draw();
             sleep(1);
             update();
        }
    }

    private void update() {
        handFrame++;
        handFrame++;
        if(handFrame >= handFramesRight.length){
            handFrame=0;
        }
    }

    public boolean draw() {
        if(!holder.getSurface().isValid()) {
            return false;
        }else {
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.WHITE);
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), (side == LEFT)?handFrames[handFrame]:handFramesRight[handFrame]);
                Paint paint = new Paint();
                canvas.drawBitmap(bitmap, null, new Rect(0, 0, canvas.getWidth(), (int) (ASPECT_RATIO * canvas.getWidth())), paint);
                holder.unlockCanvasAndPost(canvas);
            }
            return true;
        }
    }

    public void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadSprites(){
        handFramesRight = new int[87];
        handFramesRight[0] = R.drawable.rightwormins1;
        handFramesRight[1] = R.drawable.rightwormins1;
        handFramesRight[2] = R.drawable.rightwormins2;
        handFramesRight[3] = R.drawable.rightwormins3;
        handFramesRight[4] = R.drawable.rightwormins4;
        handFramesRight[5] = R.drawable.rightwormins5;
        handFramesRight[6] = R.drawable.rightwormins6;
        handFramesRight[7] = R.drawable.rightwormins7;
        handFramesRight[8] = R.drawable.rightwormins8;
        handFramesRight[9] = R.drawable.rightwormins9;

        handFramesRight[10] = R.drawable.rightwormins10;
        handFramesRight[11] = R.drawable.rightwormins11;
        handFramesRight[12] = R.drawable.rightwormins12;
        handFramesRight[13] = R.drawable.rightwormins13;
        handFramesRight[14] = R.drawable.rightwormins14;
        handFramesRight[15] = R.drawable.rightwormins15;
        handFramesRight[16] = R.drawable.rightwormins16;
        handFramesRight[17] = R.drawable.rightwormins17;
        handFramesRight[18] = R.drawable.rightwormins18;
        handFramesRight[19] = R.drawable.rightwormins19;

        handFramesRight[20] = R.drawable.rightwormins20;
        handFramesRight[21] = R.drawable.rightwormins21;
        handFramesRight[22] = R.drawable.rightwormins22;
        handFramesRight[23] = R.drawable.rightwormins23;
        handFramesRight[24] = R.drawable.rightwormins24;
        handFramesRight[25] = R.drawable.rightwormins25;
        handFramesRight[26] = R.drawable.rightwormins26;
        handFramesRight[27] = R.drawable.rightwormins27;
        handFramesRight[28] = R.drawable.rightwormins28;
        handFramesRight[29] = R.drawable.rightwormins29;

        handFramesRight[30] = R.drawable.rightwormins30;
        handFramesRight[31] = R.drawable.rightwormins31;
        handFramesRight[32] = R.drawable.rightwormins32;
        handFramesRight[33] = R.drawable.rightwormins33;
        handFramesRight[34] = R.drawable.rightwormins34;
        handFramesRight[35] = R.drawable.rightwormins35;
        handFramesRight[36] = R.drawable.rightwormins36;
        handFramesRight[37] = R.drawable.rightwormins37;
        handFramesRight[38] = R.drawable.rightwormins38;
        handFramesRight[39] = R.drawable.rightwormins39;

        handFramesRight[40] = R.drawable.rightwormins40;
        handFramesRight[41] = R.drawable.rightwormins41;
        handFramesRight[42] = R.drawable.rightwormins42;
        handFramesRight[43] = R.drawable.rightwormins43;
        handFramesRight[44] = R.drawable.rightwormins44;
        handFramesRight[45] = R.drawable.rightwormins45;
        handFramesRight[46] = R.drawable.rightwormins46;
        handFramesRight[47] = R.drawable.rightwormins47;
        handFramesRight[48] = R.drawable.rightwormins48;
        handFramesRight[49] = R.drawable.rightwormins49;

        handFramesRight[50] = R.drawable.rightwormins50;
        handFramesRight[51] = R.drawable.rightwormins51;
        handFramesRight[52] = R.drawable.rightwormins52;
        handFramesRight[53] = R.drawable.rightwormins53;
        handFramesRight[54] = R.drawable.rightwormins54;
        handFramesRight[55] = R.drawable.rightwormins55;
        handFramesRight[56] = R.drawable.rightwormins56;
        handFramesRight[57] = R.drawable.rightwormins57;
        handFramesRight[58] = R.drawable.rightwormins58;
        handFramesRight[59] = R.drawable.rightwormins59;

        handFramesRight[60] = R.drawable.rightwormins60;
        handFramesRight[61] = R.drawable.rightwormins61;
        handFramesRight[62] = R.drawable.rightwormins62;
        handFramesRight[63] = R.drawable.rightwormins63;
        handFramesRight[64] = R.drawable.rightwormins64;
        handFramesRight[65] = R.drawable.rightwormins65;
        handFramesRight[66] = R.drawable.rightwormins66;
        handFramesRight[67] = R.drawable.rightwormins67;
        handFramesRight[68] = R.drawable.rightwormins68;
        handFramesRight[69] = R.drawable.rightwormins69;

        handFramesRight[70] = R.drawable.rightwormins70;
        handFramesRight[71] = R.drawable.rightwormins71;
        handFramesRight[72] = R.drawable.rightwormins72;
        handFramesRight[73] = R.drawable.rightwormins73;
        handFramesRight[74] = R.drawable.rightwormins74;
        handFramesRight[75] = R.drawable.rightwormins75;
        handFramesRight[76] = R.drawable.rightwormins76;
        handFramesRight[77] = R.drawable.rightwormins77;
        handFramesRight[78] = R.drawable.rightwormins78;
        handFramesRight[79] = R.drawable.rightwormins79;

        handFramesRight[80] = R.drawable.rightwormins80;
        handFramesRight[81] = R.drawable.rightwormins81;
        handFramesRight[82] = R.drawable.rightwormins82;
        handFramesRight[83] = R.drawable.rightwormins83;
        handFramesRight[84] = R.drawable.rightwormins84;
        handFramesRight[85] = R.drawable.rightwormins85;
        handFramesRight[86] = R.drawable.rightwormins86;


        handFrames = new int[87];
        handFrames[0] = R.drawable.leftwormins0;
        handFrames[1] = R.drawable.leftwormins1;
        handFrames[2] = R.drawable.leftwormins2;
        handFrames[3] = R.drawable.leftwormins3;
        handFrames[4] = R.drawable.leftwormins4;
        handFrames[5] = R.drawable.leftwormins5;
        handFrames[6] = R.drawable.leftwormins6;
        handFrames[7] = R.drawable.leftwormins7;
        handFrames[8] = R.drawable.leftwormins8;
        handFrames[9] = R.drawable.leftwormins9;

        handFrames[10] = R.drawable.leftwormins10;
        handFrames[11] = R.drawable.leftwormins11;
        handFrames[12] = R.drawable.leftwormins12;
        handFrames[13] = R.drawable.leftwormins13;
        handFrames[14] = R.drawable.leftwormins14;
        handFrames[15] = R.drawable.leftwormins15;
        handFrames[16] = R.drawable.leftwormins16;
        handFrames[17] = R.drawable.leftwormins17;
        handFrames[18] = R.drawable.leftwormins18;
        handFrames[19] = R.drawable.leftwormins19;

        handFrames[20] = R.drawable.leftwormins20;
        handFrames[21] = R.drawable.leftwormins21;
        handFrames[22] = R.drawable.leftwormins22;
        handFrames[23] = R.drawable.leftwormins23;
        handFrames[24] = R.drawable.leftwormins24;
        handFrames[25] = R.drawable.leftwormins25;
        handFrames[26] = R.drawable.leftwormins26;
        handFrames[27] = R.drawable.leftwormins27;
        handFrames[28] = R.drawable.leftwormins28;
        handFrames[29] = R.drawable.leftwormins29;

        handFrames[30] = R.drawable.leftwormins30;
        handFrames[31] = R.drawable.leftwormins31;
        handFrames[32] = R.drawable.leftwormins32;
        handFrames[33] = R.drawable.leftwormins33;
        handFrames[34] = R.drawable.leftwormins34;
        handFrames[35] = R.drawable.leftwormins35;
        handFrames[36] = R.drawable.leftwormins36;
        handFrames[37] = R.drawable.leftwormins37;
        handFrames[38] = R.drawable.leftwormins38;
        handFrames[39] = R.drawable.leftwormins39;

        handFrames[40] = R.drawable.leftwormins40;
        handFrames[41] = R.drawable.leftwormins41;
        handFrames[42] = R.drawable.leftwormins42;
        handFrames[43] = R.drawable.leftwormins43;
        handFrames[44] = R.drawable.leftwormins44;
        handFrames[45] = R.drawable.leftwormins45;
        handFrames[46] = R.drawable.leftwormins46;
        handFrames[47] = R.drawable.leftwormins47;
        handFrames[48] = R.drawable.leftwormins48;
        handFrames[49] = R.drawable.leftwormins49;

        handFrames[50] = R.drawable.leftwormins50;
        handFrames[51] = R.drawable.leftwormins51;
        handFrames[52] = R.drawable.leftwormins52;
        handFrames[53] = R.drawable.leftwormins53;
        handFrames[54] = R.drawable.leftwormins54;
        handFrames[55] = R.drawable.leftwormins55;
        handFrames[56] = R.drawable.leftwormins56;
        handFrames[57] = R.drawable.leftwormins57;
        handFrames[58] = R.drawable.leftwormins58;
        handFrames[59] = R.drawable.leftwormins59;

        handFrames[60] = R.drawable.leftwormins60;
        handFrames[61] = R.drawable.leftwormins61;
        handFrames[62] = R.drawable.leftwormins62;
        handFrames[63] = R.drawable.leftwormins63;
        handFrames[64] = R.drawable.leftwormins64;
        handFrames[65] = R.drawable.leftwormins65;
        handFrames[66] = R.drawable.leftwormins66;
        handFrames[67] = R.drawable.leftwormins67;
        handFrames[68] = R.drawable.leftwormins68;
        handFrames[69] = R.drawable.leftwormins69;

        handFrames[70] = R.drawable.leftwormins70;
        handFrames[71] = R.drawable.leftwormins71;
        handFrames[72] = R.drawable.leftwormins72;
        handFrames[73] = R.drawable.leftwormins73;
        handFrames[74] = R.drawable.leftwormins74;
        handFrames[75] = R.drawable.leftwormins75;
        handFrames[76] = R.drawable.leftwormins76;
        handFrames[77] = R.drawable.leftwormins77;
        handFrames[78] = R.drawable.leftwormins78;
        handFrames[79] = R.drawable.leftwormins79;

        handFrames[80] = R.drawable.leftwormins80;
        handFrames[81] = R.drawable.leftwormins81;
        handFrames[82] = R.drawable.leftwormins82;
        handFrames[83] = R.drawable.leftwormins83;
        handFrames[84] = R.drawable.leftwormins84;
        handFrames[85] = R.drawable.leftwormins85;
        handFrames[86] = R.drawable.leftwormins86;
    }

    public void pause() {
        playing = false;
    }

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        super.draw(canvas);
    }

}
