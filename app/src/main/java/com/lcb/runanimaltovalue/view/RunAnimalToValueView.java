package com.lcb.runanimaltovalue.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by lichuanbei on 2017/4/17.
 * <p>
 * 功能描述：自定义测试控件,在指定的时间时间内，从0变化到设置的值
 */

public class RunAnimalToValueView extends AppCompatTextView {

    //显示真实值
    private float progress = 0f;
    //实际显示最大值
    private float max;

    //设置动画每次延迟时间
    private int timeMillis = 150;
    //动画进度总大小设置最大值百分比值为100
    private int MAX_VALUE = 100;
    //当前位置值百分比值
    private int currentValue = 0;
    //动画进度的增加值--5%
    private int animValueAddIndex = 5;


    public float getProgress() {
        return progress;
    }

    /**
     * @param progress
     */
    public void setProgress(float progress) {
        this.progress = progress;
    }

    public float getMax() {
        return max;
    }

    /**
     * 设置最大值
     *
     * @param max
     */
    public void setMax(float max) {
        this.max = max;
    }


    public RunAnimalToValueView(Context context) {
        super(context);
    }

    public RunAnimalToValueView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RunAnimalToValueView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 进度更新task。
     */
    private Runnable progressChangeTask = new Runnable() {
        @Override
        public void run() {
            removeCallbacks(this);
            currentValue += animValueAddIndex;
            //每次百分比走5%，那么100/5=20,20次走完，
            //时间计算3000/20=150毫秒

            progress = max * currentValue / MAX_VALUE;

            if (currentValue >= 0 && currentValue <= MAX_VALUE) {
                RunAnimalToValueView.this.setText(progress + "");
                postDelayed(progressChangeTask, timeMillis);
            } else {
                currentValue = validateAnimValue(currentValue);
                progress = validateProgress(progress);
            }

        }
    };

    /**
     * 验证真实值
     *
     * @param progress 你要验证的进度值。
     * @return 返回真正的进度值。
     */
    private float validateProgress(float progress) {
        if (progress > max)
            progress = max;
        else if (progress < 0)
            progress = 0;
        return max;
    }

    /**
     * 验证进度动画进度
     *
     * @param maxValue 你要验证的进度值。
     * @return 返回真正的进度值。
     */
    private int validateAnimValue(int maxValue) {
        if (maxValue > 100)
            maxValue = 100;
        else if (maxValue < 0)
            maxValue = 0;
        return maxValue;
    }

    /**
     * 开始。
     */
    public void startAnim() {
        stopAnim();
        post(progressChangeTask);
    }

    /**
     * 停止。
     */
    public void stopAnim() {
        removeCallbacks(progressChangeTask);
        resetProgress();
        invalidate();
    }

    /**
     * 重新开始。
     */
    public void reStart() {
        resetProgress();
        startAnim();
    }

    /**
     * 重置进度。
     */
    private void resetProgress() {
        currentValue = 0;
        progress = 0;

    }
}
