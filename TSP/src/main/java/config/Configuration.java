package config;

import graph.Noeud;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private Noeud [] positions;
    private List<Configuration> voisins;


    public Configuration(Noeud n1, Noeud n2, Noeud n3) {
        positions = new Noeud[3];
        positions[0] = n1;
        positions[1] = n2;
        positions[2] = n3;
        voisins = new ArrayList<Configuration>();
    }

    public Configuration(Noeud [] n) {
        positions = n;
        voisins = new ArrayList<Configuration>();

    }

    public List<Configuration> getVoisins() {
        return voisins;
    }

    public Noeud[] getPositions() {
        return positions;
    }

    public void setPositions(Noeud[] positions) {
        this.positions = positions;
    }

    public void setVoisins(List<Configuration> voisins) {
        this.voisins = voisins;
    }

    public void addVoisins(Configuration c) {
        if (!voisins.contains(c)) {
            voisins.add(c);
        }
    }


    public String afficher() {
        String s ="";

        for (Noeud p : positions) {
            s +=  p.getNom() + " ";
        }

        return s;
    }



    public boolean eq(Configuration c) {
        if (c.getPositions().length == this.positions.length) {
            for (int i = 0 ; i < c.getPositions().length ; i++) {
                if (c.getPositions()[i] != this.getPositions()[i]) {
                    return false;
                }
            }
        } else return false;

       if (c.voisins.size() == this.voisins.size()) {
           for (int i = 0 ; i < c.voisins.size() ; i++) {
               if (c.voisins.get(i) != this.voisins.get(i)) {
                   return false;
               }
           }
       } else {
           return false;
       }
       return true;
    }
}
