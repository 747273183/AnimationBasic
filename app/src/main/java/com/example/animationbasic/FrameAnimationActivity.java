package com.example.animationbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;

public class FrameAnimationActivity extends AppCompatActivity {

    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        View view=findViewById(R.id.view);
        animationDrawable = (AnimationDrawable) view.getBackground();
//        animationDrawable.setOneShot(true);//动一次
    }


    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_start:
                animationDrawable.start();
                break;
            case R.id.btn_stop:
                animationDrawable.stop();

                break;
        }
    }
}
