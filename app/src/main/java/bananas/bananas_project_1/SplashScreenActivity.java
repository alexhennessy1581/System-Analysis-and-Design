package bananas.bananas_project_1;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.ImageView;
        import android.widget.TextView;

/**
 * J
 */

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        final ImageView imageView = (ImageView) findViewById(R.id.logo);
        final TextView logo_titleView = (TextView) findViewById(R.id.logo_name);
        final TextView logo_sloganView = (TextView) findViewById(R.id.logo_slogan);

        final Animation animation_1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.translate);
        final Animation animation_2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.alpha);
        final Animation animation_3 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        imageView.startAnimation(animation_1);

        animation_1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                logo_titleView.startAnimation(animation_2);
                logo_sloganView.startAnimation(animation_2);

                logo_titleView.setVisibility(View.VISIBLE);
                logo_sloganView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation_2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                logo_titleView.startAnimation(animation_3);
                logo_sloganView.startAnimation(animation_3);
                imageView.startAnimation(animation_3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation_3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo_titleView.setVisibility(View.INVISIBLE);
                logo_sloganView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.INVISIBLE);

                finish();
                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}