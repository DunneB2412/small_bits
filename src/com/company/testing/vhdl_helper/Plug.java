package com.company.testing.vhdl_helper;

public class Plug extends Wire{
    boolean in;
    boolean out;

    public Plug(String name, boolean in, boolean out, int size){
        super(name,size);
        this.in = in;
        this.out = out;
    }
}
