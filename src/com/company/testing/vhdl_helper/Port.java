package com.company.testing.vhdl_helper;

public class Port {
    Plug[] plugs;
    public Port(Plug... plugs){
        if(plugs.length>1){
            this.plugs=plugs;
            return;
        }
        throw new UnsupportedOperationException();
    }

}
