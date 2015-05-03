package com.example.foodexpress.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash extends Activity implements Runnable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

      //  ImageView myImageView = (ImageView) findViewById(R.id.imageView);
      //  Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeinsplash);
      //  myImageView.startAnimation(myFadeInAnimation);

        Handler handler = new Handler();
        handler.postDelayed(this, 3500);
    }
    public void run(){
        Intent it = new Intent();
        it.setClass(this, MainActivity.class);
        startActivity(it);
        finish();
    }
}