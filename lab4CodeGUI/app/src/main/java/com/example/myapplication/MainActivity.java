package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TicTacToe tttGame;
    ButtonGridAndTextView tttView;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        tttGame=new TicTacToe();
        // setContentView( R.layout.activity_main );
        // Get width of the screen
        Point size = new Point( );
        //YOUR CODE – Retrieve the width of the screen
        getWindowManager().getDefaultDisplay().getSize(size);
        //YOUR CODE – Assign one third of the width of the screen to a variable w
        int w= size.x/TicTacToe.SIDE;
        tttView = new ButtonGridAndTextView(this,w,TicTacToe.SIDE,new ButtonHandler());
        setContentView(tttView);
    }


    //Implement the ButtonHandler event
    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v) {
            Button view = (Button) v;
            Log.w( "MainActivity", "Inside onClick, v = " + v );
            for( int row = 0; row < TicTacToe.SIDE; row++ ){
                for( int column = 0; column < TicTacToe.SIDE; column++ ){
                    if(tttView.isButton(view,row,column)){
                        int play = tttGame.play(row,column);
                        if(play==1)
                            tttView.setButtonText(row,column,"X");
                        else if(play==2)
                            tttView.setButtonText(row,column,"O");
                        if(tttGame.isGameOver()){
                            tttView.setStatusBackgroundColor(Color.GREEN);
                            tttView.enableButtons(false);
                            tttView.setStatusText(tttGame.result());
                            showNewGameDialog();
                        }


                    }
                }
            }

        }
    }

    public void showNewGameDialog( ) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this );
        alert.setTitle( "This is fun" );
        alert.setMessage( "Play again?" );
        PlayDialog playAgain = new PlayDialog( );
        alert.setPositiveButton( "YES",playAgain );
        alert.setNegativeButton("NO",playAgain );
        alert.show();
    }
    private class PlayDialog implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog , int id) {
            if(id==-1){
                tttGame.resetGame();
                tttView.enableButtons(true);
                tttView.resetButtons();
                tttView.setStatusBackgroundColor(Color.GREEN);
                tttView.setStatusText(tttGame.result());
            }
            else if (id==-2){
                MainActivity.this.finish();
            }
        }
    }


}