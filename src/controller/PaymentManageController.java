package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class PaymentManageController {
    public Label txtDate;
    public Label txtTime;

    public void initialize(){

        loadDateAndTime();
    }

    public void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            String state = null;
            int hour = currentTime.getHour();
            if (hour < 12) {
                state = "AM";
            } else {
                state = "PM";
            }
            txtTime.setText(
                    currentTime.getHour()+ ": "+currentTime.getMinute()+ ": "+state
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }
}
