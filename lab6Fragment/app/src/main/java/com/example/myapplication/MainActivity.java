package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Hangman game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (game == null)
        game = new Hangman(Hangman.DEFAULT_GUESSES);
        /* for the first fragment we dont have to add it because we wrote it in the main xml*/
        setContentView(R.layout.activity_main);
        TextView status = (TextView) findViewById(R.id.status);
        status.setText("" + game.getGuessesLeft());
        // we create the fragment
        GameStateFragment fragment = new GameStateFragment();
        /*we get a Fragment Manager which provides functionality to interact
         with fragments within an activity*/
        // we check if there is a place to put the fragment
        // fragment transaction  lets you add,hide,remove and replace a fragment into an activity
        if (getSupportFragmentManager().findFragmentById(R.id.game_state) == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.game_state, fragment, null)//the id tells us where we want to put it
                    .commit();

        }
            GameResultFragment resultFrag = new GameResultFragment();
        if (getSupportFragmentManager().findFragmentById(R.id.game_result) == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.game_result,resultFrag , null)
                    .commit();
        }
        // we add an invisible fragment
        BackgroundFragment backFragment=new BackgroundFragment();
        if (getSupportFragmentManager().findFragmentByTag("background")==null){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(backFragment,"background")
                    .commit();
        }
    }
    // here we code how the fragment communicate to create functionality
    public void play(View view) {
        // we retrieve the user input
        EditText input = (EditText) findViewById(R.id.letter);
        Editable userText = input.getText();
        // we handle the first letter
        if (userText != null && userText.length() > 0) {
            // update number of guesses left
            char letter = userText.charAt(0);
            game.guess(letter);
            TextView status = (TextView) findViewById(R.id.status);
            status.setText("" + game.getGuessesLeft());
            // Now we want to update the fragments therefore we need to get the fragments
            GameStateFragment gsFragment = (GameStateFragment)
                    getSupportFragmentManager().findFragmentById(R.id.game_state);
            // now we get the view inside the fragment
            View gsFragmentView = gsFragment.getView();
            // now we get the views inside the layout
            TextView gameStateTV = (TextView) gsFragmentView.findViewById(R.id.state_of_game);
            gameStateTV.setText(game.currentIncompleteWord());
            // clear EditText
            input.setText("");

            int result = game.gameOver();
            if (result != 0) /* game is over */ {
                GameResultFragment grFragment = (GameResultFragment)
                        getSupportFragmentManager().findFragmentById(R.id.game_result);
                // update TextView in result fragment
                if (result == 1)
                    grFragment.setResult("YOU WON");
                else if (result == -1)
                    grFragment.setResult("YOU LOST");
                // delete hint in EditText
                input.setHint("");
            }
            if(game.getGuessesLeft()==1){
                BackgroundFragment background = (BackgroundFragment)
                        getSupportFragmentManager().findFragmentByTag("background");
                GameResultFragment grFragment = (GameResultFragment)
                        getSupportFragmentManager().findFragmentById(R.id.game_result);
                //retrieve warning and display it
                grFragment.setResult(background.warning());

            }
        }
    }

    public Hangman getGame()
    { return game;}


}