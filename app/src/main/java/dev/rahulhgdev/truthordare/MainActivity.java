package dev.rahulhgdev.truthordare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView bottle;
    private Random random = new Random();
    private int lastDir;
    private boolean spinning;
    private TextView player_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottle = (ImageView) findViewById(R.id.bottle);
    }


    //Main Program

    public void spinBottle(View view) {

        if (!spinning) {
            int newDir = random.nextInt(1800);           // 1800 / 360 = 5 rounds you can increase 1800 for ore round
            float pivotX = (float) (bottle.getWidth() / 2.0);
            float pivotY = (float) (bottle.getHeight() / 2.0);

            Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
            rotate.setDuration(2500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            lastDir = newDir;
            bottle.startAnimation(rotate);
        }
    }

    //Alert Dialog for re-confirmation when you exit the game

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Sahi Main Ja Raha Hai Kya ?")
                .setCancelable(false)
                .setPositiveButton("Ha", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Na", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {  //Changes in arguments from dialog to dialogInterface

                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

//        super.onBackPressed();
    }

}