package bananas.bananas_project_1;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class CheckBananas extends AppCompatActivity {

    Spinner allSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_bananas);
        allSpinner = (Spinner) findViewById(R.id.spinner);

        // Create the db handler
        DBHelper dbHelper = new DBHelper(this);

        // Get the person with id 1
        final List<Banana> Bananas = dbHelper.getAllBananas();

        // Load the data into the spinner
        this.loadSpinnerData();

        allSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Banana selected = Bananas.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        DBHelper db = new DBHelper(getApplicationContext());

        // Spinner Drop down elements
        List<Banana> Bananas = db.getAllBananas();

        // Creating adapter for spinner
        BananaAdapter dataAdapter = new BananaAdapter(this,
                android.R.layout.simple_spinner_item, Bananas);

        // Drop down layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        allSpinner.setAdapter(dataAdapter);

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
        allSpinner.setAdapter(dataAdapter);
    }

    public void BananaBread(View getYum) //BananaBread
    {
        Intent checkOnYum = new Intent(this, BananaBread.class);
        startActivity(checkOnYum);
    }
}