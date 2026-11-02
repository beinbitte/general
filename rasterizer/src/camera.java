public class camera {
    static int maxHeight = 1080;
    static int maxWidth = 1960;
    vector oPlane = new vector(-100,0,0);
    vector posXPlane = new vector(1,0,0);
    vector posYPlane = new vector(0,1,0);

    vector normaleDerEbene = vector.kreuzprodukt(posXPlane,posYPlane).kleiner();
    static vector pivot;
    public camera(){
        pivot=oPlane.add(normaleDerEbene.multiply(-1));
    }
    public void changePerspective(int p){
        switch (p){
            case 0:
                posXPlane = new vector(1,0,0);
                posYPlane = new vector(0,1,0);
                normaleDerEbene = vector.kreuzprodukt(posXPlane,posYPlane).kleiner();
                pivot=oPlane.add(normaleDerEbene.multiply(-1));
                break;
            case 1:
                posXPlane = new vector(1,0,0);
                posYPlane = new vector(0,0,1);
                normaleDerEbene = vector.kreuzprodukt(posXPlane,posYPlane).kleiner();
                pivot=oPlane.add(normaleDerEbene.multiply(-1));
                break;
            case 2:
                posXPlane = new vector(0,1,0);
                posYPlane = new vector(0,0,1);
                normaleDerEbene = vector.kreuzprodukt(posXPlane,posYPlane).kleiner();
                pivot=oPlane.add(normaleDerEbene.multiply(-1));
                break;
            case 3:
                posXPlane = new vector(1,0,0);
                posYPlane = new vector(0,-1,0);
                normaleDerEbene = vector.kreuzprodukt(posXPlane,posYPlane).kleiner();
                pivot=oPlane.add(normaleDerEbene.multiply(-1));
                break;
            case 4:
                posXPlane = new vector(1,0,0);
                posYPlane = new vector(0,0,-1);
                normaleDerEbene = vector.kreuzprodukt(posXPlane,posYPlane).kleiner();
                pivot=oPlane.add(normaleDerEbene.multiply(-1));
                break;
            case 5:
                posXPlane = new vector(0,1,0);
                posYPlane = new vector(0,0,-1);
                normaleDerEbene = vector.kreuzprodukt(posXPlane,posYPlane).kleiner();
                pivot=oPlane.add(normaleDerEbene.multiply(-1));
                break;
        }
    }
    public void moveCam(vector v){
        oPlane = oPlane.add(v);
        pivot = pivot.add(v);
    }
}
