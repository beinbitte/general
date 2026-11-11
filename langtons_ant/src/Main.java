
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.*;

public class Main extends JFrame implements KeyListener{


    static int fps = 1;

    Ant ant = new Ant(new Point(0,0),this);

    Grid grid = new Grid(ant,this);



    boolean right = false;
    boolean left = false;
    boolean up = false;
    boolean down = false;
        
    BufferedImage img = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_RGB);

    public static void main(String[] args) {
        new Main();
    }
    public Main(){

        init(this);

        while (true){

            mainloop();

            try {
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
   
    public void mainloop() {
        cammove();
        ant.antmove();
        repaint();
    }
public void init(JFrame f){
        frameInit(f);
        buttonInit();
        grid.gridInit();
}

     public void frameInit(JFrame f){
        f.setLayout(new FlowLayout());
        f.setTitle("Langton's ant");
        f.setSize(1377,768);
        f.setExtendedState(MAXIMIZED_BOTH);

        f.addKeyListener(this);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }
    


    public void cammove(){
        if(up)grid.offsety++;
        if(right)grid.offsetx--;
        if(left)grid.offsetx++;
        if(down)grid.offsety--;
    }
    
    @Override
    public void paint(Graphics g) {

        drawImg();
        g.drawImage(img,0,0,this);

    }
    public void drawImg(){

        Graphics g = img.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,getWidth(),getHeight());
           
        if(grid.gridon){
        for (int x = (int)0;x<grid.gridsX+1;x++){         
            for (int y = (int)0;y<grid.gridsY+1;y++){
                g.setColor(Color.black);
                g.drawRect(x*block.width,y*block.height,block.width,block.height);    
            }
        }
    }


        grid.gridpaint(g);


        ant.antpaint(g);

    
    }


    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_W)up=true;
        if(e.getKeyCode()==KeyEvent.VK_A)left=true;
        if(e.getKeyCode()==KeyEvent.VK_S)down=true;
        if(e.getKeyCode()==KeyEvent.VK_D)right=true;
    }

    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_W)up=false;
        if(e.getKeyCode()==KeyEvent.VK_A)left=false;
        if(e.getKeyCode()==KeyEvent.VK_S)down=false;
        if(e.getKeyCode()==KeyEvent.VK_D)right=false;
    }

public void buttonInit(){

    Button b1 = new Button("increase box size");
        b1.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
        grid.bs++;

        int ox = grid.offsetx-(int)(grid.gridsX/2);
        int oy = grid.offsety-(int)(grid.gridsY/2);
        
        block.width = grid.bs;
        block.height = grid.bs;

        grid.gridsX = (int)(getWidth()/block.width);
        grid.gridsY = (int)(getHeight()/block.height);    

        
        grid.offsetx = (int)(grid.gridsX/2)+ox;
        grid.offsety = (int)(grid.gridsY/2)+oy;

        if(grid.gridsX<=100)grid.gridon=true; 
        }});
        add(b1);
        b1.setFocusable(false);

////////////////////////////////////////////

        Button b2 = new Button("decrease box size");
        b2.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
        grid.bs--;
        if(grid.bs==0)grid.bs++;
        int ox = grid.offsetx-(int)(grid.gridsX/2);
        int oy = grid.offsety-(int)(grid.gridsY/2);
        
        block.width = grid.bs;
        block.height = grid.bs;

        grid.gridsX = (int)(getWidth()/block.width);
        grid.gridsY = (int)(getHeight()/block.height);    
        if(grid.gridsX<=100){
grid.gridon=true; 
}else{grid.gridon=false;}        
        grid.offsetx = (int)(grid.gridsX/2)+ox;
        grid.offsety = (int)(grid.gridsY/2)+oy;


        }});
        add(b2);
        b2.setFocusable(false);

///////////////////////////////////////////////////////////

Button b3 = new Button("change fps");
        b3.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
        
        Scanner scan = new Scanner(System.in);
        System.out.println("EINGABE GESCHWINDIGKEIT(fps): ");
        fps = scan.nextInt();
        
        }});
        add(b3);
        b3.setFocusable(false);

    }
}




