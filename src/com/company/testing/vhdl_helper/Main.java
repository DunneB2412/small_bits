package com.company.testing.vhdl_helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            File file = new File("filename.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter writer = new FileWriter(file);

            String moduleName = "reg_fo";
            //Wire[] wires = new Wire[]{new Wire("clk",1), new Wire("load", 1), new Wire("input",41), new Wire("value",41)};
            Port port = new Port(new Plug("clk",true,false,1), new Plug("load", true, false, 1),
                    new Plug("input", true, false, 41), new Plug("value", false, true, 41));
            int power = 8;
            int n = (int) Math.pow(2, power);
            for (int i = (n/2)-1; i < n; i++) {
                String indexing = n>1? ""+String.format("%1$"+7+"s", Integer.toBinaryString(i)).replace(' ', '0')+"":"";
//                System.out.println(i);
//                writer.write(moduleName.substring(0,Math.min(moduleName.length(),3))+indexing+": "+moduleName+" port map(\n");
//                for(int j = 0; j<port.plugs.length; j++){
//                    writer.write(port.plugs[j].name+indexing+" => "+port.plugs[j].name+indexing+(j<(port.plugs.length-1)?",":"")+"\n");
//                }
                //                          v24vvv v17        v6v4
                writer.write("\"000000000000000000000000000000000000000000\",--("+indexing+")\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    //void fillInHeader(){

    //}
}
