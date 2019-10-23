package com.example.animationbasic.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animationbasic.R;

public class ViewAnimationActivity extends AppCompatActivity {

    private TextView tv_alpha;
    private TextView tv_scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.renew:
                recreate();
                break;
        }
        return true;
    }

    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_alpha:
                Animation alphaAnimation=  AnimationUtils.loadAnimation(this,R.anim.alpha);
                view.startAnimation(alphaAnimation);
               break;
            case R.id.tv_scale:
                Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
                view.startAnimation(scaleAnimation);
                break;
            case  R.id.tv_translate:
              Animation translateAnimation=  AnimationUtils.loadAnimation(this,R.anim.translate);
              view.startAnimation(translateAnimation);
                break;
            case  R.id.tv_rotate:
                Animation rotateAnimation=  AnimationUtils.loadAnimation(this,R.anim.rotate);
                view.startAnimation(rotateAnimation);
                break;
            case  R.id.tv_set:
                Animation setAnimation=  AnimationUtils.loadAnimation(this,R.anim.set);
                view.startAnimation(setAnimation);
                break;
            case R.id.viewAcclerate:
            case R.id.viewLinear:
                View view1=findViewById(R.id.viewAcclerate);
                View view2=findViewById(R.id.viewLinear);

                Animation animationAccelerate=AnimationUtils.loadAnimation(this,R.anim.translate);
                Animation animationLinear=AnimationUtils.loadAnimation(this,R.anim.translate);

                animationAccelerate.setInterpolator(new AccelerateInterpolator());
                animationLinear.setInterpolator(new LinearInterpolator());

                view1.startAnimation(animationAccelerate);
                view2.startAnimation(animationLinear);

                break;
        }
    }
}
