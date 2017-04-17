package com.lcb.runanimaltovalue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lcb.runanimaltovalue.view.RunAnimalToValueView;

/**
 * @author lichuanbei
 * @date 创建时间：2017/4/17
 * @description 测试自定义View, 利用View的Runnable在固定的时间刷新渲染UI,点击控件执行动画
 */

public class MainActivity extends AppCompatActivity {
    RunAnimalToValueView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (RunAnimalToValueView) findViewById(R.id.textView);
        textView.setMax(700);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.startAnim();
            }
        });
    }
}
