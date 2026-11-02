import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import javax.swing.event.MouseInputListener;

public class Main extends JFrame implements KeyListener, MouseInputListener {
    Image img = new BufferedImage(1960,1080,BufferedImage.TYPE_INT_RGB);
    camera cam;
    ArrayList<object> objects = new ArrayList<>();
    int frameRate = 60;
    double r = 0;
    double s = 3.14/2;
    boolean finished=false;
    double doneIn; int doneMax=0;
    ArrayList<slider> sliders = new ArrayList<>();
    String[] modelNames = {
            "painter",          //0
            "cooles_object",    //1
            "cooles_object2",   //2
            "donut2",           //3
            "exa",              //4
            "gedrehtPainter",   //5
            "suzanne",          //6
            "name",             //7
            "utah-teapot",      //8
            "stanford-bunny",   //9
            "stanford-dragon",  //10
            "curveZX",          //11
            "curveZY",          //12
            "curveXY",          //13
            "indepthSphere",    //14
            "colorTest",        //15
            "sphere"            //16
    };
    BufferedReader br;
    vector posO1 = new vector(0,0,5000);
    vector posO2 = new vector(500,200,100);
    vector lightDir = new vector(0,0,1);
    int controller = 0;
    boolean right,down,up,left,forward,back;
    boolean click=false;
    public static void main(String[] args) throws IOException, InterruptedException {
        new Main();
    }

    public Main() throws IOException, InterruptedException {

        setLayout(new FlowLayout());
        setSize(camera.maxWidth,camera.maxHeight);
        setTitle("Rasterizer");
        setLocation(0,0);
        setExtendedState(MAXIMIZED_BOTH);
        setBackground(Color.BLACK);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        addKeyListener(this);
        addMouseListener(this);
        Button schiebeObj3 = new Button("verschiebe light");
        schiebeObj3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lightDir = lightDir.add(new vector(1,0,0));
                lightDir.printV();
            }
        });
        add(schiebeObj3);
        schiebeObj3.setFocusable(false);
        Button schiebeObj2 = new Button("xy");
        schiebeObj2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objects.get(controller).spinxy=!objects.get(controller).spinxy;
            }
        });
        add(schiebeObj2);
        schiebeObj2.setFocusable(false);
        Button schiebeObj4 = new Button("xz");
        schiebeObj4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objects.get(controller).spinxz=!objects.get(controller).spinxz;
            }
        });
        add(schiebeObj4);
        schiebeObj4.setFocusable(false);
        Button schiebeObj5 = new Button("yz");
        schiebeObj5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objects.get(controller).spinyz=!objects.get(controller).spinyz;
            }
        });
        add(schiebeObj5);
        schiebeObj5.setFocusable(false);
        setIconImage(ImageIO.read(new File("C:\\Users\\Christian\\Documents\\schule\\Java IDE\\rasterizer\\bunny.png")));
        //setUndecorated(true);
        setVisible(true);

        TextField tf = new TextField(10);
        tf.setFocusable(false);
        add(tf);

        cam = new camera();
        System.out.println("Hello world");
        for (int i = 0;i<modelNames.length;i++) System.out.println(i+":"+modelNames[i]);
        cam.moveCam(new vector(0,0,5));


        //objects.add(new object(new vector(0,0,0)));
        //objects.get(0).vertices.add(new vertex(new vector(350,350,70),new vector(500,40,70),new vector(700,100,70)));
        //objects.get(0).vertices.add(new vertex(new vector(350,350,70),new vector(500,40,70),new vector(100,300,70)));
        long a = System.nanoTime();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! SLIDERINIT

        sliders.add(new slider(100,70,1800,20,0.1,10,"SCALE"));
        sliders.add(new slider(100,1060-30,1800,10,0.1,3.14*2,"ROTATEXY",3.14));
        sliders.add(new slider(100,1060-60,1800,10,0.1,3.14*2,"ROTATEYZ",3.14));
        sliders.add(new slider(100,1060-90,1800,10,0.1,3.14*2,"ROTATEXZ",3.14));
        /*sliders.add(new slider(100,1060-120,1800,10,0.1,10,"XX",1));
        sliders.add(new slider(100,1060-150,1800,10,0.1,10,"XY",1));
        sliders.add(new slider(100,1060-180,1800,10,0.1,10,"XZ",1));
        sliders.add(new slider(100,1060-210,1800,10,0.1,10,"YX",1));
        sliders.add(new slider(100,1060-240,1800,10,0.1,10,"YY",1));
        sliders.add(new slider(100,1060-270,1800,10,0.1,10,"YZ",1));*/
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! SLIDERINIT
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! MODELINIT

        modelInit(1);
        modelInit(6);
        modelInit(8);

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! MODELINIT
        long b = System.nanoTime();
        System.out.println(tf.getWidth()+tf.getX());
        System.out.println(tf.getX());
        System.out.println(tf.getHeight()+tf.getY());
        System.out.println(tf.getY());
        System.out.println("init: "+(b-a)/(double)1000000000+"s");
        finished = true;
        for (int i = 0;i<objects.size();i++){
            controller=i;
            mainLoop();
        }
        controller=0;
        while (true){

            if(right){objects.get(controller).pos = objects.get(controller).pos.add(new vector(5,0,0));}
            if(left){objects.get(controller).pos = objects.get(controller).pos.add(new vector(-5,0,0));}
            if(up){objects.get(controller).pos = objects.get(controller).pos.add(new vector(0,-5,0));}
            if(down){objects.get(controller).pos = objects.get(controller).pos.add(new vector(0,5,0));}
            if(forward){objects.get(controller).pos = objects.get(controller).pos.add(new vector(0,0,5));}
            if(back){objects.get(controller).pos = objects.get(controller).pos.add(new vector(0,0,-5));}

            for (slider s : sliders)s.sliderMove(this);
            for (object o : objects)if(o.spinxy)o.rotateObjEnhanced("xy",0.02,true);
            for (object o : objects)if(o.spinxz)o.rotateObjEnhanced("xz",0.01,true);
            for (object o : objects)if(o.spinyz)o.rotateObjEnhanced("yz",0.02,true);

            if(sliders.get(0).followMouse)objects.get(controller).scale=sliders.get(0).value;
            if(sliders.get(1).followMouse)objects.get(controller).rotateObjEnhanced("xy",sliders.get(1).value);
            if(sliders.get(2).followMouse)objects.get(controller).rotateObjEnhanced("yz",sliders.get(2).value);
            if(sliders.get(3).followMouse)objects.get(controller).rotateObjEnhanced("xz",sliders.get(3).value);
            //if(sliders.get(4).followMouse||sliders.get(5).followMouse||sliders.get(6).followMouse)cam.posXPlane=new vector(sliders.get(4).value,sliders.get(5).value,sliders.get(6).value);
            //if(sliders.get(7).followMouse||sliders.get(8).followMouse||sliders.get(9).followMouse)cam.posYPlane=new vector(sliders.get(7).value,sliders.get(8).value,sliders.get(9).value);

            if(click){
                click = false;
                if(getMousePosition().x<tf.getWidth()+tf.getX()&&getMousePosition().x>tf.getX()&&
                        getMousePosition().y<tf.getHeight()*4+tf.getY()&&getMousePosition().y>tf.getY()){
                    tf.setFocusable(true);
                }else {
                    tf.setFocusable(false);
                }
            }
            mainLoop();

            /*objects.get(controller).rotateObj("x",r);
            objects.get(controller).rotateObj("y",s);
            objects.get(controller).rotateObj("z",s);
            r+=0.1;
            s+=0.1;*/

            Thread.sleep(1000/frameRate);

        }


    }
    public void modelInit(int model) throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\Christian\\Documents\\schule\\Java IDE\\rasterizer\\" + modelNames[model] + ".obj"))));
        initObjFile();
        objects.get(objects.size()-1).name=modelNames[model];
    }
    /*


    @Override
    public void paint(Graphics g) {
        drawImg(g);
    }
    public void drawImg(Graphics g){
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth()+1000,getHeight()+1000);
        drawObject(g);
        g.drawString("CONTROLLING OBJECT: "+(controller+1),100,100);
    }
     */

    @Override
    public void paint(Graphics g) {
        drawImg();
        g.drawImage(img,0,0,getWidth(),getHeight(),this);
    }
    public void drawImg(){
        Graphics g = img.getGraphics();
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth()+1000,getHeight()+1000);
        drawObject(g);
        for (slider s : sliders)s.drawSlider(g);
        if(!finished){
            g.drawString("INITIALISING",100,130);
        }else{
            g.setColor(Color.GREEN);
            g.drawString("FINISHED",100,130);
        }
        g.drawString("CONTROLLING OBJECT: "+(controller+1)+":"+objects.get(controller).name,100,110);
    }



    public void drawObject(Graphics g) {
        try{
        for (object o : objects) {
            for (vertex v : o.vertices) {
                if (o.active) {
                    g.setColor(v.color);
                    g.fillPolygon(v.x2dPos, v.y2dPos, 3);
                    /*try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }*/
                }
            }
        }
        }catch (ConcurrentModificationException ignored){}
    }
    public void mainLoop(){

        for (object o : objects){               //
            if(o==objects.get(controller)||o.spinxy||o.spinyz||o.spinxz)o.updatePosVertices();              //
            for (vertex v : o.vertices){        //

                if(o==objects.get(controller)||o.spinxy||o.spinyz||o.spinxz)v.checkHit(cam);                // main
                if(o==objects.get(controller)||o.spinxy||o.spinyz||o.spinxz)v.calculateColor(lightDir);
                // loop
            }
            if(o==objects.get(controller)||o.spinxy||o.spinyz||o.spinxz)sortFacesByDistanceAverage(o);
        }
        repaint();
    }

    private void sortFacesByDistanceAverage(object o) {
       vertex[] vA = aToL(o.vertices);
        Arrays.sort(vA, new Comparator<vertex>(){
            @Override
            public int compare(vertex apple1, vertex apple2){
                return (int)apple2.lange - (int)apple1.lange;
            }
        });
        o.vertices = new ArrayList<>(Arrays.asList(vA));
    }
    public vertex[] aToL(ArrayList<vertex> w){
        vertex[] walls = new vertex[w.size()];
        for(int i = 0; i< walls.length;i++){
            walls[i] = w.get(i);
        }
        return walls;
    }
    public void initObjFile() throws IOException {
        objects.add(new object(posO1));
        int stelle = objects.size()-1;
        //System.out.println("BUFFERED READER START");
        br.readLine();br.readLine();br.readLine();br.readLine();
        String s = br.readLine();
        String numberString = "";
        while (s.charAt(0)=='v'&&s.charAt(1)==' ') {
            s = s.substring(1);
            numberString=numberString+s;
            s = br.readLine();
        }
        numberString = numberString.trim();
        String[] ss = numberString.split(" ");
        double[] ii = new double[ss.length];
        for (int i = 0;i<ss.length;i++){
            ii[i] = Double.parseDouble(ss[i]);
        }
        for (int i = 0; i<ss.length; i=i+9){
            objects.get(stelle).vertices.add(new vertex(new vector(ii[i],ii[i+2],ii[i+1]),new vector(ii[i+3],ii[i+5],ii[i+4]),new vector(ii[i+6],ii[i+8],ii[i+7])));
        }

        //System.out.println("BUFFERED READER END");
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W)up=true;
        if(e.getKeyCode()==KeyEvent.VK_S)down=true;
        if(e.getKeyCode()==KeyEvent.VK_A)left=true;
        if(e.getKeyCode()==KeyEvent.VK_D)right=true;
        if(e.getKeyCode()==KeyEvent.VK_Y)forward=true;
        if(e.getKeyCode()==KeyEvent.VK_C)back=true;
        if(e.getKeyCode()==KeyEvent.VK_E)objects.get(controller).active=!objects.get(controller).active;
        if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_SPACE){
            controller++;
            if(controller==objects.size())controller=0;
            sliders.get(0).value=objects.get(controller).scale;
            sliders.get(1).value=objects.get(controller).rotateX;
            sliders.get(2).value=objects.get(controller).rotateY;
            sliders.get(3).value=objects.get(controller).rotateZ;
            for (slider s : sliders){
                s.sliderDox=(int) (((s.value/s.max)*(s.sliderwidth/(s.max/s.min))*(s.max/s.min))+s.sliderBarx);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            controller--;
            if(controller==-1)controller=objects.size()-1;
            sliders.get(0).value=objects.get(controller).scale;
            sliders.get(1).value=objects.get(controller).rotateX;
            sliders.get(2).value=objects.get(controller).rotateY;
            sliders.get(3).value=objects.get(controller).rotateZ;
            for (slider s : sliders){
                s.sliderDox=(int) (((s.value/s.max)*(s.sliderwidth/(s.max/s.min))*(s.max/s.min))+s.sliderBarx);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W)up=false;
        if(e.getKeyCode()==KeyEvent.VK_S)down=false;
        if(e.getKeyCode()==KeyEvent.VK_A)left=false;
        if(e.getKeyCode()==KeyEvent.VK_D)right=false;
        if(e.getKeyCode()==KeyEvent.VK_Y)forward=false;
        if(e.getKeyCode()==KeyEvent.VK_C)back=false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (slider s : sliders){
            s.checkSelf(this);
        }
        click=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (slider s : sliders){
            s.followMouse=false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}