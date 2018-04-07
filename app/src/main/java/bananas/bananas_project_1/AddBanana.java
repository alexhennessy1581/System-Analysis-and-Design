package bananas.bananas_project_1;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddBanana extends AppCompatActivity {

    Spinner clrSpinner;
    Button clrButton, dateButton, addButton;

    //String array used to store color choices hardcoded due to issues calling array resource
    String[] color_list = {"green", "green trace of yellow", "more green than yellow", "yellow trace of green", "yellow", "yellow trace of brown", "more brown than yellow", "brown"};

    //Variable used to get text user named the new banana
    EditText bText;
    String bName, bColor;

    private DatePickerDialog datePickerDialog;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_banana);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        clrSpinner = (Spinner) findViewById(R.id.color_spinner);
        clrButton = (Button) findViewById(R.id.color_button);

        dateButton = (Button) findViewById(R.id.date_button);
        addButton = (Button) findViewById(R.id.add_button);

        bText = (EditText) findViewById(R.id.name_box);

    }

    //Opens datepicker when you select date button
    public void dateButtonAction(View v)
    {
        DialogFragment newFragment = new DatePickerFragment2();
        newFragment.show(getFragmentManager(), "Date");

    }


    //This method is for displaying color choice from spinner drop down when user clicks on color button
    public void colorButtonAction(View view)
    {
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, color_list);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clrSpinner.setAdapter(colorAdapter);
        clrSpinner.setVisibility(View.VISIBLE);
        clrSpinner.performClick();
        clrSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position != 0) {
                    clrButton.setText(color_list[position]);

                                   }
            }
            // this closes the onItemSelected
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //addBananaAction opens confirm banana page and passes variables off to the database
    public void addBananaAction(View confirm_banana)
    {

        //bName is a String containing user input from edited text field.
        bName = (String) bText.getText().toString();
        bColor = (String) clrButton.getText().toString();
        String bDate = (String) dateButton.getText().toString();

        Banana person = new Banana(bDate, bName,
                bColor);

        DBHelper db = new DBHelper(this);
        db.addBanana(person);

        Intent confirmBanana  = new Intent(this, MainActivity.class);
        startActivity(confirmBanana);
    }
}
