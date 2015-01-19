package com.thinkful.zcarter.objectexplorer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

// This class handles output of log to the emulator screen
class Screen {
    // static fields
    static TextView textView;
    static String text = new String();

    public static void outputToScreen() {
        textView.setText(text);
    }

    public static void log(String textToLog) {
        text += textToLog + "\n";
    }
}

abstract class Ball extends Observable {
    public abstract void roll();
}

class Football extends Ball {

    @Override
    public void roll() {
        Screen.log("This football is rolling");
        this.setChanged();
        this.notifyObservers();
    }
}

class Umpire implements Observer {

    public void update(Observable observable, Object data) {
        Screen.log("The ball has been changed.");

    }
}

//-------------------------------------------------------------------
// This space is for students to define classes in Thinkful Unit 2


//-------------------------------------------------------------------


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Screen.textView = (TextView) findViewById(R.id.textView);
        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Screen.outputToScreen();
            }
        });
        this.userSetup();
        Screen.outputToScreen();
    }

    public void userSetup() {
        // Students experiment with their classes here by instantiating their objects
        // and calling methods on those objects
        // example using the Football class:
        Football football = new Football();
        Umpire umpire = new Umpire();
        football.addObserver(umpire);
        football.roll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
