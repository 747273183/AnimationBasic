package com.example.animationbasic.property;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

import com.example.animationbasic.R;

import java.text.Format;

public class PropertyActivity extends AppCompatActivity {

    private static final String TAG = "PropertyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_property:
                //ValueAnimator
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.setDuration(1000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float animatedFraction = valueAnimator.getAnimatedFraction();
                        int animatedValue = (int) valueAnimator.getAnimatedValue();
                        Log.d(TAG, String.format("%.3f %d", animatedFraction, animatedValue));
                    }
                });
                valueAnimator.start();
                break;
            case R.id.tv_alpha:
                //加载animator资源文件方式定义属性动画
                Animator animator = AnimatorInflater.loadAnimator(this, R.animator.alpha);
                animator.setTarget(view);
                animator.start();
                break;
            case R.id.tv_scale:
                ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 3.0f, 1.0f).start();//使用java代码定义属性动画

                break;
            case R.id.tv_translate:
                //如果是让一个视图动起来,我们可以直接使用view类中的animate()获得一个ViewPropertyAnimator对象
                //再调用相应的属性方法
                view.animate().translationX(5000).setDuration(1000).start();
                break;
            case R.id.tv_rotate:
                view.animate().rotation(720).start();
                break;
            case R.id.tv_set:
//                //创建一个旋转动画者
//                Animator rotationAnimator=ObjectAnimator.ofFloat(view,"rotation",0,720);
//                //创建一个位移动画者
//                Animator translateAnimator = ObjectAnimator.ofFloat(view, "x", 0, 500);
//
//                //让两个动画一起播放
//                AnimatorSet set=new AnimatorSet();
////                set.playTogether(rotationAnimator,translateAnimator);
//                //让两动画按顺序播放
//                set.playSequentially(rotationAnimator,translateAnimator);
//                //开始
//                set.start();

                //第二种简化方式
//                view.animate().rotation(720).setDuration(1000).start();
//                view.animate().translationX(500).setDuration(1000).setStartDelay(1000).start();

                ObjectAnimator moveIn = ObjectAnimator.ofFloat(view, "translationX", 50, 0f);

                ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);

                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);

                AnimatorSet animSet = new AnimatorSet();

                animSet.play(rotate).with(fadeInOut).after(moveIn);

                animSet.setDuration(5000);

                animSet.start();
                break;


        }
    }
}
