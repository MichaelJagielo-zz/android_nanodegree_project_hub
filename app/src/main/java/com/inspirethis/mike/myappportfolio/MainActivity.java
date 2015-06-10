package com.inspirethis.mike.myappportfolio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        addListenerOnProjectButtons();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
        return false;
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

    private void addListenerOnProjectButtons() {
        Button projectOne = (Button) findViewById(R.id.btn_project_one);
        projectOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), getResources().getString(R.string.project_one_message), Toast.LENGTH_SHORT).show();
                Intent streamer = new Intent(MainActivity.this, SpotifyStreamerActivity.class);
                MainActivity.this.startActivity(streamer);
                finish();
            }
        });

        Button projectTwo = (Button) findViewById(R.id.btn_project_two);
        projectTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.project_two_message), Toast.LENGTH_SHORT).show();
            }
        });

        Button projectThree = (Button) findViewById(R.id.btn_project_three);
        projectThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.project_three_message), Toast.LENGTH_SHORT).show();
            }
        });

        Button projectFour = (Button) findViewById(R.id.btn_project_four);
        projectFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.project_four_message), Toast.LENGTH_SHORT).show();
            }
        });

        Button projectFive = (Button) findViewById(R.id.btn_project_five);
        projectFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.project_five_message), Toast.LENGTH_SHORT).show();
            }
        });

        Button projectSix = (Button) findViewById(R.id.btn_project_six);
        projectSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.project_six_message), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
