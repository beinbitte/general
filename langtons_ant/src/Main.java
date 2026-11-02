import sun.font.Decoration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Main extends JFrame {

    static int fps = 1000;
    int dir = 2;
    int gridSizeX = 200;
    int gridSizeY = 200;
    Point ant = new Point(gridSizeX/2,gridSizeY/2);
    block[][] grid = new block[gridSizeX][gridSizeY];
    int offSetX = 10;
    int offSetY = 10+20;
    BufferedImage img = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_RGB);

    public static void main(String[] args) {
        new Main();
    }
    public Main(){

        setTitle("Langton's ant");
        setSize(1960/2,1080/2);
        setExtendedState(MAXIMIZED_BOTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);

        for (int x = 0;x<gridSizeX;x++){
            for (int y = 0;y<gridSizeY;y++) {
            grid[x][y]=new block();
            }
        }

        while (true){

            blockCalc();
            mainloop();

            try {
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void blockCalc() {
        block.width = ((double) getWidth() /gridSizeX)-((double) offSetX*2 /gridSizeX);
        block.height = ((double) getHeight() /gridSizeY)-((double) offSetY*2 /gridSizeY);
        /*for (int x = 0;x<gridSizeX;x++){
            for (int y = 0;y<gridSizeY;y++){
                grid[]
            }
        }*/
    }

    private void mainloop() {
        antmove();
        repaint();
    }

    public void antmove() {
        //dir == 0 == up
        //dir == 1 == right
        //dir == 2 == down
        //dir == 3 == left
        checkBox();
        if(dir == 0){

            ant.y++;
        }else if (dir == 1){

            ant.x++;
        } else if (dir == 2) {

            ant.y--;
        }else {

            ant.x--;
        }
    }
    public void checkBox(){
        grid[ant.x][ant.y].state=!grid[ant.x][ant.y].state;
        if(grid[ant.x][ant.y].state){
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
    }
    @Override
    public void paint(Graphics g) {
        drawImg();
        g.drawImage(img,0,0,this);

    }
    public void drawImg(){
        Graphics g = img.getGraphics();
        g.setColor(Color.red);
        g.fillRect(0,0,getWidth(),getHeight());

        g.setColor(Color.black);
        for (int x = 0;x<gridSizeX;x++){
            for (int y = 0;y<gridSizeY;y++){
                if(!grid[x][y].state){
                    g.drawRect((int) (offSetX+x*block.width), (int) (offSetY+y*block.height), (int) block.width, (int) block.height);
                }else {
                    g.fillRect((int) (offSetX+x*block.width), (int) (offSetY+y*block.height), (int) block.width, (int) block.height);
                }
            }
        }
        g.setColor(Color.green);
        g.fillRect((int) (offSetX+ant.x*block.width), (int) (offSetY+ant.y*block.height), (int) block.width, (int) block.height);
    }
}