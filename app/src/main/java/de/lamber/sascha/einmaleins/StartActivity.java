package de.lamber.sascha.einmaleins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void rowSelected(View view){

        int selectedRow = Integer.parseInt(view.getTag().toString());

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("selectedRow", selectedRow);
        startActivity(intent);

    }
}
