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

import org.w3c.dom.Text;

import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {
    private Button timeButton;
    private int hour, minute;
    private int price;
    private FirebaseUser user;
    private DatabaseReference reference;
    private Button purchase;

    private String userID;

    private Button logout;

    RadioGroup rgQytetet;
    RadioButton rbQytetet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        purchase = (Button) findViewById(R.id.purchaseButton);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Toast.makeText(ProfileActivity.this, "You purchased a ticket for " + price + "leke ne oren " +hour + ":" + minute , Toast.LENGTH_LONG).show();
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
                if(userProfile != null){
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
        timeButton = findViewById(R.id.timeButton);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
    }
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
    }

    public void popTimePicker(View view) {
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
    }
}