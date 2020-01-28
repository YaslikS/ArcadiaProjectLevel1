package com.example.levelonemvparcadia;

import android.util.Log;

public class MainPresenter implements MainInterface.Presenter  {
    private static final String TAG = "MainPresenter";

    private MainInterface.View mView;
    private MainInterface.Model modelAndLogic;

    private String counterTextView;
    private String speedClickerTextView;
    private String notiTextView;

    public MainPresenter(MainInterface.View mView) {
        this.mView = mView;
        this.modelAndLogic = new ModelAndLogic();
        modelAndLogic.start_timeVariable();

        try{
            modelAndLogic.set_counterMain(Integer.parseInt(mView.readFile()));
            notiTextView = "Чтение файла произошло";
            String counter = String.valueOf(modelAndLogic.get_counterMain());
            mView.showTextCounter(counter);
            mView.showNotiText(notiTextView);
        }catch (Exception e){
            notiTextView = "Чтение файла НЕ произошло";
            mView.showNotiText(notiTextView);
        }

        Log.d(TAG, "ConstructorPresenter");
    }

    //View сообщает, что кнопка была нажата GO,BACK или RESET
    @Override
    public void GOButtonWasClicked() {
        modelAndLogic.add_counterMain();
        modelAndLogic.add_countClick();
        counterTextView = String.valueOf(modelAndLogic.get_counterMain());
        speedClickerTextView = String.valueOf(modelAndLogic.get_countClickInSec());
        mView.showTextCounter(counterTextView);
        mView.showSpeedClicker(speedClickerTextView);
        mView.writeFile(String.valueOf(modelAndLogic.get_counterMain()));
        Log.d(TAG, "GOButtonWasClicked()");
    }
    @Override
    public void BACKButtonWasClicked() {
        modelAndLogic.sub_counterMain();
        modelAndLogic.add_countClick();
        counterTextView = String.valueOf(modelAndLogic.get_counterMain());
        speedClickerTextView = String.valueOf(modelAndLogic.get_countClickInSec());
        mView.showTextCounter(counterTextView);
        mView.showSpeedClicker(speedClickerTextView);
        mView.writeFile(String.valueOf(modelAndLogic.get_counterMain()));
        Log.d(TAG, "BACKButtonWasClicked()");
    }
    @Override
    public void RESETButtonWasClicked() {
        modelAndLogic.reset_counterMain();
        modelAndLogic.add_countClick();
        counterTextView = String.valueOf(modelAndLogic.get_counterMain());
        speedClickerTextView = String.valueOf(modelAndLogic.get_countClickInSec());
        mView.showTextCounter(counterTextView);
        mView.showSpeedClicker(speedClickerTextView);
        mView.writeFile(String.valueOf(modelAndLogic.get_counterMain()));
        Log.d(TAG, "RESETButtonWasClicked()");
    }
}
