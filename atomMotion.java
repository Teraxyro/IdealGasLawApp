package com.company;

import javax.swing.*;
import java.util.*;

public class atomMotion extends JPanel {

    Random rand = new Random();

    private int x;
    private int y;
    private double velX = rand.nextDouble() * 7  + 1;
    private double velY = rand.nextDouble() * 7  + 1;
    private boolean collisionStageOneCheck = false;
    private boolean collisionStageTwoCheck = false;




    //private boolean calculating = false;
    private double finTheta;
    /*
        public boolean isCalculating() {
            return calculating;
        }

        public void setCalculating(boolean calculating) {
            this.calculating = calculating;
        }
    */
    public double getFinTheta() {
        return finTheta;
    }

    public void setFinTheta(double finTheta) {
        this.finTheta = finTheta;
    }

    public atomMotion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean getCollisionStageOneCheck() {
        return collisionStageOneCheck;
    }

    public void setCollisionStageOneCheck(boolean collisionStageOneCheck) {
        this.collisionStageOneCheck = collisionStageOneCheck;
    }

    public boolean getCollisionStageTwoCheck() {
        return collisionStageTwoCheck;
    }

    public void setCollisionStageTwoCheck(boolean collisionStageTwoCheck) {
        this.collisionStageTwoCheck = collisionStageTwoCheck;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public atomMotion copy() {
        atomMotion a = new atomMotion(this.x, this.y);
        a.setVelX(velX);
        a.setVelY(velY);
        return a;
    }
}