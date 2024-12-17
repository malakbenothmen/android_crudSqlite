package com.example.tp6sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {
    EditText p , d , idposte ;
    private DAO dao = new DAO(UpdateActivity.this);
    private Button editbtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.update_act), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        p = findViewById(R.id.posteupdate);
        d = findViewById(R.id.despupdate);
        idposte = findViewById(R.id.idedited);
        editbtn = findViewById(R.id.editbtn);
        String idoffre = getIntent().getStringExtra("idoffre");
        String poste = getIntent().getStringExtra("poste");
        String descrption = getIntent().getStringExtra("description");
        p.setText(poste);
        d.setText(descrption);
        idposte.setText(idoffre);
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int res = dao.updateOffre(new Offre(Integer.parseInt(idoffre),p.getText().toString(), d.getText().toString()));
                if (res > 0) {
                    Toast.makeText(view.getContext(), "Mise à jour réussie", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Échec de la mise à jour", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}