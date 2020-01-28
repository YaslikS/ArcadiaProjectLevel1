package com.example.levelonemvparcadia;

public interface MainInterface {
    interface View{
        void showTextCounter(String counterTextView);
        void showSpeedClicker(String speedClickerTextView);
        void showNotiText(String showNotiText);
        void writeFile(String counter);
        String readFile();
    }

    interface Presenter{
        void GOButtonWasClicked();
        void BACKButtonWasClicked();
        void RESETButtonWasClicked();
    }

    interface Model{
        int get_countClick();
        void add_countClick();
        void add_counterMain();
        void set_counterMain(int counter);
        int get_counterMain();
        void sub_counterMain();
        void reset_counterMain();
        void start_timeVariable();
        double get_countClickInSec();
    }
}
