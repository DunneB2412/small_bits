package com.company;

public class IntOI {
    public final int value;
    private boolean active;
    IntOI(int value){
        this.value = value;
        active = true;
    }
    public void crossOut(){
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        if(active){
            return value+"";
        }
        return "["+value+"]";
    }
}

