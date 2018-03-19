package com.example.vudinhai.bt_studentcms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Students> arrayList;
    ArrayList<Students> results;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);

        arrayList = Students.init();
        results = Students.init();

        studentAdapter = new StudentAdapter(MainActivity.this,
                                                R.layout.layout_row,
                                                arrayList);

        listView.setAdapter(studentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, EditStudentActivity.class);
                intent.putExtra("student",arrayList.get(i));
                startActivityForResult(intent,100);
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        MenuItem itemSave = menu.findItem(R.id.mnuSave);
        itemSave.setVisible(false);

        MenuItem itemSearch = menu.findItem(R.id.mnuSearch);
        SearchView searchView = (SearchView) itemSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Students> tmp = new ArrayList<Students>();
                for(Students s : results){
                    if(s.getName().toLowerCase().contains(newText.toLowerCase())){
                        tmp.add(s);
                    }
                }

                if(tmp.size() > 0){
                    studentAdapter.clear();
                    studentAdapter.addAll(tmp);
                    studentAdapter.notifyDataSetChanged();

                }
                if(newText.isEmpty()){
                    studentAdapter.clear();
                    studentAdapter.addAll(results);
                    studentAdapter.notifyDataSetChanged();
                }


                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== R.id.mnuAdd){
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivityForResult(intent,100);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.mnuEdit:
                Intent intent = new Intent(MainActivity.this, EditStudentActivity.class);
                intent.putExtra("student",arrayList.get(info.position));
                startActivityForResult(intent,100);
                break;
            case R.id.mnuDelete:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("BT_StudentsCMS");
                builder.setMessage("Ban co muon xoa sinh vien ? " );
                builder.setIcon(R.drawable.ic_warning_black_24dp);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        arrayList.remove(arrayList.get(info.position));
                        studentAdapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == 101){
            Students students = (Students) data.getSerializableExtra("student");
            arrayList.add(students);
            studentAdapter.notifyDataSetChanged();
        }

        if(requestCode == 100 && resultCode == 102){
            Students students = (Students) data.getSerializableExtra("student");

            for (int i = 0 ; i < arrayList.size() ; i++){
                if(arrayList.get(i).getId() == students.id){
                    arrayList.set(i, students);
                }
            }
            studentAdapter.notifyDataSetChanged();
        }


    }
}
