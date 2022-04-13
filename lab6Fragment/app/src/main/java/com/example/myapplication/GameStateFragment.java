package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
// here we wrote the xml and added it with code
public class GameStateFragment extends Fragment {
    public GameStateFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_state,
                container, false);
    }
    public void onStart( ) {
        super.onStart( );
        //I get the fragment view to get the text inside the fragment
        View fragmentView = getView();
        TextView gameStateTV = ( TextView ) fragmentView.findViewById( R.id.state_of_game );
        // now we get the activity of the fragment to use the model
        MainActivity fragmentActivity = ( MainActivity ) getActivity();
        gameStateTV.setText( fragmentActivity.getGame().currentIncompleteWord()); }
}