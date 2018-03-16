package com.rajramchandani.braintrainernew;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView result;
    TextView number;
    TextView points;
    TextView time;
    RelativeLayout game;
    RelativeLayout gameover;
    RelativeLayout start;
    TextView scoring;
    int a;
    int b;
    int options;
    int correctlocation;
    int incorrect;
    int increase=0;
    int total=0;
    CountDownTimer countDownTimer;
    ArrayList<Integer> arr=new ArrayList<Integer>();

    void playagain(View view)
    {
        arr.clear();
        increase=0;
        total=0;
        result.setVisibility(View.INVISIBLE);
        points.setText(increase+"/"+total);
        game.setVisibility(View.VISIBLE);
        gameover.setVisibility(View.INVISIBLE);
        generateQuestion();
        timer();
    }
    void check(View view)
    {
        arr.clear();
        if(view.getTag().equals(Integer.toString(correctlocation)))
        {
            result.setVisibility(View.VISIBLE);
            result.setText("Correct");
            increase++;
        }
        else
        {
            result.setVisibility(View.VISIBLE);
            result.setText("Incorrect");
        }
        total++;

        points.setText(increase+"/"+total);

        generateQuestion();
    }

    void generateQuestion()
    {
        Random rand=new Random();
        a=rand.nextInt(21);
        b=rand.nextInt(21);
        number.setText(a+"+"+b);
        correctlocation=rand.nextInt(3);
        for(int i=0;i<4;i++)
        {
            incorrect=rand.nextInt(41);
            while(incorrect==a+b)
            {
                incorrect=rand.nextInt(41);
            }
            while(arr.contains(incorrect))
            {
                incorrect=rand.nextInt(41);
            }
            arr.add(incorrect);
        }
        arr.set(correctlocation,a+b);
        button0.setText(Integer.toString(arr.get(0)));
        button1.setText(Integer.toString(arr.get(1)));
        button2.setText(Integer.toString(arr.get(2)));
        button3.setText(Integer.toString(arr.get(3)));

    }

      public void timer()
      {
          countDownTimer = new CountDownTimer(30000, 1000) {
              @Override
              public void onTick(long millisUntilFinished) {

                  time.setText(String.valueOf(millisUntilFinished / 1000));
              }

              @Override
              public void onFinish() {
                  game.setVisibility(View.INVISIBLE);
                  gameover.setVisibility(View.VISIBLE);
                  scoring.setText("Your Score is " + increase + "/" + total);

              }
          }.start();
      }

      public void go(View view)
      {
          start.setVisibility(View.INVISIBLE);
          game.setVisibility(View.VISIBLE);
          generateQuestion();
          timer();
      }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        number=(TextView)findViewById(R.id.number);
        result=(TextView)findViewById(R.id.result);
        points=(TextView)findViewById(R.id.points);
        time=(TextView)findViewById(R.id.time);
        game=(RelativeLayout)findViewById(R.id.game);
        gameover=(RelativeLayout)findViewById(R.id.gameover);
        scoring=(TextView)findViewById(R.id.scoring);
        start=(RelativeLayout)findViewById(R.id.start);


    }
}
