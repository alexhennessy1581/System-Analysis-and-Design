package bananas.bananas_project_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Edit_Banana extends AppCompatActivity {
    private Banana p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_banana);

        DBHelper dbHelper = new DBHelper(this);

        Intent intent = getIntent();

        p = dbHelper.getBanana(intent.getIntExtra("id",1));

        EditText nDate = (EditText)findViewById(R.id.nDate);
        EditText nTime = (EditText)findViewById(R.id.nTime);
        EditText nColor = (EditText)findViewById(R.id.nColor);

        nDate.setText(p.getDate());
        nTime.setText(p.getTime());
        nColor.setText(p.getColor());
    }

    protected void onUpdateClick(View view){
        // Insert person into db
        EditText nDate = (EditText)findViewById(R.id.nDate);
        EditText nTime = (EditText)findViewById(R.id.nTime);
        EditText nColor = (EditText)findViewById(R.id.nColor);

        DBHelper db = new DBHelper(this);

        p.setDate(nDate.getText().toString());
        p.setTime(nTime.getText().toString());
        p.setColor(nColor.getText().toString());

        db.updateBanana(p);

        // Go back to main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
