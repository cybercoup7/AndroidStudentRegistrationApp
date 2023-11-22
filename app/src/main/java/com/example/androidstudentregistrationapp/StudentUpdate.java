package com.example.androidstudentregistrationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StudentUpdate extends AppCompatActivity {
    Spinner spinner;
    EditText fullName;
    EditText programme;
    EditText phoneNumber;
    Button upDateButton, deleteButton, editButton;
    String id;
    String[] items = new String[]{"Male", "Female"};
    SqliteDbHelper sqliteDbHelper;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update);

        spinner = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        // Initialise  objects
        fullName = findViewById(R.id.full_name2);
        programme = findViewById(R.id.programme2);
        phoneNumber = findViewById(R.id.phone_number2);
        upDateButton = findViewById(R.id.register2);
        deleteButton = findViewById(R.id.delete);
        editButton = findViewById(R.id.edit);
        sqliteDbHelper = new SqliteDbHelper(this);
        student = new Student();

        // call to get data from another activity
        getIntentData();
        //set visibility and if enabled for text fields
        fullName.setEnabled(false);
        programme.setEnabled(false);
        phoneNumber.setEnabled(false);
        upDateButton.setVisibility(View.GONE);

        // on click listener to enable editing
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName.setEnabled(true);
                programme.setEnabled(true);
                phoneNumber.setEnabled(true);
                editButton.setVisibility(View.GONE);
                upDateButton.setVisibility(View.VISIBLE);
            }
        });
        // onclick listener to delete user/student
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqliteDbHelper.DeleteStudent(id);
            }
        });
        // on click listener to update student
        upDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                student.setName(String.valueOf(fullName.getText()));
                student.setProgramme(String.valueOf(programme.getText()));
                student.setCellNo(String.valueOf(phoneNumber.getText()));
                student.setGender(String.valueOf(spinner.getSelectedItem()));

                student.setId(id);

                sqliteDbHelper.upDateStudentDetails(student);
                fullName.setEnabled(false);
                programme.setEnabled(false);
                phoneNumber.setEnabled(false);
                editButton.setVisibility(View.VISIBLE);
                upDateButton.setVisibility(View.GONE);
            }
        });
    }

    // Get Extra intent data
    void getIntentData() {
        if (getIntent().hasExtra("id")) {
            fullName.setText(getIntent().getStringExtra("name"));
            programme.setText(getIntent().getStringExtra("programme"));
            phoneNumber.setText(getIntent().getStringExtra("cellNo"));

            id = getIntent().getStringExtra("id");
            spinner.setSelection(0);

        }
    }
}