package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;


public class numbersFragment extends Fragment {

    TextView resultTextLabel;
    EditText xEdit;
    EditText yEdit;

    public numbersFragment(){

    }

    public static numbersFragment newInstance(){
        return new numbersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number, container, false);
        resultTextLabel = view.findViewById(R.id.result_text_label);
        xEdit = view.findViewById(R.id.x_editText);
        yEdit = view.findViewById(R.id.y_editText);

        Button minusButton = view.findViewById(R.id.minus_button);
        minusButton.setOnClickListener(v -> {
            Integer y = Integer.parseInt(yEdit.getText().toString());
            Integer x = Integer.parseInt(xEdit.getText().toString());
            resultTextLabel.setText(String.valueOf(x - y));
            addHistoryItem(x,y,x-y,getString(R.string.subtraction_function));
            Toast.makeText(getActivity(), String.valueOf(x - y), Toast.LENGTH_SHORT).show();
        });

        Button multiplyButton = view.findViewById(R.id.multiply_button);
        multiplyButton.setOnClickListener(v -> {
            Integer y = Integer.parseInt(yEdit.getText().toString());
            Integer x = Integer.parseInt(xEdit.getText().toString());
            resultTextLabel.setText(String.valueOf(x * y));
            addHistoryItem(x,y,x*y,getString(R.string.multiplication_function));
            Toast.makeText(getActivity(), String.valueOf(x * y), Toast.LENGTH_SHORT).show();
        });

        Button sumButton = view.findViewById(R.id.sum_button);
        sumButton.setOnClickListener(v -> {
            Integer y = Integer.parseInt(yEdit.getText().toString());
            Integer x = Integer.parseInt(xEdit.getText().toString());
            resultTextLabel.setText(String.valueOf(x + y));
            addHistoryItem(x,y,x+y,getString(R.string.summation_function));
            Toast.makeText(getActivity(), String.valueOf(x + y), Toast.LENGTH_SHORT).show();
        });
        return view;
    }
    private void addHistoryItem(int operand1, int operand2, int result, String function){
        //bad style, but will stay till lab7
        String operand1String = String.format("%1d",operand1);
        String operand2String = String.format("%1d",operand2);
        String resultString = String.format("%1d",result);

        MainActivity parent = (MainActivity)getActivity();
        parent.addToHistory(new historyItem(operand1String, operand2String, function, resultString));
    }
}
