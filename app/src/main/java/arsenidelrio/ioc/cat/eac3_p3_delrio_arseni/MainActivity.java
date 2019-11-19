package arsenidelrio.ioc.cat.eac3_p3_delrio_arseni;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.io.IOException;

import static android.media.MediaPlayer.create;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable simpsonsAnimation;
    public MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView simpsonstitol = (ImageView) findViewById(R.id.titol_simpsons);
        simpsonstitol.setBackgroundResource(R.drawable.titol); //establim la llista d'animació
        simpsonsAnimation = (AnimationDrawable) simpsonstitol.getBackground(); //apliquem a l'ImageView
        simpsonsAnimation.start(); //s'inicia l'animació


        final ImageView donut = (ImageView) findViewById(R.id.donut);
        final ImageView ull = (ImageView) findViewById(R.id.ull);
        final ImageView engranatge_blau = (ImageView) findViewById(R.id.engranatge_blau);
        final ImageView engranatge_vermell = (ImageView) findViewById(R.id.engranatge_vermell);
        final ImageView engranatge_verd = (ImageView) findViewById(R.id.engranatge_verd);

        final ObjectAnimator engranatge_blautobjectAnimatorR = ObjectAnimator.ofFloat(engranatge_blau, "rotation", 360f, 0f); //rotació inversa agulles rellotge
        final ObjectAnimator engranatge_vermelltobjectAnimatorR = ObjectAnimator.ofFloat(engranatge_vermell, "rotation", 360f, 0f); //rotació inversa agulles rellotge
        final ObjectAnimator engranatge_verdtobjectAnimatorR = ObjectAnimator.ofFloat(engranatge_verd, "rotation", 0f, 360f); //rotació agulles rellotge
        engranatge_blautobjectAnimatorR.setDuration(2000); //durada
        engranatge_vermelltobjectAnimatorR.setDuration(3500); //durada diferent al ser més gran
        engranatge_verdtobjectAnimatorR.setDuration(2000); //durada
        engranatge_blautobjectAnimatorR.setRepeatCount(ValueAnimator.INFINITE); //repetició permanent
        engranatge_vermelltobjectAnimatorR.setRepeatCount(ValueAnimator.INFINITE); //repetició permanent
        engranatge_verdtobjectAnimatorR.setRepeatCount(ValueAnimator.INFINITE); //repetició permanent


        final ObjectAnimator donutobjectAnimatorV = ObjectAnimator.ofFloat(donut, "translationY", 100f, 500f); //desplaçament vertical
        final ObjectAnimator donutobjectAnimatorR = ObjectAnimator.ofFloat(donut, "rotation", 0f, 360f); //rotació
        donutobjectAnimatorV.setDuration(2000); //durada
        donutobjectAnimatorR.setDuration(2000); //"
        donutobjectAnimatorV.setRepeatCount(ValueAnimator.INFINITE); //repetició permanent
        donutobjectAnimatorR.setRepeatCount(ValueAnimator.INFINITE); // "
        donutobjectAnimatorV.setRepeatMode(ValueAnimator.REVERSE); //fa el moviment invers dels valors (100f, 500f)
        donutobjectAnimatorR.setRepeatMode(ValueAnimator.RESTART); //la rotació sobre si mateix constant


        final Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotation.setRepeatCount(ValueAnimator.INFINITE);
        rotation.setRepeatMode(ValueAnimator.REVERSE);


        simpsonstitol.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (donut.getVisibility() == View.VISIBLE) { //nomes cal xequejar la visibilitat d'un objecte per aplicar-ho a la resta
                    donut.setVisibility(View.INVISIBLE);
                    ull.setVisibility(View.INVISIBLE);
                    engranatge_blau.setVisibility(View.INVISIBLE);
                    engranatge_vermell.setVisibility(View.INVISIBLE);
                    engranatge_verd.setVisibility(View.INVISIBLE);
                } else {
                    donut.setVisibility(View.VISIBLE);
                    ull.setVisibility(View.VISIBLE);
                    engranatge_blau.setVisibility(View.VISIBLE);
                    engranatge_vermell.setVisibility(View.VISIBLE);
                    engranatge_verd.setVisibility(View.VISIBLE);
                    engranatge_blautobjectAnimatorR.start(); //inicio moviment al fer-se visible (opció personal)
                    engranatge_vermelltobjectAnimatorR.start(); //inicio moviment al fer-se visible (opció personal)
                    engranatge_verdtobjectAnimatorR.start(); //inicio moviment al fer-se visible (opció personal)
                    donutobjectAnimatorV.start(); //inicio moviment al fer-se visible (opció personal)
                    donutobjectAnimatorR.start(); // "
                    ull.startAnimation(rotation);
                }
            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.the_simpsons);

    }
    public void playmp3(View view) { //procediment de reproducció

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        } else {
            mediaPlayer.start();
        }
    }
}
