package com.example.levelonemvparcadia;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ModelAndLogic extends AppCompatActivity implements MainInterface.Model {
    private static final String TAG = "MainModelAndLogic";
    private final String FILENAME_COUNTER = "counterFile";

    private static int _counterMain = 0;
    private static int _countClick = 0;
    private static long _timeVariable;

    public int get_countClick() { return _countClick;}
    public void add_countClick() { ++_countClick;}

    public int get_counterMain() {return _counterMain;}
    public void set_counterMain(int counter) { ModelAndLogic._counterMain = counter; }
    public void add_counterMain(){++_counterMain;}
    public void sub_counterMain(){--_counterMain;}
    public void reset_counterMain(){ _counterMain = 0;}

    public void start_timeVariable(){ _timeVariable = System.currentTimeMillis(); }
    private long get_timeVariable() { return _timeVariable; }

    public double get_countClickInSec(){
        return (double) get_countClick() / ((System.currentTimeMillis() - get_timeVariable()) / 1000);
    }
}
