import java.util.ArrayList;
public class object {
    ArrayList<vertex> vertices = new ArrayList<>();
    vector pos;
    double scale=1;
    boolean active = true;
    boolean spinxy = false;
    boolean spinxz = false;
    boolean spinyz = false;
    double rotateX=3.14;double rotateY=3.14;double rotateZ=3.14;
    String name="placeholder";
    public object(vector v){
        pos = v;
    }



    public void rotateObjEnhanced(String s, double rotate){
        if (s.equalsIgnoreCase("xy")) {
            for (vertex v : vertices) {

                v.rotatexy = rotate;
                rotateX = rotate;
            }
        } else if (s.equalsIgnoreCase("yz")) {
            for (vertex v : vertices) {

                v.rotatexz = rotate;
                rotateY = rotate;
            }

        }else if (s.equalsIgnoreCase("xz")) {
            for (vertex v : vertices) {
                v.rotateyz=rotate;
                rotateZ=rotate;


            }
        }
    }
    public void rotateObjEnhanced(String s, double rotate,boolean spin){
        for (vertex v : vertices) {
            vector temp1 = new vector(v.op1);
            vector temp2 = new vector(v.op2);
            vector temp3 = new vector(v.op3);
            if (s.equalsIgnoreCase("xy")) {
                v.op1.x = (temp1.x*Math.cos(rotate)-temp1.y*Math.sin(rotate));
                v.op2.x = (temp2.x*Math.cos(rotate)-temp2.y*Math.sin(rotate));
                v.op3.x = (temp3.x*Math.cos(rotate)-temp3.y*Math.sin(rotate));
                v.op1.y = (temp1.x*Math.sin(rotate)+temp1.y*Math.cos(rotate));
                v.op2.y = (temp2.x*Math.sin(rotate)+temp2.y*Math.cos(rotate));
                v.op3.y = (temp3.x*Math.sin(rotate)+temp3.y*Math.cos(rotate));
            } else if (s.equalsIgnoreCase("yz")) {
                v.op1.y = (temp1.y*Math.cos(rotate)-temp1.z*Math.sin(rotate));
                v.op2.y = (temp2.y*Math.cos(rotate)-temp2.z*Math.sin(rotate));
                v.op3.y = (temp3.y*Math.cos(rotate)-temp3.z*Math.sin(rotate));
                v.op1.z = (temp1.y*Math.sin(rotate)+temp1.z*Math.cos(rotate));
                v.op2.z = (temp2.y*Math.sin(rotate)+temp2.z*Math.cos(rotate));
                v.op3.z = (temp3.y*Math.sin(rotate)+temp3.z*Math.cos(rotate));
            }else if (s.equalsIgnoreCase("xz")) {
                v.op1.x = (temp1.x*Math.cos(rotate)-temp1.z*Math.sin(rotate));
                v.op2.x = (temp2.x*Math.cos(rotate)-temp2.z*Math.sin(rotate));
                v.op3.x = (temp3.x*Math.cos(rotate)-temp3.z*Math.sin(rotate));
                v.op1.z = (temp1.x*Math.sin(rotate)+temp1.z*Math.cos(rotate));
                v.op2.z = (temp2.x*Math.sin(rotate)+temp2.z*Math.cos(rotate));
                v.op3.z = (temp3.x*Math.sin(rotate)+temp3.z*Math.cos(rotate));
            }
        }
    }
    public void updatePosVertices(){
        for (vertex v : vertices){
            v.posOffset=pos;
            v.scale = scale;
            v.offsetEnable();
        }
    }
    public void printObject(){
        for (vertex v : vertices)v.printVertex();
    }
}
