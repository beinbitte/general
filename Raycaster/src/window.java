import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class window extends Frame {
    Main m;
    Line[] l;
    public window(Main m){
        this.setLocation(2500,0);
        this.setSize(600,600);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        this.m = m;

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                l = m.p.lines;
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0,0, (int) l[1].length));
        g.fillRect(0,0,200,200);
        g.fillRect(0,0,200,200);
        g.fillRect(0,0,200,200);
    }
}
