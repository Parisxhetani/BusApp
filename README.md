```
//Might add some proper features in the future
```
# MyBus

MyBus is a ticket booking mobile app for the South and North Albania Bus Terminal.

## Installation

Clone it on your Android Studio ->File -> New -> Project From Version Control

```bash
https://github.com/Parisxhetani/MyBus.git
```
Then run the app after it completes loading.

## Usage/Example

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
