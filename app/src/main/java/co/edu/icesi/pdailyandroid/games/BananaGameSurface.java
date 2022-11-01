package co.edu.icesi.pdailyandroid.games;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import co.edu.icesi.pdailyandroid.R;

public class BananaGameSurface extends SurfaceView implements Runnable {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private static final double ASPECT_RATIO = 1.77;
    private Thread thread;
    private SurfaceHolder holder;
    private boolean playing = true;
    private int[] handFrames, handFramesRight;
    private int handFrame = 0;
    private int side = LEFT;

    public BananaGameSurface(Context context) {
        super(context);
        init();
    }

    public BananaGameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BananaGameSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BananaGameSurface(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        holder = this.getHolder();
        loadSprites();
    }

    public void start() {
        handFrame = 0;
        playing = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
        while (!draw()) {
        }

        this.setAlpha(1);

        sleep(400);
        while (playing) {
            draw();
            sleep(1);
            update();
        }
    }

    private void update() {
        handFrame++;
        handFrame++;
        if (handFrame >= handFramesRight.length) {
            handFrame = 0;
        }
    }

    public boolean draw() {
        if (!holder.getSurface().isValid()) {
            return false;
        } else {
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.WHITE);
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), (side == LEFT) ? handFrames[handFrame] : handFramesRight[handFrame]);
                Paint paint = new Paint();
                canvas.drawBitmap(bitmap, null, new Rect(0, 0, canvas.getWidth(), (int) (ASPECT_RATIO * canvas.getWidth())), paint);
                holder.unlockCanvasAndPost(canvas);
            }
        }
        return true;

    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadSprites() {
        handFramesRight = new int[87];
        handFramesRight[0] = R.drawable.rightbananains0;
        handFramesRight[1] = R.drawable.rightbananains1;
        handFramesRight[2] = R.drawable.rightbananains2;
        handFramesRight[3] = R.drawable.rightbananains3;
        handFramesRight[4] = R.drawable.rightbananains4;
        handFramesRight[5] = R.drawable.rightbananains5;
        handFramesRight[6] = R.drawable.rightbananains6;
        handFramesRight[7] = R.drawable.rightbananains7;
        handFramesRight[8] = R.drawable.rightbananains8;
        handFramesRight[9] = R.drawable.rightbananains9;

        handFramesRight[10] = R.drawable.rightbananains10;
        handFramesRight[11] = R.drawable.rightbananains11;
        handFramesRight[12] = R.drawable.rightbananains12;
        handFramesRight[13] = R.drawable.rightbananains13;
        handFramesRight[14] = R.drawable.rightbananains14;
        handFramesRight[15] = R.drawable.rightbananains15;
        handFramesRight[16] = R.drawable.rightbananains16;
        handFramesRight[17] = R.drawable.rightbananains17;
        handFramesRight[18] = R.drawable.rightbananains18;
        handFramesRight[19] = R.drawable.rightbananains19;

        handFramesRight[20] = R.drawable.rightbananains20;
        handFramesRight[21] = R.drawable.rightbananains21;
        handFramesRight[22] = R.drawable.rightbananains22;
        handFramesRight[23] = R.drawable.rightbananains23;
        handFramesRight[24] = R.drawable.rightbananains24;
        handFramesRight[25] = R.drawable.rightbananains25;
        handFramesRight[26] = R.drawable.rightbananains26;
        handFramesRight[27] = R.drawable.rightbananains27;
        handFramesRight[28] = R.drawable.rightbananains28;
        handFramesRight[29] = R.drawable.rightbananains29;

        handFramesRight[30] = R.drawable.rightbananains30;
        handFramesRight[31] = R.drawable.rightbananains31;
        handFramesRight[32] = R.drawable.rightbananains32;
        handFramesRight[33] = R.drawable.rightbananains33;
        handFramesRight[34] = R.drawable.rightbananains34;
        handFramesRight[35] = R.drawable.rightbananains35;
        handFramesRight[36] = R.drawable.rightbananains36;
        handFramesRight[37] = R.drawable.rightbananains37;
        handFramesRight[38] = R.drawable.rightbananains38;
        handFramesRight[39] = R.drawable.rightbananains39;

        handFramesRight[40] = R.drawable.rightbananains40;
        handFramesRight[41] = R.drawable.rightbananains41;
        handFramesRight[42] = R.drawable.rightbananains42;
        handFramesRight[43] = R.drawable.rightbananains43;
        handFramesRight[44] = R.drawable.rightbananains44;
        handFramesRight[45] = R.drawable.rightbananains45;
        handFramesRight[46] = R.drawable.rightbananains46;
        handFramesRight[47] = R.drawable.rightbananains47;
        handFramesRight[48] = R.drawable.rightbananains48;
        handFramesRight[49] = R.drawable.rightbananains49;

        handFramesRight[50] = R.drawable.rightbananains50;
        handFramesRight[51] = R.drawable.rightbananains51;
        handFramesRight[52] = R.drawable.rightbananains52;
        handFramesRight[53] = R.drawable.rightbananains53;
        handFramesRight[54] = R.drawable.rightbananains54;
        handFramesRight[55] = R.drawable.rightbananains55;
        handFramesRight[56] = R.drawable.rightbananains56;
        handFramesRight[57] = R.drawable.rightbananains57;
        handFramesRight[58] = R.drawable.rightbananains58;
        handFramesRight[59] = R.drawable.rightbananains59;

        handFramesRight[60] = R.drawable.rightbananains60;
        handFramesRight[61] = R.drawable.rightbananains61;
        handFramesRight[62] = R.drawable.rightbananains62;
        handFramesRight[63] = R.drawable.rightbananains63;
        handFramesRight[64] = R.drawable.rightbananains64;
        handFramesRight[65] = R.drawable.rightbananains65;
        handFramesRight[66] = R.drawable.rightbananains66;
        handFramesRight[67] = R.drawable.rightbananains67;
        handFramesRight[68] = R.drawable.rightbananains68;
        handFramesRight[69] = R.drawable.rightbananains69;

        handFramesRight[70] = R.drawable.rightbananains70;
        handFramesRight[71] = R.drawable.rightbananains71;
        handFramesRight[72] = R.drawable.rightbananains72;
        handFramesRight[73] = R.drawable.rightbananains73;
        handFramesRight[74] = R.drawable.rightbananains74;
        handFramesRight[75] = R.drawable.rightbananains75;
        handFramesRight[76] = R.drawable.rightbananains76;
        handFramesRight[77] = R.drawable.rightbananains77;
        handFramesRight[78] = R.drawable.rightbananains78;
        handFramesRight[79] = R.drawable.rightbananains79;

        handFramesRight[80] = R.drawable.rightbananains80;
        handFramesRight[81] = R.drawable.rightbananains81;
        handFramesRight[82] = R.drawable.rightbananains82;
        handFramesRight[83] = R.drawable.rightbananains83;
        handFramesRight[84] = R.drawable.rightbananains84;
        handFramesRight[85] = R.drawable.rightbananains85;
        handFramesRight[86] = R.drawable.rightbananains86;

        handFrames = new int[87];
        handFrames[0] = R.drawable.leftbananains0;
        handFrames[1] = R.drawable.leftbananains1;
        handFrames[2] = R.drawable.leftbananains2;
        handFrames[3] = R.drawable.leftbananains3;
        handFrames[4] = R.drawable.leftbananains4;
        handFrames[5] = R.drawable.leftbananains5;
        handFrames[6] = R.drawable.leftbananains6;
        handFrames[7] = R.drawable.leftbananains7;
        handFrames[8] = R.drawable.leftbananains8;
        handFrames[9] = R.drawable.leftbananains9;

        handFrames[10] = R.drawable.leftbananains10;
        handFrames[11] = R.drawable.leftbananains11;
        handFrames[12] = R.drawable.leftbananains12;
        handFrames[13] = R.drawable.leftbananains13;
        handFrames[14] = R.drawable.leftbananains14;
        handFrames[15] = R.drawable.leftbananains15;
        handFrames[16] = R.drawable.leftbananains16;
        handFrames[17] = R.drawable.leftbananains17;
        handFrames[18] = R.drawable.leftbananains18;
        handFrames[19] = R.drawable.leftbananains19;

        handFrames[20] = R.drawable.leftbananains20;
        handFrames[21] = R.drawable.leftbananains21;
        handFrames[22] = R.drawable.leftbananains22;
        handFrames[23] = R.drawable.leftbananains23;
        handFrames[24] = R.drawable.leftbananains24;
        handFrames[25] = R.drawable.leftbananains25;
        handFrames[26] = R.drawable.leftbananains26;
        handFrames[27] = R.drawable.leftbananains27;
        handFrames[28] = R.drawable.leftbananains28;
        handFrames[29] = R.drawable.leftbananains29;

        handFrames[30] = R.drawable.leftbananains30;
        handFrames[31] = R.drawable.leftbananains31;
        handFrames[32] = R.drawable.leftbananains32;
        handFrames[33] = R.drawable.leftbananains33;
        handFrames[34] = R.drawable.leftbananains34;
        handFrames[35] = R.drawable.leftbananains35;
        handFrames[36] = R.drawable.leftbananains36;
        handFrames[37] = R.drawable.leftbananains37;
        handFrames[38] = R.drawable.leftbananains38;
        handFrames[39] = R.drawable.leftbananains39;

        handFrames[40] = R.drawable.leftbananains40;
        handFrames[41] = R.drawable.leftbananains41;
        handFrames[42] = R.drawable.leftbananains42;
        handFrames[43] = R.drawable.leftbananains43;
        handFrames[44] = R.drawable.leftbananains44;
        handFrames[45] = R.drawable.leftbananains45;
        handFrames[46] = R.drawable.leftbananains46;
        handFrames[47] = R.drawable.leftbananains47;
        handFrames[48] = R.drawable.leftbananains48;
        handFrames[49] = R.drawable.leftbananains49;

        handFrames[50] = R.drawable.leftbananains50;
        handFrames[51] = R.drawable.leftbananains51;
        handFrames[52] = R.drawable.leftbananains52;
        handFrames[53] = R.drawable.leftbananains53;
        handFrames[54] = R.drawable.leftbananains54;
        handFrames[55] = R.drawable.leftbananains55;
        handFrames[56] = R.drawable.leftbananains56;
        handFrames[57] = R.drawable.leftbananains57;
        handFrames[58] = R.drawable.leftbananains58;
        handFrames[59] = R.drawable.leftbananains59;

        handFrames[60] = R.drawable.leftbananains60;
        handFrames[61] = R.drawable.leftbananains61;
        handFrames[62] = R.drawable.leftbananains62;
        handFrames[63] = R.drawable.leftbananains63;
        handFrames[64] = R.drawable.leftbananains64;
        handFrames[65] = R.drawable.leftbananains65;
        handFrames[66] = R.drawable.leftbananains66;
        handFrames[67] = R.drawable.leftbananains67;
        handFrames[68] = R.drawable.leftbananains68;
        handFrames[69] = R.drawable.leftbananains69;

        handFrames[70] = R.drawable.leftbananains70;
        handFrames[71] = R.drawable.leftbananains71;
        handFrames[72] = R.drawable.leftbananains72;
        handFrames[73] = R.drawable.leftbananains73;
        handFrames[74] = R.drawable.leftbananains74;
        handFrames[75] = R.drawable.leftbananains75;
        handFrames[76] = R.drawable.leftbananains76;
        handFrames[77] = R.drawable.leftbananains77;
        handFrames[78] = R.drawable.leftbananains78;
        handFrames[79] = R.drawable.leftbananains79;

        handFrames[80] = R.drawable.leftbananains80;
        handFrames[81] = R.drawable.leftbananains81;
        handFrames[82] = R.drawable.leftbananains82;
        handFrames[83] = R.drawable.leftbananains83;
        handFrames[84] = R.drawable.leftbananains84;
        handFrames[85] = R.drawable.leftbananains85;
        handFrames[86] = R.drawable.leftbananains86;
    }

    public void pause() {
        playing = false;
    }

    public void setSide(int side) {
        this.side = side;
    }
}
