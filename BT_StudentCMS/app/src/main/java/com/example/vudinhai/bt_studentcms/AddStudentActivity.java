package com.example.vudinhai.bt_studentcms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class AddStudentActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtAddress;
    RadioGroup rdgGroup;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        edtName = findViewById(R.id.editText);
        edtAddress = findViewById(R.id.editText2);
        rdgGroup = findViewById(R.id.radioGroup);
        txt = findViewById(R.id.textView2);
        txt.setText("Add student");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        MenuItem itemAdd = menu.findItem(R.id.mnuAdd);
        itemAdd.setVisible(false);


        MenuItem itemSearch = menu.findItem(R.id.mnuSearch);
        itemSearch.setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.mnuSave){
            Random id = new Random();
            int gender=0;
            switch (rdgGroup.getCheckedRadioButtonId()){
                case R.id.radioButton:
                    gender = 1;
                    break;
                case R.id.radioButton2:
                    gender = 0;
                    break;
            }

            Students students = new Students(id.nextInt(),
                    edtName.getText().toString(),
                    edtAddress.getText().toString(),gender);

            Intent intent = new Intent(AddStudentActivity.this, MainActivity.class);
            intent.putExtra("student", students);
            setResult(101,intent);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
