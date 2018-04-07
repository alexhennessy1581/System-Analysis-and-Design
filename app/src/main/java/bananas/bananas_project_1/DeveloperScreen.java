package bananas.bananas_project_1;

import android.content.Intent;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class DeveloperScreen extends AppCompatActivity {

    private Spinner peopleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_screen);

        // Get the textview
        TextView tv = (TextView) findViewById(R.id.someText);

        // Create the db handler
        DBHelper dbHelper = new DBHelper(this);

        // Get the person with id 1
        final List<Banana> Bananas = dbHelper.getAllBananas();

        Banana p;

        // If the person was not found
        if(Bananas.size() == 0) {
            // Insert a person so there is something ther
            p = new Banana("1-1-2000", "Person", "Yellow", "d");
            dbHelper.addBanana(p);
        }
        else {
            p = Bananas.get(0);
        }

        // Set the value of the textview
        tv.setText(p.getDate() + " " + p.getTime() + " -- " + p.getColor());

        // Get the spinner
        peopleSpinner = (Spinner) findViewById(R.id.spinner_people);

        // Load the data into the spinner
        this.loadSpinnerData();

        peopleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                TextView tv = (TextView) findViewById(R.id.someText);

                Banana selected = Bananas.get(position);

                tv.setText(selected.getDate() + " " + selected.getTime() + " -- " + selected.getColor());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        // database handler
        DBHelper db = new DBHelper(getApplicationContext());

        // Spinner Drop down elements
        List<Banana> Bananas = db.getAllBananas();

        // Creating adapter for spinner
        BananaAdapter dataAdapter = new BananaAdapter(this,
                android.R.layout.simple_spinner_item, Bananas);

        // Drop down layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        peopleSpinner.setAdapter(dataAdapter);
    }

    protected void onInsertClick(View view){
        Intent intent = new Intent(this, Insert_Banana.class);
        startActivity(intent);
    }

    protected void onDeleteClick(View view){
        // Get the selected person from the spinner
        Banana p = (Banana) peopleSpinner.getSelectedItem();

        // Create the db handler
        DBHelper db = new DBHelper(getApplicationContext());

        // Call the delete person method sending in the person object
        db.deleteBanana(p);

        // Reload the spinner dat
        this.loadSpinnerData();
    }

    protected void onEditClick(View view){
        // Get the selected person from the spinner
        Banana p = (Banana) peopleSpinner.getSelectedItem();

        // Create the intent
        Intent intent = new Intent(this, Edit_Banana.class);

        // Put the person id in the intent
        intent.putExtra("id", p.getId());

        // Start the edit activity
        startActivity(intent);
    }


}
