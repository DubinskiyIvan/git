package com.example.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class fileActivity extends AppCompatActivity {
    TextView fileContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file);

        Button fileButton = findViewById(R.id.show_button);
        fileButton.setOnClickListener(v -> loadFileContents());
        fileContent = findViewById(R.id.text_view);
    }

    private void loadFileContents() {
        String rezult = "";
        StringBuilder stringBuilder = new StringBuilder();
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(downloadsDir, getString(R.string.log_file));
        try {
            BufferedReader reader = new BufferedReader(new FileReader(myFile));

            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        rezult = stringBuilder.toString();
        fileContent.setText(rezult);
    }
}
