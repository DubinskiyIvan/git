package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "history")
public class HistoryEntry implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "operand1")
    private final String operand1;
    @ColumnInfo(name = "operand2")
    private final String operand2;
    @ColumnInfo(name = "function")
    private final String function;
    @ColumnInfo(name = "result")
    private final String result;

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

    public HistoryEntry(String operand1, String operand2, String function, String result){
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.function = function;
        this.result = result;
    }

    @Ignore
    public HistoryEntry(String operand1, String function, String result){
        this.operand1 = operand1;
        this.operand2 = "";
        this.function = function;
        this.result = result;
    }

    public static final Creator<HistoryEntry> CREATOR = new Creator<HistoryEntry>() {
        @Override
        public HistoryEntry createFromParcel(Parcel in) {
            return new HistoryEntry(in);
        }

        @Override
        public HistoryEntry[] newArray(int size) {
            return new HistoryEntry[size];
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

    protected HistoryEntry(Parcel in) {
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
