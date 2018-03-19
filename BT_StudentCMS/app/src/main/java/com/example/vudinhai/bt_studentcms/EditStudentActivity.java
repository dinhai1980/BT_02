package com.example.vudinhai.bt_studentcms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditStudentActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtAddress;
    RadioGroup rdgGroup;
    RadioButton rdb1, rdb2;
    TextView txt;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        edtName = findViewById(R.id.editText);
        edtAddress = findViewById(R.id.editText2);
        rdgGroup = findViewById(R.id.radioGroup);
        rdb1 = findViewById(R.id.radioButton);
        rdb2 = findViewById(R.id.radioButton2);
        txt = findViewById(R.id.textView2);
        txt.setText("Edit Student");

        Intent intent = getIntent();
        Students students = (Students) intent.getSerializableExtra("student");
        id = students.getId();
        edtName.setText(students.getName().toString());
        edtAddress.setText(students.getAddress().toString());
        if(students.gender == 1){
           rdb1.setChecked(true);
        }else {
            rdb2.setChecked(true);
        }
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

            int gender=0;
            switch (rdgGroup.getCheckedRadioButtonId()){
                case R.id.radioButton:
                    gender = 1;
                    break;
                case R.id.radioButton2:
                    gender = 0;
                    break;
            }

            Students students = new Students(id,
                    edtName.getText().toString(),
                    edtAddress.getText().toString(),gender);

            Intent intent = new Intent(EditStudentActivity.this, MainActivity.class);
            intent.putExtra("student", students);
            setResult(102,intent);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
