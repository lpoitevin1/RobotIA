package graph;


import java.util.ArrayList;
import java.util.List;

public class Noeud {
    private int idNoeud;
    private String nom;
    private List<Arc> arcs;
    private double x;
    private double y;




    public Noeud (int id ) {
        arcs = new ArrayList<Arc>();
        idNoeud = id;
    }

    public Noeud (int id , String str) {
        arcs = new ArrayList<Arc>();
        idNoeud = id;
        nom = str;
    }

    public Noeud (int id , String str,double posX, double posY) {
        arcs = new ArrayList<Arc>();
        idNoeud = id;
        nom = str;
        x = posX;
        y = posY;
    }

    public Noeud(){}


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
    public void addLink(Noeud n2 , double cost) {
        Arc a = new Arc(this,n2, cost);
        if (!arcs.contains(a)) {
            arcs.add(a);
        }
    }


    /**
     * ajoute les aretes this -> n2 , n2 -> this
     * @param n2 Noeud
     */

    public void addDualLink(Noeud n2 , double cost) {
        this.addLink(n2,cost);
        n2.addLink(this,cost);
    }


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

    /**
     * Valeure absolue de la difference abscisse
     * @param n2 Noeud
     * @return double
     */
    public double diffX(Noeud n2) {
        return Math.abs(this.x - n2.x);
    }

    /**
     * Valeur absolue de la difference ordonée
     * @param n2 Noeud
     * @return double
     */
    public double diffY(Noeud n2) {
        return Math.abs(this.y - n2.y);
    }

    /**
     * egalité des x et y
     * @param n Noeud
     */
    public boolean samePosition( Noeud n) {
        return this.x == n.x && this.y == n.y;
    }


    /**
     * Calcule la distance euclidienne au carré entre 2 noeuds
     * @param n2
     * @return
     */
    public double distEuclidianNode(Noeud n2) {
        return (Math.pow(this.x - n2.x, 2)
            + Math.pow(this.y - n2.y, 2));
    }


}




