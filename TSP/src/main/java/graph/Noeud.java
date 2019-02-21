package graph;


public class Noeud {
    private int idNoeud;
    private String nom;
    private boolean visite;




    Noeud (int id , String str) {
        idNoeud = id;
        nom = str;
        visite = false;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setVisite(boolean visite) {
        this.visite = visite;
    }

    public boolean isVisite() {
        return visite;
    }

    public void setIdNoeud(int idNoeud) {
        this.idNoeud = idNoeud;
    }

    public int getIdNoeud() {

        return idNoeud;
    }


}




