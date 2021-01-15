package com.example.myapplication.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.viewmodel.FileProcessor;

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
        fileContent.setText(FileProcessor.loadFileContent(getString(R.string.log_file)));
    }
}
