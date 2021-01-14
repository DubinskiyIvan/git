package com.example.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class stringsFragment extends Fragment {

    TextView resultTextLabel;
    EditText wordEdit;

    public stringsFragment() {

    }


    public static stringsFragment newInstance() {
        return new stringsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_string, container, false);

        resultTextLabel = view.findViewById(R.id.result_text_label);
        wordEdit = view.findViewById(R.id.word_editText);

        Button doubleButton = view.findViewById(R.id.double_button);
        doubleButton.setOnClickListener(v->doubleString());

        Button reverseButton = view.findViewById(R.id.reverse_button);
        reverseButton.setOnClickListener(v -> reverse());

        Button trebleButton = view.findViewById(R.id.treble_button);
        trebleButton.setOnClickListener(v -> trebleString());

        return view;
    }


    private void doubleString() {
        String input = wordEdit.getText().toString();
        String output = input + " " + input;
        addHistoryItem(input, getString(R.string.double_function),output);
        outResult(output);
    }

    private void trebleString() {
        String input = wordEdit.getText().toString();
        String output = input + " " + input + " " + input;
        addHistoryItem(input, getString(R.string.treble_function),output);
        outResult(output);
    }

    private void reverse() {
        String input = wordEdit.getText().toString();
        String output = "";
        for(int i=input.length()-1;i>=0;i--)
            output+=input.charAt(i);
        addHistoryItem(input, getString(R.string.reverse_function),output);
        outResult(output);
    }


    private void outResult(String result) {
        resultTextLabel.setText(result);
        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
    }

    private void addHistoryItem(String operand1, String result, String function){
        MainActivity parent = (MainActivity)getActivity();
        parent.addToHistory(new historyItem(operand1, function, result));
    }
}


