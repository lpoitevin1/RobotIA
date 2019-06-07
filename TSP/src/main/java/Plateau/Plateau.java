package Plateau;

import config.Configuration;
import config.GraphConfig;
import graph.Graphe;
import graph.Noeud;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private Graphe g = new Graphe();
    private Noeud[] robots;
    private Noeud objectif;
    private GraphConfig coups;


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

    public GraphConfig getCoups() {
        return coups;
    }

    public void setCoups(GraphConfig coups) {
        this.coups = coups;
    }

    /**
     * Generation des configuration voisines
     * @param robot Robot
     * @return Liste de configuration
     */
    public List<Configuration> access(int robot) {
        List<Configuration> conf = new ArrayList<Configuration>();
        if (robot == 0) {
            for (Noeud n1 : g.getNodes()) {
                if(robots[0].getX() == n1.getX() || robots[0].getY() == n1.getY()) {
                    if(!g.contrainteAllignee(robots[0],robots[1],n1)
                        && !g.contrainteAllignee(robots[0],robots[2],n1)) {
                        if (!n1.samePosition(robots[1]) && !n1.samePosition(robots[2])) {
                            conf.add(new Configuration(n1, robots[1], robots[2]));
                        }
                    }
                }

            }
        } else if (robot == 1) {
            for (Noeud n1 : g.getNodes()) {
                if (robots[1].getX() == n1.getX() || robots[1].getY() == n1.getY()) {
                    if(!g.contrainteAllignee(robots[1],robots[0],n1)
                        && !g.contrainteAllignee(robots[1],robots[2],n1)) {
                        if (!n1.samePosition(robots[0]) && !n1.samePosition(robots[2])) {
                            conf.add(new Configuration(robots[0], n1, robots[2]));
                        }
                    }
                }

            }
        } else if (robot == 2) {
            for (Noeud n1 : g.getNodes()) {
                if (robots[2].getX() == n1.getX() || robots[2].getY() == n1.getY()) {
                    if(!g.contrainteAllignee(robots[2],robots[0],n1)
                        && !g.contrainteAllignee(robots[2],robots[1],n1)) {
                        if (!n1.samePosition(robots[0]) && !n1.samePosition(robots[1])) {
                            conf.add(new Configuration(robots[0], robots[1], n1));
                        }
                    }
                }

            }

        } else return conf;

        for(Configuration c1 : conf) {
            //System.out.println(c1.printConfig());
        }
        return conf;
    }



    public Configuration bruteForce(Configuration source, Noeud dest) {
        objectif = dest;
        List<Configuration> visite = new ArrayList<Configuration>();
        List<Configuration> aTraiter = new ArrayList<Configuration>();
        List<Configuration> nouveauxCoups;
        Configuration current;
        coups = new GraphConfig(source);
        aTraiter.add(coups.getNodes().get(0));
        while (!aTraiter.isEmpty()) {
            current = aTraiter.get(0);
            aTraiter.remove(0);
            visite.add(current);

            robots[0] = current.getV1();
            robots[1] = current.getV2();
            robots[2] = current.getV3();
            for (int i = 0; i < 3; i++) {
                nouveauxCoups = access(i);
                for(Configuration c : nouveauxCoups) {
                    if (!visite.contains(c)) {
                        aTraiter.add(c);
                        coups.addConfig(c);
                        current.addDualLink(c,current.generateCost(c));
                    }
                    if (c.getV1().samePosition(objectif)) {
                        return c;
                    }
                }
            }
        }
        return new Configuration();
    }

}