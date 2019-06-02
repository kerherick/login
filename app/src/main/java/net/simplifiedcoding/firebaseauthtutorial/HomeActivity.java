package net.simplifiedcoding.firebaseauthtutorial;




import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etIdade;
    private ListView listV_dados;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Cliente> listCliente = new ArrayList<Cliente>();
    private ArrayAdapter<Cliente> arrayAdapterClientes;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etIdade = (EditText) findViewById(R.id.etIdade);
        listV_dados = (ListView) findViewById(R.id.listV_dados);


        //Metodos

        inicializarFirebase();





    }





    private void inicializarFirebase() {
        FirebaseApp.initializeApp(HomeActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();
    }

    public void Listar(View view) {


        databaseReference.child("Clientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCliente.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Cliente c = objSnapshot.getValue(Cliente.class);
                    listCliente.add(c);
                }
                arrayAdapterClientes = new ArrayAdapter<Cliente>(HomeActivity.this, android.R.layout.simple_expandable_list_item_1, listCliente);
                listV_dados.setAdapter(arrayAdapterClientes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void cadastrar(View view) {


        Cliente cli = new Cliente();


        cli.setNome(etNome.getText().toString());
        cli.setEmail(etEmail.getText().toString());
        cli.setIdade(etIdade.getText().toString());

        databaseReference.child("Clientes").push().setValue(cli);

        limparCampos();


    }

    private void limparCampos() {
        etNome.setText("");
        etEmail.setText("");
        etIdade.setText("");
    }


}