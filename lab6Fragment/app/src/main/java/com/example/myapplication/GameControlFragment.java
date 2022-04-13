package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

// this is all done in xml.
// you cannot find a view from the this fragment class it makes sense we have not linked a layout with this fragment.
public class GameControlFragment extends Fragment {
    public GameControlFragment( ) {
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState ) {
        return inflater.inflate( R.layout.fragment_game_control,
                container, false );
    }


}
