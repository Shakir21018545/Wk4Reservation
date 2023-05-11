package sg.edu.rp.c346.id21018545.wk4reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText Name;
    EditText Phone;
    EditText Size;
    CheckBox checkBox;
    DatePicker datePicker;
    TimePicker timePicker;
    Button Reserve;
    Button Reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.editTextName);
        Phone = findViewById(R.id.editTextPhone);
        Size = findViewById(R.id.editTextSize);
        checkBox = findViewById(R.id.checkBox);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        Reserve = findViewById(R.id.buttonReserve);
        Reset = findViewById(R.id.buttonReset);

        datePicker.updateDate(2020, 5, 1);

        Reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Name.getText().toString().isEmpty() || Size.getText().toString().isEmpty() || Phone.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Do not leave blanks!!", Toast.LENGTH_SHORT).show();
                }
                Calendar current = Calendar.getInstance();
                Calendar reservation = Calendar.getInstance();
                reservation.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                        timePicker.getHour(), timePicker.getMinute());
                if (current.after(reservation)) {
                    Toast.makeText(MainActivity.this, "You can't time travel! Please select future date and time", Toast.LENGTH_LONG).show();
                }
                timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay<8) {
                            Toast.makeText(MainActivity.this, "Unfortunately, we are closed from 9pm to 7.59am :(", Toast.LENGTH_LONG).show();
                        } else if (hourOfDay >= 21) {
                            Toast.makeText(MainActivity.this, "Unfortunately, we only open from 8am to 8.59pm :(", Toast.LENGTH_LONG).show();
                        }
                    }
                });


                String SmokeTable = "";
                if (checkBox.isChecked()) {
                    SmokeTable = "smoking";
                }
                else {
                    SmokeTable = "non-smoking";
                }

                Toast.makeText(MainActivity.this,
                        "(Name: " + Name.getText().toString() +
                                ") (People: " + Size.getText().toString() +
                                ") (Table: " + SmokeTable +
                                ") (Number: " + Phone.getText().toString() +
                                ") (Date: " + datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear() +
                                ") (Time: " + timePicker.getHour() + ":" + timePicker.getMinute() + ")", Toast.LENGTH_LONG).show();
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name.setText(null);
                Phone.setText(null);
                Size.setText(null);
                checkBox.setChecked(false);
                datePicker.updateDate(2020, 0, 1);
                timePicker.setHour(0);
                timePicker.setMinute(0);
            }
        });
    }

}