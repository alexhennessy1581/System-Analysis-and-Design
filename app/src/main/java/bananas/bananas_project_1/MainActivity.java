package bananas.bananas_project_1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        boolean disclaimShowed_def = getResources().getBoolean(R.bool.disclaimer_show_default);
        boolean disclaimShowed = prefs.getBoolean("disclaimShowed", disclaimShowed_def);

        boolean notagain_def = getResources().getBoolean(R.bool.disclaimer_notagain_default);
        boolean notagain = prefs.getBoolean("notAgain", notagain_def);

        Log.d("Prefs", Boolean.toString(notagain) + " " + Boolean.toString(disclaimShowed));


        setContentView(R.layout.activity_main);

        if(!notagain && !disclaimShowed) {
            editor.putBoolean("disclaimShowed", true);
            editor.apply();
            showMyDialog(this);
        }
    }

    @Override
    protected void onDestroy()
    {
        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean("disclaimShowed", false);
        editor.apply();

        super.onDestroy();
    }
    
    public void checkBananas(View oldBanana) //Check Banana
    {
        Intent checkOnBanana = new Intent(this, CheckBananas.class);
        startActivity(checkOnBanana);
    }
    
        public void fulltermsandConditions(View conditions) //Full terms and conditions
    {
        Intent fullTerms = new Intent(this, full_conditions.class);
        startActivity(fullTerms);
    }
      public void addBanana(View newBanana) {
          Intent createBanana = new Intent(this, AddBanana.class);
          startActivity(createBanana);
      }

    public void developScreen(View newBanana) {
        Intent developBanana = new Intent(this, DeveloperScreen.class);
        startActivity(developBanana);
    }

    private void showMyDialog(Context context) {
        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.disclaimer_dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        TextView textView = (TextView) dialog.findViewById(R.id.txtTitle);
        Button btnBtmRight = (Button) dialog.findViewById(R.id.btnBtmRight);
        CheckBox chkBoxNotAgain = (CheckBox) dialog.findViewById(R.id.not_again);

        btnBtmRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        chkBoxNotAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                if (isChecked)
                    editor.putBoolean("notAgain", true);
                else
                    editor.putBoolean("notAgain", false);

                editor.apply();

            }
        });


                /**
                 * if you want the dialog to be specific size, do the following
                 * this will cover 85% of the screen (85% width and 85% height)
                 */
                dialog.show();
    }
}
