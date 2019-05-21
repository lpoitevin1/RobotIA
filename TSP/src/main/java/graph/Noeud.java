package graph;


import java.util.ArrayList;
import java.util.List;

public class Noeud {
    private int idNoeud;
    private String nom;
    private List<Arc> arcs;
    private double x;
    private double y;




    Noeud (int id ) {
        arcs = new ArrayList<Arc>();
        idNoeud = id;
    }

    Noeud (int id , String str) {
        arcs = new ArrayList<Arc>();
        idNoeud = id;
        nom = str;
    }

    Noeud (int id , String str,double posX, double posY) {
        arcs = new ArrayList<Arc>();
        idNoeud = id;
        nom = str;
        x = posX;
        y = posY;
    }

    Noeud(){}


    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    public void setArcs(List<Arc> arcs) {
        this.arcs = arcs;
    }

    public void setIdNoeud(int idNoeud) {
        this.idNoeud = idNoeud;
    }

    public int getIdNoeud() {

        return idNoeud;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    /**
     * ajoute l'arete this -> n2
     * @param n2 Noeud
     */
    public void addLink(Noeud n2 , double cost, char direction) {
        Arc a = new Arc(this,n2, cost, direction);
        if (!arcs.contains(a)) {
            arcs.add(a);
        }
    }


    /**
     * ajoute les aretes this -> n2 , n2 -> this
     * @param n2 Noeud
     */
    /*
    public void addDualLink(Noeud n2 , double cost) {
        Arc a1 = new Arc(this,n2, cost);
        Arc a2 = new Arc(n2,this, cost);
        if (!arcs.contains(a1)) {
            arcs.add(a1);
        }
        if (!n2.arcs.contains(a2)) {
            n2.arcs.add(a2);
        }
    }
    */

    /**
     * affiche les arcs
     */
    public void printArcs() {
        for(Arc a : arcs) {
            System.out.println(a.getDebut().getNom()
                + " -> " + a.getFin().getNom()
                +" cout : "+a.getCout());
        }
    }


    /**
     * calcule la distance pour parcourir l'arete (this,n2) si elle existe sinon retourne Double.MaxValue
     * @param n2 Noeud arrivée
     * @return distance de l'arete
     */
    public double calculerDistance (Noeud n2) {
        for(Arc a : arcs) {
            if (a.getDebut().equals(this) && a.getFin().equals(n2)) {
                return a.getCout();
            }
        }
        return Double.MAX_VALUE;
    }



    public boolean equalsNode(Noeud n) {

        for (int i = 0 ; i < this.arcs.size() ; i++) {
            if (!this.arcs.get(i).isEquals(n.getArcs().get(i))) {
                return false;
            }
        }
        return (this.idNoeud == n.getIdNoeud())
            && (this.nom == n.getNom());

    }
}




