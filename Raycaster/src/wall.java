import java.awt.*;

public class wall {
    int posx = 0;
    int posy = 0;
    static int width = 60;
    static Color color = Color.BLACK;
    public wall(int posx,int posy){
        this.posx = posx;
        this.posy = posy;
    }
    public void setPosx(int posx) {
        this.posx = posx;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public static void setWidth(int width) {
        wall.width = width;
    }

    public boolean checkbox(player p){
        return p.posx + p.size > posx && p.posx < posx + width && p.posy + p.size > posy && p.posy < posy + width;
    }

}
