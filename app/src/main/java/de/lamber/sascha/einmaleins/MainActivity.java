package de.lamber.sascha.einmaleins;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView challengeText;
    private ImageView resultImage;
    private Button[] options;

    private int selectedRow = 0;
    private int correctResult = 0;
    private int correctResultAt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        options = new Button[3];
        options[0] = (Button) findViewById(R.id.option1);
        options[1] = (Button) findViewById(R.id.option2);
        options[2] = (Button) findViewById(R.id.option3);

        resultImage = (ImageView) findViewById(R.id.resultImage);
        challengeText = (TextView) findViewById(R.id.challengeText);

        selectedRow = (int)getIntent().getSerializableExtra("selectedRow");

        Log.d("EinMalEins", String.valueOf(selectedRow));

        setTitle(String.format("Die %der Reihe", selectedRow));
        nextChallenge();
    }

    public void nextChallenge(){

        int operand = (int)(Math.random() * 10) +1;
        int randomizeChallengeText = (int)(Math.random() * 6) +1;
        correctResult = selectedRow * operand;

        if (randomizeChallengeText == 1 || randomizeChallengeText == 3){
            challengeText.setText(String.format("%d * %d = ", operand, selectedRow));
        }else {
            challengeText.setText(String.format("%d * %d = ", selectedRow, operand));
        }


        int ruse[] = new int[2];
        ruse[0] = generateRuse(correctResult);
        ruse[1] = generateRuse(correctResult);

        while (ruse[0] == ruse[1]){
            ruse[1] = generateRuse(correctResult);
        }

        // between 0 - 2
        correctResultAt = (int)(Math.random() * 3);

        int currentRuse = 0;

        for (int i = 0; i < options.length; i++){

            if (i == correctResultAt){
                options[i].setText(String.valueOf(correctResult));
            }else {
                options[i].setText(String.valueOf(ruse[currentRuse++]));
            }
        }

    }

    private int generateRuse(int correctResult){

        int tempRuse;

        int randomFactor = (int)(Math.random() * 6) +1;

        do {
            if (randomFactor == 2 || randomFactor == 4){
                tempRuse = correctResult - (int) (Math.random() * randomFactor) + 1;
            }else {
                tempRuse = correctResult + (int) (Math.random() * randomFactor) + 1;
            }

        }while (tempRuse == correctResult);

        return tempRuse;
    }

    public void answerChallenge(View view){

        int position = Integer.parseInt(view.getTag().toString());

        if (position == correctResultAt){
            Toast toast = Toast.makeText(this, "Richtig!", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Toast toast = Toast.makeText(this, String.format("Falsch: %s%d", challengeText.getText(), correctResult), Toast.LENGTH_SHORT);
            toast.show();
        }

        nextChallenge();

    }
}
