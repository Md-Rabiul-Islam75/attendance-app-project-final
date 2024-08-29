package com.example.projectnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;

    ClassAdapter classAdapter;

    RecyclerView.LayoutManager layoutManager;

    ArrayList<ClassItem> classItems = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(v -> showDialog());

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        classAdapter = new ClassAdapter(this,classItems);
        recyclerView.setAdapter(classAdapter);
    }

    private void showDialog() {

     MyDialog dialog = new MyDialog();
     dialog.show(getSupportFragmentManager(),MyDialog.CLASS_ADD_DIALOG);

     dialog.setListener((className,subjectName) -> addClass(className,subjectName));

    }

    private void addClass(String className,String subjectName) {

        classItems.add(new ClassItem(className,subjectName));
        classAdapter.notifyDataSetChanged();

    }

}