public class ray {
    vector origin;
    vector richtung;
    boolean hit = false;
    double r = 1;
    int rP = 0;//x
    int sP = 0;//y
    public ray(vector origin, vector richtung){
        this.origin = origin;
        this.richtung = richtung;
    }

}
