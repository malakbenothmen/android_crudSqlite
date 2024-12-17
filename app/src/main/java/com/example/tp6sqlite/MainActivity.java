package com.example.tp6sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView1 ;
    private CustomerAdepter mAdapter;
    private DAO dao ;
    private Button btn ;
    List<Offre>  offreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView1 = findViewById(R.id.recycleViewOffre);
        btn = findViewById(R.id.addOffre);




        dao = new DAO(this);

        offreList = dao.allOffres();




        mAdapter = new CustomerAdepter(MainActivity.this ,offreList);
        recyclerView1.setAdapter(mAdapter);
        LinearLayoutManager llm= new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView1.setLayoutManager(llm);






        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext() ,AddActivity.class) ;
                startActivityForResult(i,15);
            }
        });


        // Ajouter un ItemTouchHelper pour gérer le glisser
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false; // Aucun mouvement d'éléments
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();
                Offre offreToDelete = offreList.get(position);
                dao.deleteOffre(offreToDelete);

                offreList.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        }).attachToRecyclerView(recyclerView1);


    }


   @Override
    protected  void onActivityResult(int requestCode , int resultCode , Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);


        if(requestCode==15)
        {if(resultCode==RESULT_OK)
        {
            offreList = dao.allOffres();
            mAdapter.updateList(offreList);
            Toast.makeText(this, "ajout avec succées", Toast.LENGTH_SHORT).show();
        }

        }

    }
}