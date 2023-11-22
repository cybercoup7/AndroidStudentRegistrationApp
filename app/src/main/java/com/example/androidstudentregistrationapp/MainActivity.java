package com.example.androidstudentregistrationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    SqliteDbHelper dbHelper;
    CustomAdaptor adaptor;
    //    ArrayList<String> name, gender, programme, phone;
    ArrayList<Student> students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddStudent.class);
            startActivity(intent);
        });
        dbHelper = new SqliteDbHelper(MainActivity.this);
        students = new ArrayList<>();
        displayData();
        adaptor = new CustomAdaptor(MainActivity.this, students);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptor);
    }

    void displayData() {
        Cursor cursor = dbHelper.fetchStudents();


        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "No records yet", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                Student student = new Student();
                student.setId(cursor.getString(0));
                student.setName(cursor.getString(1));
                student.setGender(cursor.getString(2));
                student.setProgramme(cursor.getString(3));
                student.setCellNo(cursor.getString(4));
                students.add(student);
            }
        }
    }
}