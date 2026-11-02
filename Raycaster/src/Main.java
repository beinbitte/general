import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Main extends Frame {
    player p = new player();
    ArrayList<wall> walls = new ArrayList<>();

    int xw = 1920;
    int yh = 1080;
    int raylange = 150;
    int schrittweite = wall.width;
    public static void main(String[] args) {
        new Main();

    }
    public Main(){
        this.setTitle("ERSTER");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        this.setLocation(xw,0);
        this.setSize(xw-1500,yh-700);
        this.setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        int pixelhohe = 100;
        int pixelabstand = pixelhohe /p.lines.length;
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'w'||e.getKeyChar() == 'W'){
                    if(p.rotation==4){
                        if(!p.checkhitbox(aToL(walls),p))p.posy=p.posy-schrittweite;
                        if(p.checkhitbox(aToL(walls),p))p.posy=p.posy+schrittweite;
                    }if(p.rotation==3){
                        if(!p.checkhitbox(aToL(walls),p))p.posx=p.posx-schrittweite;
                        if(p.checkhitbox(aToL(walls),p))p.posx=p.posx+schrittweite;
                    }if(p.rotation==2){
                        if(!p.checkhitbox(aToL(walls),p))p.posy=p.posy+schrittweite;
                        if(p.checkhitbox(aToL(walls),p))p.posy=p.posy-schrittweite;
                    }if(p.rotation==1){
                        if(!p.checkhitbox(aToL(walls),p))p.posx=p.posx+schrittweite;
                        if(p.checkhitbox(aToL(walls),p))p.posx=p.posx-schrittweite;
                    }
                    for(int i = 0;i<p.lines.length;i++){
                        Line l = p.lines[i];
                        l.setorts(p.posx+(p.size/2),p.posy+(p.size/2));
                        l.setziel(p.posx+(p.size/2)+ raylange,p.posy+(p.size/2)+(i*pixelabstand)-(p.lines.length/2*pixelabstand));
                    }
                }
                if(e.getKeyChar() == 'a'||e.getKeyChar() == 'A'){
                    if(!p.checkhitbox(aToL(walls),p))p.posx=p.posx-schrittweite;
                    if(p.checkhitbox(aToL(walls),p))p.posx=p.posx+schrittweite;
                    for(int i = 0;i<p.lines.length;i++){
                        Line l = p.lines[i];
                        l.setorts(p.posx+(p.size/2),p.posy+(p.size/2));
                        l.setziel(p.posx+(p.size/2)+ raylange,p.posy+(p.size/2)+(i*pixelabstand)-(p.lines.length/2*pixelabstand));
                    }
                }
                if(e.getKeyChar() == 's'||e.getKeyChar() == 'S'){
                    if(!p.checkhitbox(aToL(walls),p))p.posy=p.posy+schrittweite;
                    if(p.checkhitbox(aToL(walls),p))p.posy=p.posy-schrittweite;
                    for(int i = 0;i<p.lines.length;i++){
                        Line l = p.lines[i];
                        l.setorts(p.posx+(p.size/2),p.posy+(p.size/2));
                        l.setziel(p.posx+(p.size/2)+ raylange,p.posy+(p.size/2)+(i*pixelabstand)-(p.lines.length/2*pixelabstand));
                    }
                }
                if(e.getKeyChar() == 'd'||e.getKeyChar() == 'D'){
                    if(!p.checkhitbox(aToL(walls),p))p.posx=p.posx+schrittweite;
                    if(p.checkhitbox(aToL(walls),p))p.posx=p.posx-schrittweite;
                    for(int i = 0;i<p.lines.length;i++){
                        Line l = p.lines[i];
                        l.setorts(p.posx+(p.size/2),p.posy+(p.size/2));
                        l.setziel(p.posx+(p.size/2)+ raylange,p.posy+(p.size/2)+(i*pixelabstand)-(p.lines.length/2*pixelabstand));
                    }
                }
                if(e.getKeyChar() == 'j' || e.getKeyChar() == 'J'){
                    p.rotatelinks();
                }
                if(e.getKeyChar() == 'l' || e.getKeyChar() == 'L'){
                    p.rotaterechts();
                }
                System.out.println(p.rotation);
                p.lineaccept();
                System.out.println(p.posx+":"+p.posy);
                if(p.posy==134&&p.posx==314)System.exit(0);

                repaint();
            }
        });

//        for (int i = 0;i<p.lines.length;i++){
//            p.lines[i] = new Line(p.posx+(p.size/2),p.posy+(p.size/2),p.posx+(p.size/2)+50,p.posy+(p.size/2)+(i*10)-(p.lines.length/2*10));
//        }

        for (int i = 0;i<p.lines.length;i++){
            p.lines[i] = new Line(p.posx+(p.size/2),p.posy+(p.size/2),p.posx+(p.size/2)+raylange,p.posy+(p.size/2)+(i*pixelabstand)-(p.lines.length/2*pixelabstand));
        }

        for (int i =0;i<5;i++){
            walls.add(new wall(50,50+(i* wall.width)));
        }
        for (int i =0;i<5;i++){
            walls.add(new wall(5*wall.width+50,50+(i* wall.width)));
        }
        for (int i =0;i<5;i++){
            walls.add(new wall(50+(i* wall.width),50));
        }
        for (int i =0;i<6;i++){
            walls.add(new wall(50+(i* wall.width),50+5*wall.width));
        }
        walls.add(new wall(50+wall.width,50+2*wall.width));
        for (int i =0;i<3;i++){
            walls.add(new wall(50+(3* wall.width),50+wall.width+i*wall.width));
        }
    }

    @Override
    public void paint(Graphics g) {
       drawplayer(g);
       drawwall(g);
       drawLines(g);
       drawscene(g);
    }

    private void drawscene(Graphics g) {
        int pixelwidth = 1500/p.lines.length;
        for (int i = 0;i<p.lines.length;i++){
            if(p.lines[i].walltype.equalsIgnoreCase("top"))g.setColor(new Color(255,0,(int)p.lines[i].length));
            if(p.lines[i].walltype.equalsIgnoreCase("right"))g.setColor(new Color(0,255,(int)p.lines[i].length));
            if(p.lines[i].walltype.equalsIgnoreCase("left"))g.setColor(new Color(0,0,(int)p.lines[i].length));
            if(p.lines[i].walltype.equalsIgnoreCase("bottom"))g.setColor(new Color(255,255,(int)p.lines[i].length));
            if(p.lines[i].walltype.equalsIgnoreCase("nothing"))g.setColor(new Color(0,0,0));
            g.fillRect(400+(i*pixelwidth),0,pixelwidth,1000);
        }
    }

    public void drawplayer(Graphics g){
        g.setColor(p.color);
        g.fillOval(p.posx,p.posy,p.size,p.size);
        g.setColor(Color.RED);
    }
    public void drawwall(Graphics g){
        g.setColor(wall.color);
        for (int i = 0;i<walls.size();i++){
            g.drawRect(walls.get(i).posx,walls.get(i).posy,wall.width,wall.width);
        }
    }
    public void drawLines(Graphics g){
        for (int i = 0;i<p.lines.length;i++){
            Line l = p.lines[i];
            l.length=255;
            l.walltype="nothing";
            if(!l.checkhit(aToL(walls)))g.drawLine(l.ex1,l.ey1,l.ex2,l.ey2);
            if(l.checkhit(aToL(walls))){
               g.drawLine(l.ex1,l.ey1,(int)l.finalintersactionx,(int)l.finalintersactiony);
            }
            System.out.println("LINE: "+i+" LENGTH: "+l.length+" walltype: "+l.walltype);
        }
    }
    public wall[] aToL(ArrayList<wall> w){
        wall[] walls = new wall[w.size()];
        for(int i = 0; i< walls.length;i++){
            walls[i] = w.get(i);
        }
        return walls;
    }
}