import processing.core.PApplet;

import java.util.ArrayList;

public class ExOneMain extends PApplet{
    public static PApplet PA;
    public static void main(String args){
        PApplet.main("MainClass");
    }
    int backgroundColour = 200;
    ArrayList<ColidableRectangle> boxes;
    public void setup(){
        PA = this;



        size(400, 600);
        background(backgroundColour);

        boxes = new ArrayList<ColidableRectangle>();
        int numberOfBoxes = (int)random(6,12);
        for(int a=0; a<numberOfBoxes;a++){
          ColidableRectangle next = new ColidableRectangle((int)random(width/4),(int)random(height/4), new Vector((int)random(width), (int)random(height)));
          next.show(new Force(new Vector(random(-5,5),random(-5,5),0,0));
          boxes.add();
        }

    }
    public void draw(){
        stroke(0);
        fill(backgroundColour);
        rect(0,0,width,height);
        //simple();

        //com.company.fr_recursion.test.show(red,green,blue);

        //for(ColidableRectangle box: boxes){
        //  box.show(new Force(new Vector(0,0)0,0));
        //}

        //j = j + 0.1;
    }


//    void simple(){
//        int offset;
//        noStroke(); fill(255, 204, 0);
//        offset = width-(i%(width))-50;
//        rect(offset, 20, 50, 50);
//        if(offset<0){
//            rect(width+offset, 20, 50, 50);
//        }
//
//        fill(100, 204, 255);
//        offset = (i*2)%(height);
//        rect(width*0.5, offset, 50, 50);
//        if(offset>(height-50)){
//            rect(width*0.5, (offset-height), 50, 50);
//        }
//
//        fill(100, 255, 120);
//        offset = (int)(i*0.5)%(width);
//        rect(offset, 30, 50, 50);
//        if(offset>(width-50)){
//            rect((offset-width), 30, 50, 50);
//        }
//
//        if(red+dr>255||red+dr<0){
//            dr *= -1;
//        }
//        if(green+dg>255||green+dg<0){
//            dg *= -1;
//        }
//        if(blue+db>255||blue+db<0){
//            db *= -1;
//        }
//        red+=dr;
//        green+=dg;
//        blue+=db;
//        fill(red,green,blue);
//        offset = (int)(i*1.5)%(width);
//        float sin = 20*(sin(j));
//        rect(offset, height*0.7+sin, 50, 50);
//        if(offset>(width-50)){
//            rect((offset-width), height*0.7+sin, 50, 50);
//        }
//
//
//        if(i++>=(width)*12) i=0;
//    }
}
