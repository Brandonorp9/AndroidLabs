package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
public class ButtonGridAndTextView extends GridLayout{
    private int side;
    private Button[][] buttons;
    private TextView status;

    public ButtonGridAndTextView(Context context, int width, int newSide,
                                 View.OnClickListener listener) {
        super(context);
        side= newSide;
        buttons= new Button[newSide][newSide];
        // Set # of rows and columns of this GridLayout
        setColumnCount( side );
        setRowCount( side + 1 );
        // Create the buttons and add them to this GridLayout
        for( int row = 0; row < TicTacToe.SIDE; row++ ) {
            for( int col = 0; col < TicTacToe.SIDE; col++ ) {
                buttons[row][col] = new Button(context);
                // we set a size to the text
                buttons[row][col].setTextSize((float) (width*0.2)); // why does it now work when it is just w.
                // we add the listener to each button
                buttons[row][col].setOnClickListener(listener);
                addView(buttons[row][col],width,width);
            }
        }
        // set up layout parameters of 4th row of gridLayout
        status = new TextView(context);
        GridLayout.Spec rowSpec = GridLayout.spec(TicTacToe.SIDE,1);
        GridLayout.Spec columnSpec = GridLayout.spec(0,TicTacToe.SIDE);
        GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams(rowSpec,columnSpec);
        status.setLayoutParams(lpStatus);
        status.setWidth( TicTacToe.SIDE * width );
        status.setHeight( width);
        status.setGravity( Gravity.CENTER );
        status.setBackgroundColor( Color.GREEN );
        status.setTextSize( ( int ) ( width * .15 ) );
        status.setText( "PLay" );
        addView(status);

    }

    public void setStatusText( String text ) {
        status.setText( text );
    }
    public void setStatusBackgroundColor( int color ) {
        status.setBackgroundColor( color );
    }
    public void setButtonText( int row, int column, String text ) {
        buttons[row][column].setText( text );
    }
    public boolean isButton( Button b, int row, int column ) {
        return ( b == buttons[row][column] );
    }
    public void resetButtons( ) {
        for( int row = 0; row < side; row++ )
            for( int col = 0; col < side; col++ )
                buttons[row][col].setText( "" );
    }
    public void enableButtons( boolean enabled ) {
        for( int row = 0; row < side; row++ )
            for( int col = 0; col < side; col++ )
                buttons[row][col].setEnabled( enabled );
    }
}
