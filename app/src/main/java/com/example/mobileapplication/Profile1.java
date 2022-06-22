package com.example.mobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Profile1 extends AppCompatActivity {
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
        ImageView img = findViewById(R.id.imageViewLac);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String qyteti = extras.getString("qyteti");
            String ora = extras.getString("ora");
            Integer cmimi = extras.getInt("cmimi");
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText("Detajet e biletes: |Qyteti: " +qyteti +" |Ora: "+ora + " |Cmimi: "+ cmimi.toString() + " Leke");
        }

        if(extras.getString("qyteti").equals("Durres")){
        img.setImageResource(R.drawable.durres);
        }
        else if(extras.getString("qyteti").equals("Lac")){
            img.setImageResource(R.drawable.lac);
        }
        else if(extras.getString("qyteti").equals("Shkoder")){
            img.setImageResource(R.drawable.shkoder);
        }
        else if(extras.getString("qyteti").equals("Vlore")){
            img.setImageResource(R.drawable.vlore);
        }
        else if(extras.getString("qyteti").equals("Sarande")){
            img.setImageResource(R.drawable.sarande);
        }
        logout = (Button) findViewById(R.id.signOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile1.this, MainActivity.class));
            }
        });
}
}