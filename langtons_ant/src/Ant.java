
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.*;
public class Ant{
    
    Point pos;
    boolean antNegX = false;
    boolean antNegY = false;
    int dir = 3;
    int isIn = 0;
    Main main;

    public Ant(Point p,Main main){
        pos = p;
        this.main = main;
    }

public void antmove() {
        //dir == 0 == up
        //dir == 1 == right
        //dir == 2 == down
        //dir == 3 == left

        rules();

        if(dir == 0){
            pos.y--;
        }else if (dir == 1){
            pos.x++;
        } else if (dir == 2) {
            pos.y++;
        }else {
            pos.x--;
        }

        checkneg();
        checkin();

        negatePos();
        main.grid.increaseGrid();
        negatePos();

    }
        public void checkneg(){
        if(pos.x<0){antNegX=true;
        }else{antNegX=false;}
        if(pos.y<0){antNegY=true;
        }else{antNegY=false;}

        }
        public void rules(){

        negatePos();
        if(!main.grid.grid[isIn].get(pos.x).get(pos.y).state){
            if(dir==3){
                dir=0;
            }else {
                dir++;
            }

        }else{
            if(dir==0){
                dir=3;
            }else {
                dir--;
            }
        }

        main.grid.grid[isIn].get(pos.x).get(pos.y).state=!main.grid.grid[isIn].get(pos.x).get(pos.y).state;

        negatePos();


    }
    public void negatePos(){
        if(antNegX){pos.x*=-1;}
        if(antNegY){pos.y*=-1;}
    }
    public void checkin(){
    if(pos.x<0&&pos.y>0){
        isIn = 1;
    }else if(pos.x<0&&pos.y<0){
        isIn = 2;    
    }else if(pos.x>0&&pos.y<0){
        isIn = 3;    
    }else if(pos.x>=0&&pos.y>=0){
        isIn = 0;    
    }
    }
    public void antpaint(Graphics g){
            g.setColor(Color.green);
        g.fillRect((int) (pos.x*block.width)+main.grid.offsetx*block.width, (int) (pos.y*block.height)+main.grid.offsety*block.height, (int) block.width, (int) block.height);
    }
}
