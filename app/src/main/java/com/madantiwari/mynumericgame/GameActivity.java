package com.madantiwari.mynumericgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView tvScore, tvWL, tvResult;
    private Button btnNum1, btnNum2, btnRestart;
    private int score = 0, points_won = 0, points_loss = 0, total_clicks = 0;
    private int rand_int1, rand_int2;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvScore = findViewById(R.id.tvScore);
        tvWL = findViewById(R.id.tvWL);
        tvResult = findViewById(R.id.tvResult);

        btnNum1 = findViewById(R.id.btnNum1);
        btnNum2 = findViewById(R.id.btnNum2);
        btnRestart = findViewById(R.id.btnRestart);

        randomGen();
        assignValue();

        tvScore.setText(Integer.toString(score));

        btnNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rand_int1 > rand_int2){
                    score = score + 1;
                    points_won = points_won + 1;
                    total_clicks = total_clicks + 1;
                    tvScore.setText(Integer.toString(score));
                    randomGen();
                    assignValue();
                    check(points_won, points_loss, total_clicks);
                    System.out.println(points_loss+":loss "+points_won+":won"+" greater value");
                }

                else if (rand_int1 < rand_int2){
                    score = score - 1;
                    points_loss = points_loss - 1;
                    total_clicks = total_clicks + 1;

                    tvScore.setText(Integer.toString(score));

                    randomGen();
                    assignValue();
                    check(points_won, points_loss, total_clicks);
                    System.out.println(points_loss+":loss "+points_won+":won"+"lesser value");
                }
            }
        });


        btnNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rand_int2 > rand_int1){
                    score = score + 1;
                    points_won = points_won + 1;
                    total_clicks = total_clicks + 1;

                    tvScore.setText(Integer.toString(score));

                    randomGen();
                    assignValue();
                    check(points_won, points_loss, total_clicks);
                    System.out.println(points_loss+":loss "+points_won+":won"+"greater value");


                }
                else if (rand_int2 < rand_int1){
                    score = score - 1;
                    points_loss = points_loss - 1;
                    total_clicks = total_clicks + 1;

                    tvScore.setText(Integer.toString(score));

                    randomGen();
                    assignValue();
                    check(points_won, points_loss, total_clicks);
                    System.out.println(points_loss+":loss "+points_won+":won"+"lesser value");

                }


            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomGen();
                assignValue();
                btnNum1.setEnabled(true);
                btnNum2.setEnabled(true);
                points_won = 0;
                points_loss = 0;
                score = 0;
                total_clicks = 0;

                tvWL.setText("");
                tvScore.setText("");
                tvResult.setText("");

            }
        });
    }


    private void randomGen(){

        rand_int1 = rand.nextInt(101);
        rand_int2 = rand.nextInt(101);

        if(rand_int1 == rand_int2){
            randomGen();
        }
    }

    private void assignValue(){
        btnNum1.setText(Integer.toString(rand_int1));
        btnNum2.setText(Integer.toString(rand_int2));
    }

    private void cancel(){
        btnNum1.setEnabled(false);
        btnNum2.setEnabled(false);

    }


    private void check(int points_won, int points_loss, int total_clicks){
        if (score == 10){
            tvWL.setText("Congrats, You won!!!");
            tvResult.setText("Win= " + points_won + "Loss= " + points_loss + "Total Clicks= " + total_clicks);
            cancel();

        }

        else if(score == -10){
            tvWL.setText("Sorry, You Lost!!!");
            tvResult.setText("Win= " + points_won + "Loss= " + points_loss + "Total Clicked= " + total_clicks);
            cancel();
        }
    }
    }