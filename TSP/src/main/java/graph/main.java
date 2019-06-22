package graph;

import Plateau.Plateau;
import config.Configuration;
import config.Dijkstra;

public class main {

    public static void main(String[] args) {

        /**
         *
         *
         *
         * 20   21  22  23  24
         * 15   X  17  X  19
         * 10   X  X   X  14
         * 5    6   7   8   9
         * 0    1   2   3   4
         */

        Plateau p = new Plateau("node_grille_mur5x5", "link_grille_mur5x5");
        Graphe g = p.getG();

        Noeud r1 = g.getNodes().get(0);
        Noeud r2 = g.getNodes().get(1);
        Noeud r3 = g.getNodes().get(2);

        //Noeud obj = g.getNodes().get(17);
        Noeud obj = g.findNode(2,3);

        p.setRobots(r1, r2, r3);
        p.setObjectif(obj);

        Configuration source = new Configuration(p.getRobots());
        Configuration result = p.bruteForce(source, p.getObjectif());

        if (source != result) {
            Dijkstra dik = new Dijkstra(p.getCoups());
            dik.plusCoutChemin(source, result);
        }
    }
}

