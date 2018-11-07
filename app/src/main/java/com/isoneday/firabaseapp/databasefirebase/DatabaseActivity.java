package com.isoneday.firabaseapp.databasefirebase;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isoneday.firabaseapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DatabaseActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listview;
    DatabaseReference reference;
    String BASE_URL="https://training-cabd7.firebaseio.com/";
    FirebaseClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            insertdata();

            }
        });

        reference = FirebaseDatabase.getInstance().getReference("hewan");
        client = new FirebaseClient(DatabaseActivity.this,listview,reference,BASE_URL);
        client.refreshData();
    }

    private void insertdata() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.customdialog_layout);
        dialog.setTitle("save data hewan");
        final EditText edtnama = (EditText) dialog.findViewById(R.id.nameEditText);
        final EditText edturl = (EditText) dialog.findViewById(R.id.urlEditText);
        final EditText edtinfo = (EditText) dialog.findViewById(R.id.infoEditText);
        Button btnsave = (Button) dialog.findViewById(R.id.saveBtn);
        Button btncancel = (Button) dialog.findViewById(R.id.cancelBtn);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        client.insertDataHewan(edtnama.getText().toString(),edturl.getText().toString(),edtinfo.getText().toString());
        dialog.dismiss();
            }

        });
        dialog.show();

    }

}
