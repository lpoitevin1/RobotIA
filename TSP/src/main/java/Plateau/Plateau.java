package Plateau;

import config.Configuration;
import graph.Graphe;
import graph.Noeud;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private Graphe g = new Graphe();
    private Noeud[] robots;
    private Noeud objectif;


    public Plateau(String fichierNode, String fichierArc) {
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


    /**
     * Generation des configuration voisines
     * @param robot Robot
     * @return Liste de configuration
     */
    public List<Configuration> access(int robot) {
        List<Configuration> conf = new ArrayList<Configuration>();
        if(robot == 0) {
            for (Noeud n1 : g.getNodes()) {
                if(robots[0].getX() == n1.getX() || robots[0].getY() == n1.getY()) {
                    if (!n1.samePosition(robots[1]) && !n1.samePosition(robots[2])) {
                            conf.add( new Configuration(n1,robots[1],robots[2]));
                    }
                }

            }
        } else if (robot == 1){
            for (Noeud n1 : g.getNodes()) {
                if(robots[1].getX() == n1.getX() || robots[1].getY() == n1.getY()) {
                    if (!n1.samePosition(robots[0]) && !n1.samePosition(robots[2])) {
                        conf.add( new Configuration(robots[0],n1,robots[2]));
                    }
                }

            }
        } else if (robot == 2){
            for (Noeud n1 : g.getNodes()) {
                if(robots[2].getX() == n1.getX() || robots[2].getY() == n1.getY()) {
                    if (!n1.samePosition(robots[0]) && !n1.samePosition(robots[1])) {
                        conf.add( new Configuration(robots[0],robots[1],n1));
                    }
                }

            }

        } else return conf;

        for(Configuration c1 : conf) {
            System.out.println(c1.printConfig());
        }


        return conf;
    }

}