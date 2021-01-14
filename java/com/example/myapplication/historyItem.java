package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class historyItem implements Parcelable {

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getFunction() {
        return function;
    }

    public String getResult() {
        return result;
    }

    private String operand1;
    private String operand2;
    private String function;
    private String result;
    public historyItem(String operand1, String operand2, String function, String result){
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.function = function;
        this.result = result;
    }

    public historyItem(String operand1, String function, String result){
        this.operand1 = operand1;
        this.operand2 = "";
        this.function = function;
        this.result = result;
    }

    public static final Creator<historyItem> CREATOR = new Creator<historyItem>() {
        @Override
        public historyItem createFromParcel(Parcel in) {
            return new historyItem(in);
        }

        @Override
        public historyItem[] newArray(int size) {
            return new historyItem[size];
        }
    };

    public String getTextRepresentation(){
        String textRepresentation;

        if(operand2.equals(""))
            textRepresentation = String.format("Operation: %1s; Result String: %2s; of Word: %3s ",
                    result, function, operand1);
        else
            textRepresentation = String.format("Result: %1s; Operation: %2s; of numbers: %3s and %4s ",
                    result, function, operand1, operand2);
        return textRepresentation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private historyItem(Parcel in) {
        operand1 = in.readString();
        operand2 = in.readString();
        function = in.readString();
        result = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(operand1);
        dest.writeString(operand2);
        dest.writeString(function);
        dest.writeString(result);
    }
}