package com.migia.model;

public enum Position{
    ABOVE_MIDDLE(2),
    BELOW_MIDDLE(3),
    ABOVE_UPPER(1),
    BELOW_UPPER(2),
    ABOVE_LOWER(3),
    BELOW_LOWER(4),
    UNKNOWN(-1);
    private final int value;

    Position(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}



