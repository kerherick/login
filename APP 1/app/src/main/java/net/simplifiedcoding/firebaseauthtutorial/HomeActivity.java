package net.simplifiedcoding.firebaseauthtutorial;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }


    public void btnShowListImage_Click(View v) {
        Intent i = new Intent(HomeActivity.this, ImageListActivity.class);
        startActivity(i);
    }




}
