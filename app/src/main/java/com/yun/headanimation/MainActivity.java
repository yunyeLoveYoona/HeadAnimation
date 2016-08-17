package com.yun.headanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yun.headanimation.view.HeadGroupView;

public class MainActivity extends AppCompatActivity {
    private HeadGroupView headGroupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headGroupView = (HeadGroupView) findViewById(R.id.head);
        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50,
                    50));
            imageView.setImageResource(R.drawable.head);
            headGroupView.addView(imageView);
        }
    }
}
