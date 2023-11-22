package com.example.androidstudentregistrationapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SqliteDbHelper extends SQLiteOpenHelper {
    private Context context;
    private  static String DATABASE_NAME = "school.db";
    private  static int DATABASE_VERSION = 1;
    private  static String TABLE_NAME = "student";
    private final String COLUMN_ID = "id";
    private final String STUDENT_NAME= "student_name";
    private final String GENDER = "gender";
    private final String CELL_NO = "cell_no";
    private final String PROGRAMME = "programme";



    public SqliteDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STUDENT_NAME + " TEXT, " +
                GENDER + " TEXT, " +
                PROGRAMME + " TEXT, " +
                CELL_NO + " TEXT );" ;

         db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
           onCreate(db);
    }

    void addStudent(String fullName, String programme, String gender, String phoneNumber){
         SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, fullName);
        values.put(GENDER, gender);
        values.put(PROGRAMME,programme);
        values.put(CELL_NO,phoneNumber);
              long result = database.insert(TABLE_NAME, null, values);
       if(result == -1){
           Toast.makeText(context, "An Error Occurred", Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(context, "Student successfully registered!!", Toast.LENGTH_SHORT).show();
       }
    }
   void  upDateStudentDetails(Student student){
       SQLiteDatabase database = this.getWritableDatabase();
       String whereClaus =  COLUMN_ID + " = " + student.getId();
       ContentValues values = new ContentValues();
       values.put(STUDENT_NAME, student.getName());
       values.put(GENDER, student.getGender());
       values.put(PROGRAMME,student.getProgramme());
       values.put(CELL_NO,student.getCellNo());
       long response = database.update(TABLE_NAME, values,whereClaus,null);
       if(response == -1){
           Toast.makeText(context, "An Error Occurred", Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(context, "Updated Student data ", Toast.LENGTH_SHORT).show();
       }
   }
   void  DeleteStudent(String id){
       SQLiteDatabase database = this.getWritableDatabase();
       String whereClaus =  COLUMN_ID + " = " + id;
       long response = database.delete(TABLE_NAME,whereClaus,null);

       if(response == -1){
           Toast.makeText(context, "An Error Occurred", Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(context, "Student Data Deleted", Toast.LENGTH_SHORT).show();


       }
   }
    Cursor fetchStudents(){
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }
        return  cursor;
    }
}
