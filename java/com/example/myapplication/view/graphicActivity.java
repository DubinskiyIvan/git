package com.example.myapplication.view;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class graphicActivity extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphic);

        image = findViewById(R.id.imageView);

        final Animation animationRotate = AnimationUtils.loadAnimation(
                this, R.anim.poms_rotate);


        Button rotateButton = findViewById(R.id.button);
        rotateButton.setOnClickListener(v-> animate(animationRotate));
    }

    private void animate(Animation animation) {
        image.startAnimation(animation);
    }
}