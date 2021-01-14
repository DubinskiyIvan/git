package com.example.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FrameLayout fragmentSlot;

    private int state;
    private final int NUMBER_STATE = 1;
    private final int STRING_STATE = 2;
    private ArrayList<historyItem> history;

    public static final String HISTORY_KEY = "history";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        history = new ArrayList<>();

        fragmentSlot = findViewById(R.id.fragment_slot);
        Button switchButton = findViewById(R.id.switch_fragment_button);

        if(fragmentSlot!=null) {
            setNumberFragment();
            switchButton.setOnClickListener(v -> switchFragment());
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }

    private void setNumberFragment(){
        setFragment(numbersFragment.newInstance());
        state = NUMBER_STATE;
    }
    private void setStringFragment(){
        setFragment(stringsFragment.newInstance());
        state = STRING_STATE;
    }

    private void switchFragment(){
        if(state == NUMBER_STATE){
            setStringFragment();
        } else if (state == STRING_STATE) {
            setNumberFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    public void addToHistory(historyItem newItem){
        history.add(newItem);

        databaseManager databaseManager = new databaseManager(this);
        databaseManager.open();
        databaseManager.insert(newItem);
        databaseManager.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.history:
                Intent intent = new Intent(this, historyActivity.class);
                intent.putParcelableArrayListExtra(HISTORY_KEY, history);
                startActivity(intent);
                break;
            case R.id.toast:
                Toast.makeText(getBaseContext(),"Hello User!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.service:
                intent = new Intent(this, serviceActivity.class);
                startActivity(intent);
                break;
            case R.id.browser:
                intent = new Intent(this, browser.class);
                startActivity(intent);
                break;
            case R.id.file_item:
                intent = new Intent(this, fileActivity.class);
                startActivity(intent);
                break;
            case R.id.db_item:
                intent = new Intent(this, dbActivity.class);
                startActivity(intent);
                break;
            case R.id.shared_item:
                intent = new Intent(this, SharedPreferencesActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
