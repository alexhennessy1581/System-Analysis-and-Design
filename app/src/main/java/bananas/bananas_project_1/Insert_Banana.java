package bananas.bananas_project_1;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Insert_Banana extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_banana);
    }

    protected void onInsertClick(View view){
        // Insert person into db
        TextView editTextDate = (TextView) findViewById(R.id.dateText);
        TextView editTextTime = (TextView) findViewById(R.id.timeText);
        EditText editTextColor = (EditText) findViewById(R.id.nColor);

        Banana person = new Banana(editTextDate.getText().toString(), editTextTime.getText().toString(),
                                    editTextColor.getText().toString(), "");

        DBHelper db = new DBHelper(this);
        db.addBanana(person);

        // Go back to main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "Date");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "Time");
    }
}
