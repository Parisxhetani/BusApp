package com.example.mobileapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.os.Handler;
import org.w3c.dom.Text;

import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {
    private Button timeButton;
    private int hour, minute;
    private int price;
    private FirebaseUser user;
    private DatabaseReference reference;
    private Button purchase;
    private ProgressBar progressBar;

    private String userID;

    private Button logout;
    private String cityName;
    private String cityTime;

    RadioGroup rgQytetet;

    RadioGroup rgDurres;
    RadioGroup rgVlore;
    RadioGroup rgLac;
    RadioGroup rgShkoder;
    RadioGroup rgSarande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        purchase = (Button) findViewById(R.id.purchaseButton);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Profile1.class);

                intent.putExtra("qyteti", cityName);
                intent.putExtra("ora", cityTime);
                intent.putExtra("cmimi", price);
                startActivity(intent);
            }
        });


        logout = (Button) findViewById(R.id.signOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String fullName = userProfile.fullName;
                    greetingTextView.setText("Welcome");
                    fullNameTextView.setText(fullName + "!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        rgQytetet = findViewById(R.id.qytetet);
        rgQytetet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                findRadioButton(checkedId);

            }
        });
        rgDurres = findViewById(R.id.oraretDurres);
        rgDurres.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedIdDurres) {
                findRadioButtonDurres(checkedIdDurres);

            }
        });
    rgVlore = findViewById(R.id.oraretVlore);
        rgVlore.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedIdVlore) {
                findRadioButtonVlore(checkedIdVlore);

            }
        });
    rgLac = findViewById(R.id.oraretLac);
        rgLac.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedIdLac) {
                findRadioButtonLac(checkedIdLac);

            }
        });
        rgShkoder = findViewById(R.id.oraretShkoder);
        rgShkoder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedIdShkoder) {
                findRadioButtonShkoder(checkedIdShkoder);

            }
        });
        rgSarande = findViewById(R.id.oraretSarande);
        rgSarande.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedIdSarande) {
                findRadioButtonSarande(checkedIdSarande);

            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();


    }


    private void findRadioButton(int checkedId) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
        RadioGroup oraretDurres = (RadioGroup) findViewById(R.id.oraretDurres);
        RadioGroup oraretLac = (RadioGroup) findViewById(R.id.oraretLac);
        RadioGroup oraretShkoder = (RadioGroup) findViewById(R.id.oraretShkoder);
        RadioGroup oraretVlore = (RadioGroup) findViewById(R.id.oraretVlore);
        RadioGroup oraretSarande = (RadioGroup) findViewById(R.id.oraretSarande);
        switch (checkedId) {
            case R.id.radio_durres:

                price = 150;
                cityName = "Durres";
                oraretSarande.setVisibility(View.GONE);
                oraretShkoder.setVisibility(View.GONE);
                oraretVlore.setVisibility(View.GONE);
                oraretLac.setVisibility(View.GONE);
                oraretDurres.setVisibility(View.VISIBLE);

                break;
            case R.id.radio_vlore:
                price = 250;
                cityName = "Vlore";
                oraretSarande.setVisibility(View.GONE);
                oraretShkoder.setVisibility(View.GONE);
                oraretVlore.setVisibility(View.VISIBLE);
                oraretLac.setVisibility(View.GONE);
                oraretDurres.setVisibility(View.GONE);

                break;
            case R.id.radio_lac:
                price = 200;
                cityName = "Lac";
                oraretSarande.setVisibility(View.GONE);
                oraretShkoder.setVisibility(View.GONE);
                oraretVlore.setVisibility(View.GONE);
                oraretLac.setVisibility(View.VISIBLE);
                oraretDurres.setVisibility(View.GONE);

                break;
            case R.id.radio_shkoder:
                price = 350;
                cityName = "Shkoder";
                oraretSarande.setVisibility(View.GONE);
                oraretShkoder.setVisibility(View.VISIBLE);
                oraretVlore.setVisibility(View.GONE);
                oraretLac.setVisibility(View.GONE);
                oraretDurres.setVisibility(View.GONE);

                break;
            case R.id.radio_sarande:
                price = 950;
                cityName = "Sarande";
                oraretSarande.setVisibility(View.VISIBLE);
                oraretShkoder.setVisibility(View.GONE);
                oraretVlore.setVisibility(View.GONE);
                oraretLac.setVisibility(View.GONE);
                oraretDurres.setVisibility(View.GONE);

                break;
        }
        }
            }, 3000);

        }

    private void findRadioButtonVlore(int checkedIdVlore) {
        switch (checkedIdVlore) {
            case R.id.vloreOrar1:
            cityTime = "5:30";
                break;
            case R.id.vloreOrar2:
                cityTime = "7:00";
                break;
            case R.id.vloreOrar3:
                cityTime = "10:30";
                break;
            case R.id.vloreOrar4:
                cityTime = "13:00";
                break;

            case R.id.vloreOrar5:
                cityTime = "15:30";
                break;
            case R.id.vloreOrar6:
                cityTime = "17:30";
                break;
        }
    }
    private void findRadioButtonDurres(int checkedIdDurres) {
        switch (checkedIdDurres) {
            case R.id.durresOrar1:
                cityTime = "6:30";
                break;
            case R.id.durresOrar2:
                cityTime = "8:00";
                break;
            case R.id.durresOrar3:
                cityTime = "11:30";
                break;
            case R.id.durresOrar4:
                cityTime = "14:00";
                break;

            case R.id.durresOrar5:
                cityTime = "16:30";
                break;
            case R.id.durresOrar6:
                cityTime = "18:00";
                break;
        }
    }
    private void findRadioButtonShkoder(int checkedIdShkoder) {
        switch (checkedIdShkoder) {
            case R.id.shkoderOrar1:
                cityTime = "5:00";
                break;
            case R.id.shkoderOrar2:
                cityTime = "7:30";
                break;
            case R.id.shkoderOrar3:
                cityTime = "9:30";
                break;
            case R.id.shkoderOrar4:
                cityTime = "12:00";
                break;

            case R.id.shkoderOrar5:
                cityTime = "14:30";
                break;
            case R.id.shkoderOrar6:
                cityTime = "17:00";
                break;
        }
    }
    private void findRadioButtonLac(int checkedIdLac) {
        switch (checkedIdLac) {
            case R.id.lacOrar1:
                cityTime = "6:30";
                break;
            case R.id.lacOrar2:
                cityTime = "8:00";
                break;
            case R.id.lacOrar3:
                cityTime = "11:30";
                break;
            case R.id.lacOrar4:
                cityTime = "13:30";
                break;

            case R.id.lacOrar5:
                cityTime = "16:00";
                break;
            case R.id.lacOrar6:
                cityTime = "18:30";
                break;
        }
    }
    private void findRadioButtonSarande(int checkedIdSarande) {
        switch (checkedIdSarande) {
            case R.id.sarandeOrar1:
                cityTime = "6:30";
                break;
            case R.id.sarandeOrar2:
                cityTime = "8:30";
                break;
            case R.id.sarandeOrar3:
                cityTime = "11:00";
                break;
            case R.id.sarandeOrar4:
                cityTime = "14:00";
                break;

            case R.id.sarandeOrar5:
                cityTime = "17:30";
                break;
            case R.id.sarandeOrar6:
                cityTime = "20:00";
                break;
        }
    }
    /*public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }*/ /*
    private void findRadioButton (int checkedId){
        ImageView imageViewDurres=(ImageView)findViewById(R.id.imageViewDurres);
        ImageView imageViewLac=(ImageView)findViewById(R.id.imageViewLac);
        ImageView imageViewVlore=(ImageView)findViewById(R.id.imageViewVlore);
        ImageView imageViewShkoder=(ImageView)findViewById(R.id.imageViewShkoder);
        ImageView imageViewSarande=(ImageView)findViewById(R.id.imageViewSarande);
        switch (checkedId){
            case R.id.radio_durres:
                price = 150;
                imageViewSarande.setVisibility(View.GONE);
                imageViewShkoder.setVisibility(View.GONE);
                imageViewVlore.setVisibility(View.GONE);
                imageViewLac.setVisibility(View.GONE);
                imageViewDurres.setVisibility(View.VISIBLE);
                int imageResource = getResources().getIdentifier("@drawable/durres", null, this.getPackageName());
                imageViewDurres.setImageResource(imageResource);
                break;

            case R.id.radio_lac:
                price = 200;
                imageViewDurres.setVisibility(View.GONE);
                imageViewSarande.setVisibility(View.GONE);
                imageViewShkoder.setVisibility(View.GONE);
                imageViewVlore.setVisibility(View.GONE);
                imageViewLac.setVisibility(View.VISIBLE);

                int imageResource1 = getResources().getIdentifier("@drawable/lac", null, this.getPackageName());
                imageViewLac.setImageResource(imageResource1);
                break;

            case R.id.radio_vlore:
                price = 500;
                imageViewLac.setVisibility(View.GONE);
                imageViewDurres.setVisibility(View.GONE);
                imageViewSarande.setVisibility(View.GONE);
                imageViewShkoder.setVisibility(View.GONE);
                imageViewVlore.setVisibility(View.VISIBLE);
                int imageResource2 = getResources().getIdentifier("@drawable/vlore", null, this.getPackageName());
                imageViewVlore.setImageResource(imageResource2);
                break;

            case R.id.radio_shkoder:
                price = 500;
                imageViewSarande.setVisibility(View.GONE);
                imageViewVlore.setVisibility(View.GONE);
                imageViewLac.setVisibility(View.GONE);
                imageViewDurres.setVisibility(View.GONE);
                imageViewShkoder.setVisibility(View.VISIBLE);
                int imageResource3 = getResources().getIdentifier("@drawable/shkoder", null, this.getPackageName());
                imageViewShkoder.setImageResource(imageResource3);
                break;

            case R.id.radio_sarande:
                price = 2000;
                imageViewVlore.setVisibility(View.GONE);
                imageViewLac.setVisibility(View.GONE);
                imageViewDurres.setVisibility(View.GONE);
                imageViewShkoder.setVisibility(View.GONE);
                imageViewSarande.setVisibility(View.VISIBLE);
                int imageResource4 = getResources().getIdentifier("@drawable/sarande", null, this.getPackageName());
                imageViewSarande.setImageResource(imageResource4);
                break;
        }
    }*/
    }
