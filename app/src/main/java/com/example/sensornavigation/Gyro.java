package com.example.sensornavigation;

public class Gyro {
    private String xGyro,yGyro,zGyro;

    public Gyro(){

    }

    public Gyro(String xGyro, String yGyro, String zGyro) {
        this.xGyro = xGyro;
        this.yGyro = yGyro;
        this.zGyro = zGyro;
    }

    public String getxGyro() {
        return xGyro;
    }

    public String getyGyro() {
        return yGyro;
    }

    public String getzGyro() {
        return zGyro;
    }
}
