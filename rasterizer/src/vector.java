
public class vector {
    double x,y,z;


    public vector(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public vector(vector v2){
        this.x = v2.x;
        this.y = v2.y;
        this.z = v2.z;
    }
    public void printV(){
        System.out.println("x; "+x+" y; "+y+" z; "+z);
    }
    public static vector kreuzprodukt(vector a, vector b, vector c){
        vector ab = new vector(b.x-a.x,b.y-a.y,b.z-a.z);
        vector ac = new vector(c.x-a.x,c.y-a.y,c.z-a.z);
        return new vector(ab.y*ac.z-ab.z*ac.y,ab.z*ac.x-ab.x*ac.z,ab.x*ac.y-ab.y*ac.x);
    }
    public static vector kreuzprodukt(vector a, vector b){
        return new vector(a.y*b.z-a.z*b.y,a.z*b.x-a.x*b.z,a.x*b.y-a.y*b.x);
    }
    public vector add(vector v2) {
        return new vector(this.x + v2.x, this.y + v2.y, this.z + v2.z);
    }
    public vector subtraction(vector v2){
        return new vector(this.x - v2.x, this.y - v2.y, this.z - v2.z);
    }
    public vector kleiner(){
        vector v = new vector(x,y,z);
        if(v.x!=0) {
            double a = Math.abs(v.x);
            v.x /= a;
            v.y /= a;
            v.z /= a;
        }else if (v.y!=0){
            double a = Math.abs(v.y);
            v.x /= a;
            v.y /= a;
            v.z /= a;
        }else {
            double a = Math.abs(v.z);
            v.x /= a;
            v.y /= a;
            v.z /= a;
        }
        return v;
    }
    public vector negieren(){
        return new vector(-x,-y,-z);
    }
    public vector multiply(double d){
        return new vector(x*d,y*d,z*d);
    }
    public double lange(){
        return Math.sqrt(x*x+y*y+z*z);
    }
    public double skalarProdukt(vector v2){
        return this.x* v2.x+(this.y)* v2.y+(this.z)* v2.z;
    }

    public static vector get(vector v2) {
        return new vector(v2.x, v2.y, v2.z);
    }
}
