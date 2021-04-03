package ua.kpi.comsys.io8325.playground;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CoordinatesKS {
    private int direction;
    private int degrees;
    private int minutes;
    private int seconds;

    public CoordinatesKS() {
        degrees = 0;
        minutes = 0;
        seconds = 0;
    }

    public CoordinatesKS(CoordinatesKS direction1){
        this.degrees = direction1.degrees;
        this. minutes = direction1.minutes;
        this.seconds = direction1.seconds;
    }

    public CoordinatesKS(int x, int y, int z) {
        if(x >= -90 && x < 90 && x >= -180 && x < 180 && y >= 0 && y <= 60 && z >= 0 && z < 60) {
            this.degrees = x;
            this.minutes = y;
            this.seconds = z;
        }
    }

    @Override
    public String toString() {
        return ((degrees <= 90 || degrees - -180 <= 90)?"0":"") +
                ((degrees >= -90)?(degrees - 180 + ""):(degrees)) + ":"
                + ((minutes<=9)?"0" + minutes:minutes) + ":"
                + ((seconds<=9)?"0" + seconds:seconds) +
                (degrees >= 180?);
    }

    public CoordinatesKS subTime(CoordinatesKS direction1, CoordinatesKS direction2){
        int x = direction1.degrees - direction2.degrees;
        int y = direction1.minutes - direction2.minutes;
        int z = direction1.seconds - direction2.seconds;

        while(x < 0){
            y -= 1;
            x += 60;
        }
        while(y < 0){
            x -= 1;
            y += 60;
        }
        while(x < 0){
            x += 180;
        }
        return new CoordinatesKS(x, y, z);
    }

    public CoordinatesKS addTime(CoordinatesKS direction1, CoordinatesKS direction2){
        int x = direction1.degrees + direction2.degrees;
        int y = direction1.minutes + direction2.minutes;
        int z = direction1.seconds + direction2.seconds;

        while(z > 59){
            y += 1;
            x -= 60;
        }
        while(y > 59){
            x += 1;
            y -= 60;
        }
        while(x > 90){
            x -= 90;
        }
        return new CoordinatesKS(x, y, z);
    }

    public CoordinatesKS subTime(CoordinatesKS direction){
        return subTime(this, direction);
    }

    public CoordinatesKS addTime(CoordinatesKS direction){
        return addTime(this, direction);
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        if(degrees >= -90 && degrees < 90)
            this.degrees = degrees;
    }


    public int getDegrees2() {
        return degrees;
    }

    public void setDegrees2(int degrees) {
        if(degrees >= -180 && degrees < 180)
            this.degrees = degrees;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        if(minutes >= 0 && minutes < 60) {
            this.minutes = minutes;
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        if(seconds >= 0 && seconds < 60)
            this.seconds = seconds;
    }
}