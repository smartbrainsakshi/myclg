package com.project.myclg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//import com.example.sakshipc.profilemain.R;

public class gradientbackground extends AppCompatActivity {
    private GradientBackgroundPainter gradientBackgroundPainter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradientbackground);
        View backgroundImage = findViewById(R.id.root_view);

        final int[] drawables = new int[3];
        drawables[0] = R.drawable.gradient1;
        drawables[1] = R.drawable.gradient2;
        drawables[2] = R.drawable.gradient3;

        gradientBackgroundPainter = new GradientBackgroundPainter(backgroundImage, drawables);
        gradientBackgroundPainter.start();

    }
    @Override protected void onDestroy() {
        super.onDestroy();
        gradientBackgroundPainter.stop();
    }
}

