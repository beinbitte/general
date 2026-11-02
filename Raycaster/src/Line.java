public class Line {
    int x1,y1;
    int x2,y2;

    int ex1,ey1=0;
    int ex2,ey2=1;

    float intersectionX = 0;
    float intersectionY = 0;
    float finalintersactionx = 0;
    float finalintersactiony = 0;
    double length = 255;

    public String walltype = "nothing";

    public Line(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        vertausch(1);
    }

    public double calclength() {
        return Math.sqrt(Math.pow(intersectionX-x1,2)+Math.pow(intersectionY-y1,2));
    }

    public void setorts(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void setziel(int x2, int y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    public void vertausch(int state){
        if(state==1){
            ex1=x1;
            ey1=y1;
            ex2=x2;
            ey2=y2;
        }
        if(state==2){
            ex1=x1;
            ey1=y1;
            ex2=x1+(y2-y1);
            ey2=y1+(x2-x1);
        }
        if(state==3){
            ex1=x1;
            ey1=y1;
            ex2=x1-(x2-x1);
            ey2=y2;
        }
        if(state==4){
            ex1=x1;
            ey1=y1;
            ex2=x1+(y2-y1);
            ey2=y1-(x2-x1);
        }
    }
    public boolean checkhit(wall[] walls){
        boolean b = false;
        double lengthTemp = 0;
        for (int i = 0;i<walls.length;i++) {
           wall w = walls[i];
           if(lineLine(ex1, ey1, ex2, ey2, w.posx, w.posy, w.posx, w.posy + wall.width)){
               lengthTemp = calclength();
                if(lengthTemp<length){
                    length=lengthTemp;
                    finalintersactionx = intersectionX;
                    finalintersactiony = intersectionY;
                    walltype="left";
                }
               b = true;
           }
           if(lineLine(ex1, ey1, ex2, ey2, w.posx + wall.width, w.posy, w.posx + wall.width, w.posy + wall.width)){
               lengthTemp = calclength();
               if(lengthTemp<length){
                   length=lengthTemp;
                   finalintersactionx = intersectionX;
                   finalintersactiony = intersectionY;
                   walltype="right";

               }
               b = true;
           }
           if(lineLine(ex1, ey1, ex2, ey2, w.posx, w.posy, w.posx + wall.width, w.posy)){
               lengthTemp = calclength();
               if(lengthTemp<length){
                   length=lengthTemp;
                   finalintersactionx = intersectionX;
                   finalintersactiony = intersectionY;
                   walltype="top";
               }
               b = true;
           }
           if(lineLine(ex1, ey1, ex2, ey2, w.posx, w.posy + wall.width, w.posx + wall.width, w.posy + wall.width)){
               lengthTemp = calclength();
               if(lengthTemp<length){
                   length=lengthTemp;
                   finalintersactionx = intersectionX;
                   finalintersactiony = intersectionY;
                   walltype="bottom";

               }
               b = true;
           }

        }
        return b;
    }

    public boolean lineLine(int x1,int y1,int x2,int y2,int x3,int y3, int x4, int y4) {


        float uA = (float) ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / (float)((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));
        float uB = (float) ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / (float)((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));

        if(uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1){

            intersectionX = x1 + (uA * (x2-x1));
            intersectionY = y1 + (uA * (y2-y1));
            return true;
        }
        return false;
    }
}
