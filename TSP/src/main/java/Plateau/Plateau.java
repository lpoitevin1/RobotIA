package Plateau;

import graph.Graphe;
import graph.Noeud;

public class Plateau {
    private Graphe g = new Graphe();
    private Noeud [] robots;
    private Noeud objectif;


    public Plateau (String fichierNode, String fichierArc) {
        robots = new Noeud[3];
        g.construirereGrapheLecture(fichierNode, fichierArc);
    }

    public Graphe getG() {
        return g;
    }

    public Noeud getObjectif() {
        return objectif;
    }

    public Noeud[] getRobots() {
        return robots;
    }

    public void setG(Graphe g) {
        this.g = g;
    }

    public void setObjectif(Noeud objectif) {
        this.objectif = objectif;
    }

    public void setRobots(Noeud n1, Noeud n2, Noeud n3) {
        this.robots[0] = n1;
        this.robots[1] = n2;
        this.robots[2] = n3;

    }

    public void setRobots(Noeud n, int index) {
        robots[index] = n;
    }


}
