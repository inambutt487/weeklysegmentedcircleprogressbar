package com.example.weeklysegmentedcircleprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircularSegmentedProgressBar extends View {
    private static final int NUM_SEGMENTS = 7;
    private static final float GAP_WIDTH = 4f;
    private static final String[] DAYS = {"M", "T", "W", "T", "F", "S", "S"};
    private Paint[] segmentPaints;
    private Paint textPaint;

    public CircularSegmentedProgressBar(Context context) {
        super(context);
        init();
    }

    public CircularSegmentedProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularSegmentedProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        segmentPaints = new Paint[NUM_SEGMENTS];
        int[] colors = {Color.RED, Color.GREEN, Color.GRAY};

        for (int i = 0; i < NUM_SEGMENTS; i++) {
            segmentPaints[i] = new Paint();
            segmentPaints[i].setColor(colors[i % colors.length]);
            segmentPaints[i].setStyle(Paint.Style.STROKE);
            segmentPaints[i].setStrokeWidth(30);
            segmentPaints[i].setAntiAlias(true);
        }

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2 - 20;

        float startAngle = -90;
        float sweepAngle = 360f / NUM_SEGMENTS - GAP_WIDTH;

        for (int i = 0; i < NUM_SEGMENTS; i++) {
            canvas.drawArc(
                20, 20, width - 20, height - 20,
                startAngle + i * (sweepAngle + GAP_WIDTH),
                sweepAngle,
                false,
                segmentPaints[i]
            );

            float angle = (startAngle + i * (sweepAngle + GAP_WIDTH) + sweepAngle / 2) * (float) Math.PI / 180;
            float textX = (float) (width / 2 + (radius - 30) * Math.cos(angle));
            float textY = (float) (height / 2 + (radius - 30) * Math.sin(angle)) + 10;

            canvas.drawText(DAYS[i], textX, textY, textPaint);
        }
    }
}
