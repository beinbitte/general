import java.awt.*;

public class vertex {
    vector op1, op2, op3;
    vector p1, p2, p3;
    vector t1,t2,t3;
    vector normal;
    ray r1;
    ray r2;
    ray r3;
    ray[] rays = new ray[3];
    double rotatexy=3.14;
    double rotatexz=3.14;
    double rotateyz=3.14;
    double scale = 1;
    int[] x2dPos = new int[3];
    int[] y2dPos = new int[3];
    Color color = Color.white;
    vector posOffset;
    static int colorShift = 0;
    static int weight=0;
    static int weightScale=1;
    Color[] ca = {Color.lightGray,Color.GREEN,Color.RED,Color.YELLOW,Color.MAGENTA,Color.CYAN};
    double lange;
    Color faceColor;
    public vertex(vector v1, vector v2, vector v3){
        op1=v1;
        op2=v2;
        op3=v3;
        p1=v1;
        p2=v2;
        p3=v3;
        setNormal();
        rays[0] = r1 = new ray(p1,camera.pivot.subtraction(p1));
        rays[1] = r2 = new ray(p2,camera.pivot.subtraction(p2));
        rays[2] = r3 = new ray(p3,camera.pivot.subtraction(p3));
        color = ca[colorShift];
        weight++;
        if(weight==weightScale) {
            //colorShift++;
            if (colorShift == ca.length) colorShift = 0;
            weight=0;
        }
        faceColor = color;
    }


    private void setNormal() {
        normal = vector.kreuzprodukt(p1,p2,p3).kleiner();
    }

    public void printVertex(){
        p1.printV();
        p2.printV();
        p3.printV();
    }
    public void checkHit(camera cam){
        int i = 0;
        double sum=0;
        for (ray r : rays){
            vector normaleEbene = cam.normaleDerEbene;
            /*
            double linkegleichungAbs = r.origin.x*normaleEbene.x+r.origin.y*normaleEbene.y+r.origin.z*normaleEbene.z;
            double linkegleichungr = r.richtung.x*normaleEbene.x+r.richtung.y*normaleEbene.y+r.richtung.z*normaleEbene.z;
            double rechts = cam.oPlane.x*normaleEbene.x+cam.oPlane.y*normaleEbene.y+cam.oPlane.z*normaleEbene.z;
            rechts = rechts-linkegleichungAbs;
            if (r.richtung.lange()<=0)return;
            if(linkegleichungr == 0)return;
            rechts=rechts/linkegleichungr;
            r.r = rechts;
            if(rechts <=0||rechts>1)return;
            r.hit=true;
            */

            if(r.richtung.x*normaleEbene.x+r.richtung.y*normaleEbene.y+r.richtung.z*normaleEbene.z==0)return;
            r.r = ((((cam.oPlane.x-r.origin.x)*normaleEbene.x)+((cam.oPlane.y-r.origin.y)*normaleEbene.y)+(cam.oPlane.z-r.origin.z)*normaleEbene.z)/
                    (r.richtung.x*normaleEbene.x+r.richtung.y*normaleEbene.y+r.richtung.z*normaleEbene.z));
            if(r.r <=0||r.r>1)return;

            sum+=r.richtung.multiply(r.r).lange();
            if(r.r<1) {
                //r.sp und r.rp sind die pixel xy OK.
                vector konst = cam.oPlane.subtraction(r.origin).add(r.richtung.multiply(r.r));
                vector rLG = vector.get(cam.posXPlane);
                vector sLG = vector.get(cam.posYPlane);
                if(rLG.x!=0){
                    r.rP=-(int)(konst.x/rLG.x);
                }else if (rLG.y!=0){
                    r.rP=-(int)(konst.y/rLG.y);
                }else if (rLG.z!=0){
                    r.rP=-(int)(konst.z/rLG.z);
                }
                if(sLG.x!=0){
                    r.sP=-(int)(konst.x/sLG.x);
                }else if (sLG.y!=0){
                    r.sP=-(int)(konst.y/sLG.y);
                }else if (sLG.z!=0){
                    r.sP=-(int)(konst.z/sLG.z);
                }

                /*
                if(rLG.x==0){
                    if(sLG.x!=0)r.sP=-(int)(konst.x/sLG.x);
                } else if(rLG.y==0){
                    if (sLG.y!=0)r.sP=-(int)(konst.y/sLG.y);
                } else if(rLG.z==0){
                    if (sLG.z!=0)r.sP=-(int)(konst.z/sLG.z);
                }
                if(sLG.x==0){
                    if(rLG.x!=0)r.rP=-(int)(konst.x/rLG.x);
                } else if(sLG.y==0){
                    if (rLG.y!=0)r.rP=-(int)(konst.y/rLG.y);
                } else if(sLG.z==0){
                    if (rLG.z!=0)r.rP=-(int)(konst.z/rLG.z);
                }
                */
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!PROBLEMSTELLE
                //r.rP = (int) (r.origin.subtraction(camera.pivot).x - r.richtung.multiply(r.r).x);
                //r.sP = (int) (r.origin.subtraction(camera.pivot).y - r.richtung.multiply(r.r).y);
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!PROBLEMSTELLE

                x2dPos[i] = r.rP;
                y2dPos[i] = r.sP;
            }
            i++;
        }
        lange=sum/3;
    }

    public void offsetEnable(){

        p1 = op1.add(posOffset);
        p2 = op2.add(posOffset);
        p3 = op3.add(posOffset);

        p1.x = (op1.x*scale);
        p2.x = (op2.x*scale);
        p3.x = (op3.x*scale);
        p1.y = (op1.y*scale);
        p2.y = (op2.y*scale);
        p3.y = (op3.y*scale);
        p1.z = (op1.z*scale);
        p2.z = (op2.z*scale);
        p3.z = (op3.z*scale);

        t1=new vector(p1);
        t2=new vector(p2);
        t3=new vector(p3);
                p1.x = (t1.x*Math.cos(rotatexy)-t1.y*Math.sin(rotatexy));
                p2.x = (t2.x*Math.cos(rotatexy)-t2.y*Math.sin(rotatexy));
                p3.x = (t3.x*Math.cos(rotatexy)-t3.y*Math.sin(rotatexy));
                p1.y = (t1.x*Math.sin(rotatexy)+t1.y*Math.cos(rotatexy));
                p2.y = (t2.x*Math.sin(rotatexy)+t2.y*Math.cos(rotatexy));
                p3.y = (t3.x*Math.sin(rotatexy)+t3.y*Math.cos(rotatexy));
        t1=new vector(p1);
        t2=new vector(p2);
        t3=new vector(p3);
                p1.x = (t1.x*Math.cos(rotateyz)-t1.z*Math.sin(rotateyz));
                p2.x = (t2.x*Math.cos(rotateyz)-t2.z*Math.sin(rotateyz));
                p3.x = (t3.x*Math.cos(rotateyz)-t3.z*Math.sin(rotateyz));
                p1.z = (t1.x*Math.sin(rotateyz)+t1.z*Math.cos(rotateyz));
                p2.z = (t2.x*Math.sin(rotateyz)+t2.z*Math.cos(rotateyz));
                p3.z = (t3.x*Math.sin(rotateyz)+t3.z*Math.cos(rotateyz));
        t1=new vector(p1);
        t2=new vector(p2);
        t3=new vector(p3);
                p1.y = (t1.y*Math.cos(rotatexz)-t1.z*Math.sin(rotatexz));
                p2.y = (t2.y*Math.cos(rotatexz)-t2.z*Math.sin(rotatexz));
                p3.y = (t3.y*Math.cos(rotatexz)-t3.z*Math.sin(rotatexz));
                p1.z = (t1.y*Math.sin(rotatexz)+t1.z*Math.cos(rotatexz));
                p2.z = (t2.y*Math.sin(rotatexz)+t2.z*Math.cos(rotatexz));
                p3.z = (t3.y*Math.sin(rotatexz)+t3.z*Math.cos(rotatexz));



        p1.x+= posOffset.x;
        p2.x+= posOffset.x;
        p3.x+= posOffset.x;
        p1.y+= posOffset.y;
        p2.y+= posOffset.y;
        p3.y+= posOffset.y;
        p1.z+= posOffset.z;
        p2.z+= posOffset.z;
        p3.z+= posOffset.z;
        rays[0] = r1 = new ray(p1,camera.pivot.subtraction(p1));
        rays[1] = r2 = new ray(p2,camera.pivot.subtraction(p2));
        rays[2] = r3 = new ray(p3,camera.pivot.subtraction(p3));
        setNormal();
    }

    public void calculateColor(vector lightDir) {

        double winkel = (normal.skalarProdukt(lightDir))/(normal.lange()*lightDir.lange());
        winkel*=90;
        if(winkel<0)winkel=0;
        if(winkel>90)winkel=90;
        double mult = 1-(winkel/90);
        color = new Color( (int)(faceColor.getRed()*(mult)),  (int)(faceColor.getGreen()*(mult)),  (int)(faceColor.getBlue()*(mult)));

    }
}
