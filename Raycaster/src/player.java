import java.awt.*;

public class player {
    int posx = 134;
    int posy = 134;
    int size = 10;
    Color color = Color.black;
    int rotation = 1; //oben:4    links:3     rechts:1      unten:2
    Line[] lines = new Line[21];
    public void setColor(Color color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void rotaterechts() {
        rotation++;
        if(rotation==5)rotation=1;

    }
    public void rotatelinks() {
        rotation--;
        if(rotation==0)rotation=4;

    }
    public boolean checkhitbox(wall[] walls, player p){
        boolean b = false;
        for(wall w : walls){
            if(w.checkbox(p))b = true;
        }
        return b;
    }

    public void lineaccept(){
        for(int i = 0;i<lines.length;i++){
            Line l = lines[i];
            l.calclength();
            l.vertausch(rotation);
        }
    }
}
