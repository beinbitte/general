import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

public class slider {
    int sliderBarx = 100;int sliderBary = 50;int sliderDox = 100;int sliderDotR;
    int sliderwidth=1800;int slideHeight=20;boolean followMouse;double min=0.01;double max=10;double value=1;
    String label;
    public slider(int x,int y,int width,int height,double min,double max,String label){
        sliderBarx=x;
        sliderBary=y;
        sliderwidth=width;
        slideHeight=height;
        this.min=min;
        this.max=max;
        sliderDotR=slideHeight*2;
        this.label = label;
        sliderDox=(int) (((value/max)*(sliderwidth/(max/min))*(max/min))+sliderBarx);
    }
    public slider(int x,int y,int width,int height,double min,double max,String label,double startValue){
        sliderBarx=x;
        sliderBary=y;
        sliderwidth=width;
        slideHeight=height;
        this.min=min;
        this.max=max;
        sliderDotR=slideHeight*2;
        this.label = label;
        value = startValue;
        sliderDox=(int) (((value/max)*(sliderwidth/(max/min))*(max/min))+sliderBarx);
    }
    public slider(){}
    public void drawSlider(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(sliderBarx,sliderBary,sliderwidth,slideHeight);
        g.setColor(Color.blue);
        g.fillOval(sliderDox-sliderDotR/4,sliderBary-sliderDotR/4,sliderDotR,sliderDotR);
        g.drawString(label,(sliderBarx/2)-((label.length()*5)/2),sliderBary+slideHeight/2);
    }
    public void sliderMove(JFrame f) {

        if (followMouse) {
            if (f.getMousePosition()==null)return;
            sliderDox = f.getMousePosition().x;
            if (sliderDox < sliderBarx) sliderDox = sliderBarx;
            if (sliderDox > sliderBarx + sliderwidth) sliderDox = sliderBarx + sliderwidth;
            value=max*((sliderDox-sliderBarx)/(max/min))/(sliderwidth/(max/min));
        }
    }
    public void checkSelf(JFrame f){
        if(f.getMousePosition().x<=sliderBarx+sliderwidth&&f.getMousePosition().x>=sliderBarx&&f.getMousePosition().y+f.getMousePosition().y/30<=sliderBary+slideHeight&&f.getMousePosition().y+f.getMousePosition().y/30>=sliderBary)followMouse=true;
    }
}
