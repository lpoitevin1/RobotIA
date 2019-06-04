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
       if (c.voisin.size() == this.voisin.size()) {
           for (int i = 0 ; i < c.voisin.size() ; i++) {
               if (c.voisin.get(i) != this.voisin.get(i)) {
                   return false;
               }
           }
       } else {
           return false;
       }
       return (this.getV1().equalsNode( c.v1)
           && this.getV2().equalsNode(c.v2)
           && this.getV3().equalsNode(c.v3));
    }






    /**
     * Ajoute un lien
     * @param n2 config
     * @param cost cout
     */
    public void addLink(Configuration n2 , double cost) {
        ArcConfig a = new ArcConfig(this,n2, cost);
        if (!voisin.contains(a)) {
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




}
