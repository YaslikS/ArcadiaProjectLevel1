package com.example.levelonemvparcadia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements MainInterface.View  {
    final static String nameSaveNotiTV = "nameSaveNotiTV";
    final static String nameSaveCounterTV = "nameSaveCounterTV";
    final static String nameSaveSpeedClickerTV = "nameSaveSpeedClickerTV";
    private final String FILENAME_COUNTER = "counterFile";
    private static final String TAG = "MainActivity";
    private MainInterface.Presenter mPresenter;
    private Button btnGO, btnBACK, btnRESET;
    private TextView speedClickerTV, counterTV, notiTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBACK = (Button) findViewById(R.id.btnBACK);
        btnGO = (Button) findViewById(R.id.btnGO);
        btnRESET = (Button) findViewById(R.id.btnRESET);
        speedClickerTV = (TextView) findViewById(R.id.speedClickerTextView);
        counterTV = (TextView) findViewById(R.id.counterTextView);
        notiTV = (TextView) findViewById(R.id.notyTextView);

        mPresenter = new MainPresenter(this);

        btnGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.GOButtonWasClicked();
            }
        });
        btnBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.BACKButtonWasClicked();
            }
        });
        btnRESET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.RESETButtonWasClicked();
            }
        });
        Log.d(TAG, "onCreate()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        /*outState.putString(nameSaveViewIndicatorDegreeTV, indicatorDegreeTV.getText().toString());
        outState.putString(nameSaveViewWeatherDetailsTV, weatherDetailsTV.getText().toString());*/

        outState.putString(nameSaveCounterTV, counterTV.getText().toString());
        outState.putString(nameSaveSpeedClickerTV, speedClickerTV.getText().toString());
        outState.putString(nameSaveNotiTV, notiTV.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        /*String indicatorDegreeTVtext = savedInstanceState.getString(nameSaveViewIndicatorDegreeTV);
        String weatherDetailsTVtext = savedInstanceState.getString(nameSaveViewWeatherDetailsTV);

        indicatorDegreeTV.setText(indicatorDegreeTVtext);
        weatherDetailsTV.setText(weatherDetailsTVtext);*/

        String counterTVtext = savedInstanceState.getString(nameSaveCounterTV);
        String speedClickerTVtext = savedInstanceState.getString(nameSaveSpeedClickerTV);
        String notiTVtext = savedInstanceState.getString(nameSaveNotiTV);

        counterTV.setText(counterTVtext);
        speedClickerTV.setText(speedClickerTVtext);
        notiTV.setText(notiTVtext);
    }

    @Override
    public void showTextCounter(String counterTextView) {
        counterTV.setText(counterTextView);
        Log.d(TAG, "showCounterTextView()");
    }
    @Override
    public void showSpeedClicker(String showSpeedClicker) {
        speedClickerTV.setText(showSpeedClicker);
        Log.d(TAG, "showSpeedClicker()");
    }
    @Override
    public void showNotiText(String showNotiText) {
        notiTV.setText(showNotiText);
        Log.d(TAG, "showNotiTextView()");
    }

    public void writeFile(String counter){
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(  // отрываем поток для записи
                    openFileOutput(FILENAME_COUNTER, MODE_PRIVATE)));   // пишем данные
            bw.write(counter);
            bw.close();     // закрываем поток
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String readFile() {
        String counter = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(   // открываем поток для чтения
                    openFileInput(FILENAME_COUNTER)));
            counter = br.readLine();    // читаем содержимое
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }
}
