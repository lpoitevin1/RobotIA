package graph;


public class Noeud {
    private int idNoeud;
    private String nom;
    private double posX;
    private double posY;



    Noeud (int id , String str) {
        idNoeud = id;
        nom = str;
        posX = 0;
        posY = 0;
    }

    Noeud (int id , String str, double x , double y) {
        idNoeud = id;
        nom = str;
        posX = x;
        posY = y;

    }


    public int getIdNoeud() {
        return idNoeud;
    }

    public String getNom() {
        return nom;
    }

    public double gePosX() {
        return posX;
    }

    public double gePosY() {
        return posY;
    }
}




