package com.example.androidstudentregistrationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {
    Spinner spinner;
    EditText fullName;
    EditText programme;
    EditText phoneNumber;
    Button registerButton;
    String[] items = new String[]{"Male", "Female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        spinner = findViewById(R.id.spinner);
        fullName = findViewById(R.id.full_name);
        programme = findViewById(R.id.programme);
        phoneNumber = findViewById(R.id.phone_number);
        registerButton = findViewById(R.id.register);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        registerButton.setOnClickListener(new View.OnClickListener() {
            SqliteDbHelper dbHelper = new SqliteDbHelper(AddStudent.this);

            @Override
            public void onClick(View v) {
                String name = fullName.getText().toString();
                String prog = programme.getText().toString();
                String cell = phoneNumber.getText().toString();
                String gender = spinner.getSelectedItem().toString().trim();
                dbHelper.addStudent(name, prog, gender, cell);
                fullName.setText("");
                programme.setText("");
                phoneNumber.setText("");
                spinner.setSelection(0);
            }
        });
    }
}