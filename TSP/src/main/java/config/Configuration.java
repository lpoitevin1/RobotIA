package config;
import graph.Noeud;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private List<ArcConfig> voisin;
    private int iD;
    private Noeud v1;
    private Noeud v2;
    private Noeud v3;


    public Configuration(){};

    public Configuration(Noeud n1, Noeud n2, Noeud n3) {
        v1 = n1;
        v2 = n2;
        v3 = n3;
        voisin = new ArrayList<ArcConfig>();
    }


    public Configuration(Noeud [] robots) {
        v1 = robots[0];
        v2 = robots[1];
        v3 = robots[2];
        voisin = new ArrayList<ArcConfig>();
    }


    public Configuration(int id ,Noeud n1, Noeud n2, Noeud n3) {
        iD = id;
        v1 = n1;
        v2 = n2;
        v3 = n3;
        voisin = new ArrayList<ArcConfig>();
    }


    public List<ArcConfig> getVoisins() {
        return voisin;
    }

    public List<ArcConfig> getVoisin() {
        return voisin;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public Noeud getV1() {
        return v1;
    }

    public Noeud getV2() {
        return v2;
    }

    public Noeud getV3() {
        return v3;
    }

    public void setV1(Noeud v1) {
        this.v1 = v1;
    }

    public void setV2(Noeud v2) {
        this.v2 = v2;
    }

    public void setV3(Noeud v3) {
        this.v3 = v3;
    }

    public void setVoisin(List<ArcConfig> voisin) {
        this.voisin = voisin;
    }



    /**
     * Egalité entre 2 configurations
     * @param c configuration a tester
     * @return boolean
     */
    public boolean eq(Configuration c) {
       return (this.getV1().samePosition( c.v1)
           && this.getV2().samePosition(c.v2)
           && this.getV3().samePosition(c.v3));
    }


    /**
     * genere le cout d'une arete pour une 2 configurations
     * @param c2
     * @return double
     */
    public double generateCost(Configuration c2){
        return v1.distEuclidianNode(c2.v1)
            + v2.distEuclidianNode(c2.v2)
            + v3.distEuclidianNode(c2.v3);
    }




    /**
     * Ajoute un lien
     * @param n2 config
     * @param cost cout
     */
    public void addLink(Configuration n2 , double cost) {
        ArcConfig a = new ArcConfig(this,n2, cost);
        if (!voisin.contains(a) && !this.eq(n2)) {
            voisin.add(a);
        }
    }



    /**
     * ajoute les aretes this -> n2 , n2 -> this
     * @param n2 Noeud
     */

    public void addDualLink(Configuration n2 , double cost) {
        this.addLink(n2,cost);
        n2.addLink(this,cost);
    }



    public String printConfig() {
        return " [ " + v1.getNom() +" ; "+  v2.getNom() +" ; "+  v3.getNom() +" ] ";
    }

    public String printConfigX_Y() {
        return " [ (" + v1.getX()+ ";"+ v1.getY() +") ; ("
            + v2.getX()+ ";"+ v2.getY() +") ; ("
            + v3.getX()+ ";"+ v3.getY() +")] ";
    }




}
