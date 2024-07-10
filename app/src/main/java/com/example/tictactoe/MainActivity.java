package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int totalMoves,flagReplay=0,x=0,y=0;int[][] winGrid =new int[3][3];
    ConstraintLayout ll;
    TextView status;
    ProgressBar progressBar;
    ImageView o0,o1,o2,o3,o4,o5,o6,o7,o8,x0,x1,x2,x3,x4,x5,x6,x7,x8,tempImage,userInfo,h1,h2,h3,v1,v2,v3,d1,d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInfo=findViewById(R.id.userInfo);

        init();
        reset();

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "OWNER : PRATYUSH", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(){
        ll=findViewById(R.id.ll);
        status=findViewById(R.id.status);
        progressBar=findViewById(R.id.progressBar);

        o0=findViewById(R.id.o0);x0=findViewById(R.id.x0);
        o1=findViewById(R.id.o1);x1=findViewById(R.id.x1);
        o2=findViewById(R.id.o2);x2=findViewById(R.id.x2);
        o3=findViewById(R.id.o3);x3=findViewById(R.id.x3);
        o4=findViewById(R.id.o4);x4=findViewById(R.id.x4);
        o5=findViewById(R.id.o5);x5=findViewById(R.id.x5);
        o6=findViewById(R.id.o6);x6=findViewById(R.id.x6);
        o7=findViewById(R.id.o7);x7=findViewById(R.id.x7);
        o8=findViewById(R.id.o8);x8=findViewById(R.id.x8);

        h1=findViewById(R.id.h1);v1=findViewById(R.id.v1);
        h2=findViewById(R.id.h2);v2=findViewById(R.id.v2);
        h3=findViewById(R.id.h3);v3=findViewById(R.id.v3);
        d1=findViewById(R.id.d1);d2=findViewById(R.id.d2);
    }

    private void reset(){
        flagReplay=0;
        ll.setBackgroundColor(getResources().getColor(R.color.white));
        totalMoves=0;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                winGrid[i][j]=-1;
            }
        }

        status.setText("status\nX turn to play");

        progressBar.setVisibility(View.INVISIBLE);

        o0.setAlpha(0f);x0.setAlpha(0f);
        o1.setAlpha(0f);x1.setAlpha(0f);
        o2.setAlpha(0f);x2.setAlpha(0f);
        o3.setAlpha(0f);x3.setAlpha(0f);
        o4.setAlpha(0f);x4.setAlpha(0f);
        o5.setAlpha(0f);x5.setAlpha(0f);
        o6.setAlpha(0f);x6.setAlpha(0f);
        o7.setAlpha(0f);x7.setAlpha(0f);
        o8.setAlpha(0f);x8.setAlpha(0f);

        h1.setAlpha(0f);v1.setAlpha(0f);
        h2.setAlpha(0f);v2.setAlpha(0f);
        h3.setAlpha(0f);v3.setAlpha(0f);
        d1.setAlpha(0f);d2.setAlpha(0f);
    }

    private void check(){
        if(getWin()){
            if((totalMoves-1)%2==0){
                status.setText("Status\nX WON\nTap to Replay");
            }else{
                status.setText("Status\nO WON\nTap to Replay");
            }

            ll.setBackgroundColor(getResources().getColor(R.color.green));
            finish();
        }else if(totalMoves>=9){
            status.setText("Status\nMatch Draw\nTap to Replay");

            ll.setBackgroundColor(getResources().getColor(R.color.red));
            finish();
        }else{
            if((totalMoves)%2==0){
                status.setText("Status\nX turn to play\nTap to Restart");
            }else{
                status.setText("Status\nO turn to play\nTap to Restart");
            }

        }
    }

    private boolean getWin(){
        ImageView winImage;
        if(winGrid[0][0]==winGrid[0][1] && winGrid[0][1]==winGrid[0][2] && winGrid[0][0]!=-1){
            winImage=h1;
        }else if((winGrid[1][0]==winGrid[1][1] && winGrid[1][1]==winGrid[1][2]) && winGrid[1][0]!=-1){
            winImage=h2;
        }else if((winGrid[2][0]==winGrid[2][1] && winGrid[2][1]==winGrid[2][2]) && winGrid[2][0]!=-1){
            winImage=h3;
        }else if((winGrid[0][0]==winGrid[1][0] && winGrid[1][0]==winGrid[2][0]) && winGrid[0][0]!=-1){
            winImage=v1;
        }else if((winGrid[0][1]==winGrid[1][1] && winGrid[1][1]==winGrid[2][1]) && winGrid[0][1]!=-1){
            winImage=v2;
        }else if((winGrid[0][2]==winGrid[1][2] && winGrid[1][2]==winGrid[2][2]) && winGrid[0][2]!=-1){
            winImage=v3;
        }else if((winGrid[0][0]==winGrid[1][1] && winGrid[1][1]==winGrid[2][2]) && winGrid[0][0]!=-1){
            winImage=d1;
        }else if((winGrid[0][2]==winGrid[1][1] && winGrid[1][1]==winGrid[2][0]) && winGrid[0][2]!=-1 ){
            winImage=d2;
        }else{
            return false;
        }

        Animation fadeIn= AnimationUtils.loadAnimation(this,R.anim.move);
        winImage.startAnimation(fadeIn);
        winImage.setAlpha(1f);
        return true;
    }

    public void finish(){
        flagReplay=1;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                winGrid[i][j]=2;
            }
        }

        progressBar.setVisibility(View.INVISIBLE);
    }

    public void forAllClicks(View view){
        progressBar.setVisibility(View.VISIBLE);
        int currId=view.getId();

        updateXYTempImage(currId);

        if(winGrid[x][y]!=-1){
            if(flagReplay==1){
                status.setText("Status\nInvalid Move\nTap to Replay");
            }else{
                status.setText("Status\nInvalid Move");
            }
            return;
        }

        winGrid[x][y]=totalMoves%2;
        Animation fadeIn= AnimationUtils.loadAnimation(this,R.anim.move);
        tempImage.startAnimation(fadeIn);
        tempImage.setAlpha(1f);
        totalMoves++;

        check();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        },500);
    }

    private void updateXYTempImage(int currId){
        if(currId==R.id.x0 || currId==R.id.o0){
            x=0;
            y=0;

            if(totalMoves%2==0){
                tempImage=x0;
            }else{
                tempImage=o0;
            }
        }else if(currId==R.id.x1 || currId==R.id.o1){
            x=0;
            y=1;

            if(totalMoves%2==0){
                tempImage=x1;
            }else{
                tempImage=o1;
            }
        }else if(currId==R.id.x2 || currId==R.id.o2){
            x=0;
            y=2;

            if(totalMoves%2==0){
                tempImage=x2;
            }else{
                tempImage=o2;
            }
        }else if(currId==R.id.x3 || currId==R.id.o3){
            x=1;
            y=0;

            if(totalMoves%2==0){
                tempImage=x3;
            }else{
                tempImage=o3;
            }
        }else if(currId==R.id.x4 || currId==R.id.o4){
            x=1;
            y=1;

            if(totalMoves%2==0){
                tempImage=x4;
            }else{
                tempImage=o4;
            }
        }else if(currId==R.id.x5 || currId==R.id.o5){
            x=1;
            y=2;

            if(totalMoves%2==0){
                tempImage=x5;
            }else{
                tempImage=o5;
            }
        }else if(currId==R.id.x6 || currId==R.id.o6){
            x=2;
            y=0;

            if(totalMoves%2==0){
                tempImage=x6;
            }else{
                tempImage=o6;
            }
        }else if(currId==R.id.x7 || currId==R.id.o7){
            x=2;
            y=1;

            if(totalMoves%2==0){
                tempImage=x7;
            }else{
                tempImage=o7;
            }
        }else if(currId==R.id.x8 || currId==R.id.o8){
            x=2;
            y=2;

            if(totalMoves%2==0){
                tempImage=x8;
            }else{
                tempImage=o8;
            }
        }
    }

}