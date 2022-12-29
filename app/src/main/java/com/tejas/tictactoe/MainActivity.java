package com.tejas.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    // 0 - O
    // 1 - X
    // 2 - Blank
    int activePlayer = 0;
    int []gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6},
                               {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void player_tap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameActive){
            gameReset(view);
        }

        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.o);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to Play");
                status.setTextColor(Color.rgb(255,97,95));
            } else {
                img.setImageResource(R.drawable.x);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to Play");
                status.setTextColor(Color.rgb(62,197,244));
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
//        Check If Any Player has won
        for (int [] winningPosition:winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]]!=2){
                // Somebody has won! - Find out who won the match

                TextView status = findViewById(R.id.status);
                String winner_str = "";
                gameActive = false;

                if (gameState[winningPosition[0]] == 0){
                    winner_str = "O has won the Match! Well Played!";

                }
                else if (gameState[winningPosition[0]] == 1){
                    winner_str = "X has won the Match! Well Played!";
                    status.setText(winner_str);
                    status.setTextColor(Color.rgb(0, 255, 0));
                    Toast.makeText(this, winner_str, Toast.LENGTH_SHORT).show();
                }

//                Update the Status Bar for Winner Announcement
                status.setText(winner_str);
                status.setTextColor(Color.rgb(0, 255, 0));
                Toast.makeText(this, winner_str, Toast.LENGTH_SHORT).show();
            } else{
//                Match Tied
                int i = 0;
                for (int gameStateEle:gameState){
                    if (gameStateEle != 2) {
                        i++;
                    }
                    if (i==9) {
                        TextView status = findViewById(R.id.status);
                        String winner_str = "Match Tie! Both Well Played!";
                        status.setText(winner_str);
                        status.setTextColor(Color.rgb(255, 165, 0));
                        Toast.makeText(this, winner_str, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        TextView status = findViewById(R.id.status);
        status.setText("Tap to Play");
        status.setTextColor(Color.rgb(0,0,0));
        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(R.drawable.white_block);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(R.drawable.white_block);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(R.drawable.white_block);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(R.drawable.white_block);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(R.drawable.white_block);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(R.drawable.white_block);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(R.drawable.white_block);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(R.drawable.white_block);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(R.drawable.white_block);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}