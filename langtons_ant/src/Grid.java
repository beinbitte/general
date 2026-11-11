
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.*;
public class Grid{

    int gridsX;
    int gridsY;
    int offsetx = 180;
    int offsety = 60;
    int bs = 5;
    boolean gridon = false;
    Ant ant;
    Main main;
    ArrayList<ArrayList<block>>[] grid = new ArrayList[4];
    public Grid(Ant a,Main main){
    ant=a;
    this.main=main; 
   }
    public void gridInit(){
        block.width = bs;
        block.height = bs;

        gridsX = (int)(main.getWidth()/block.width);
        gridsY = (int)(main.getHeight()/block.height);    
        
        offsetx = (int)(gridsX/2);
        offsety = (int)(gridsY/2);

        if(gridsX<=100){
            gridon=true;        
        }else{
            gridon=false;
        }
        for (int i = 0;i<grid.length;i++)grid[i] = new ArrayList<>();    

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        increaseGrid();
}   
public void increaseGrid(){

               try{

        if(grid[ant.isIn].size()<=ant.pos.x){
            //System.out.println("increasing y");
            grid[ant.isIn].add(new ArrayList<>());
            increaseGrid();
        }
        else if(grid[ant.isIn].get(ant.pos.x).size()<=ant.pos.y){
            grid[ant.isIn].get(ant.pos.x).add(new block());
            increaseGrid();
        }
    
                }catch(IndexOutOfBoundsException e){System.out.println("BBBBBBBBBBBBBBBBBBBB");}
    }

public void gridpaint(Graphics g){

for (int x = 0;x<grid[0].size();x++){     
            for (int y = 0;y<grid[0].get(x).size();y++){   
                        try{
                        
                        if(grid[0].get(x).get(y).state){
                            g.setColor(Color.black);
                            g.fillRect((int)(x*block.width)+offsetx*block.width,(int)(y*block.height)+offsety*block.height,block.width,block.height);
                        }else{
        if(gridon){
                            g.setColor(Color.black);
                            g.drawRect(x*block.width+offsetx*block.width,y*block.height+offsety*block.height,block.width,block.height);    
        }else{
                            g.setColor(Color.white);
                            g.fillRect((int)(x*block.width)+offsetx*block.width,(int)(y*block.height)+offsety*block.height,block.width,block.height);
        }             
           }
                        
                    }catch(IndexOutOfBoundsException e){break;}
                
            }
}
         for (int x = 0;x>gridsX*-1;x--){         
            for (int y = 0;y<gridsY;y++){
                    try{


                        if(grid[1].get(x*-1).get(y).state){
                            g.setColor(Color.black);
                            g.fillRect((int)(x*block.width)+offsetx*block.width,(int)(y*block.height)+offsety*block.height,block.width,block.height);
                        }else{
        if(gridon){
                            g.setColor(Color.black);
                            g.drawRect(x*block.width+offsetx*block.width,y*block.height+offsety*block.height,block.width,block.height);    
        }else{
                            g.setColor(Color.white);
                            g.fillRect((int)(x*block.width)+offsetx*block.width,(int)(y*block.height)+offsety*block.height,block.width,block.height);
        }  
                        }
                        
                    }catch(IndexOutOfBoundsException e){break;}
                }      
            }


         for (int x = 0;x>gridsX*-1;x--){         
            for (int y = 0;y>gridsY*-1;y--){
                    try{


                        if(grid[2].get(x*-1).get(y*-1).state){
                            g.setColor(Color.black);
                            g.fillRect((int)(x*block.width)+offsetx*block.width,(int)(y*block.height)+offsety*block.height,block.width,block.height);
                        }else{
        if(gridon){
                            g.setColor(Color.black);
                            g.drawRect(x*block.width+offsetx*block.width,y*block.height+offsety*block.height,block.width,block.height);    
        }else{
                            g.setColor(Color.white);
                            g.fillRect((int)(x*block.width)+offsetx*block.width,(int)(y*block.height)+offsety*block.height,block.width,block.height);
        }  
                        }
                        
                    }catch(IndexOutOfBoundsException e){break;}
                }      
            }

         for (int x = 0;x<gridsX;x++){         
            for (int y = 0;y>gridsY*-1;y--){
                    try{

        
                        if(grid[3].get(x).get(y*-1).state){
                            g.setColor(Color.black);
                            g.fillRect((int)(x*block.width)+offsetx*block.width,(int)(y*block.height)+offsety*block.height,block.width,block.height);
                        }else{
        if(gridon){
                            g.setColor(Color.black);
                            g.drawRect(x*block.width+offsetx*block.width,y*block.height+offsety*block.height,block.width,block.height);    
        }else{
                            g.setColor(Color.white);
                            g.fillRect((int)(x*block.width)+offsetx*block.width,(int)(y*block.height)+offsety*block.height,block.width,block.height);
        }  
                        }
                        
                    }catch(IndexOutOfBoundsException e){break;}
                }      
            }

}

}
