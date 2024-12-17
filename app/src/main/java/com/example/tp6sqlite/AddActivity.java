package com.example.tp6sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {
    EditText id , desciption , poste;
    Button addbtn ;
    DAO dbOffres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_add), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        id = findViewById(R.id.IDInput);
        poste = findViewById(R.id.posteimput);
        desciption= findViewById(R.id.despinput);
        addbtn = findViewById(R.id.addbtn);
        dbOffres=new DAO(this);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Offre o = new Offre(poste.getText().toString(), desciption.getText().toString());
                dbOffres.addOffre(o);
                Intent i = new Intent(getApplicationContext(),MainActivity.class) ;
                i.putExtra("ok","ajout avec succées");
                setResult(RESULT_OK,i);
                finish();

            }
        });


    }


}