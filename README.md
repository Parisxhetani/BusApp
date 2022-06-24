# MyBus

MyBus is a ticket booking mobile app for the South and North Albania Bus Terminal in Tirana.

## Installation

Clone it on your Android Studio ->File -> New -> Project From Version Control

```
https://github.com/Parisxhetani/MyBus.git
```
Then run the app after it completes loading.

## `Register User`
```java
 private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextFullName.setError("Full name is required!");
            editTextFullName.requestFocus();
            return;
        }
        if(age.isEmpty()){
            editTextAge.setError("Age is required!");
            editTextAge.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

       progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(fullName, age, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }else{
                                        Toast.makeText(RegisterUser.this, "Failed to register! Try Again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterUser.this, "Failed to register!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
```
## `User Login `
```java
private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email!");
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextPassword.setError("Password must have more than 6 characters!");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    //redirect to user profile
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
```
## `Reset User Password`
```java
 private void resetPassword() {
        String email = emailEditText.getText().toString().trim();

        if(email.isEmpty()){
             emailEditText.setError("Email is required!");
             emailEditText.requestFocus();
             return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please provide valid email!");
            emailEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }else{
                    Toast.makeText(ForgotPassword.this, "Try Again! Something wrong happened!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
```

## `Usage/Purpose`

```java
import MyBus

// returns 'The time of departure for a trip to Durres.'
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

// returns 'The destination of the Trip and the price for each trip.'

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
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
No license.
