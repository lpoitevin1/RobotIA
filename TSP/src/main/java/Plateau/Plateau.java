package Plateau;


import config.Configuration;
import config.GraphConfig;

import graph.Graphe;
import graph.Noeud;


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

    /**
     * Affiche les parametres de la partie
     * @return string
     */
    public String print(){
        String s = "";
        for (int i = 0 ; i < robots.length ; i++) {
            s += "robot " + i + " : "+ robots[i].getNom() + " ["+robots[i].getX()+","+robots[i].getY()+"] \n";
        }
        s+= "objectif : "+ objectif.getNom() + " ["+objectif.getX()+","+objectif.getY()+"] \n";
        return s;
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


    /**
     * Initialisation de tous les robots aux noeuds
     * @param n1 Noeuds n1 -> (robot 0)
     * @param n2 Noeuds n2 -> (robot 1)
     * @param n3 Noeuds n3 -> (robot 2)
     */
    public void setRobots(Noeud n1, Noeud n2, Noeud n3) {
        this.robots[0] = n1;
        this.robots[1] = n2;
        this.robots[2] = n3;

    }

    /**
     * Initialisation de la position d'un robot a un noeud du graphe
     * @param n Noeud
     * @param index identifiant du robot
     */
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
                if (robots[0].getX() == n1.getX() || robots[0].getY() == n1.getY()) {
                    if (g.existeChemin_X (n1 ,robots[0]) || g.existeChemin_Y (n1 ,robots[0])) {
                        if (!g.contrainteAllignee(robots[0], robots[1], n1)
                            && !g.contrainteAllignee(robots[0], robots[2], n1)) {
                            if (!n1.samePosition(robots[1]) && !n1.samePosition(robots[2])) {
                                if(robots[0].finDeLigne(n1,robots[1],robots[2])) {
                                    conf.add(new Configuration(n1, robots[1], robots[2]));
                                }
                            }
                        }
                    }
                }

            }
        } else if (robot == 1) {
            for (Noeud n1 : g.getNodes()) {
                if (robots[1].getX() == n1.getX() || robots[1].getY() == n1.getY()) {
                    if (g.existeChemin_X(n1, robots[1]) || g.existeChemin_Y(n1, robots[1])) {
                        if (!g.contrainteAllignee(robots[1], robots[0], n1)
                            && !g.contrainteAllignee(robots[1], robots[2], n1)) {
                            if (!n1.samePosition(robots[0]) && !n1.samePosition(robots[2])) {
                                if (robots[1].finDeLigne(n1,robots[0],robots[2])) {
                                    conf.add(new Configuration(robots[0], n1, robots[2]));
                                }
                            }
                        }
                    }
                }

            }
        } else if (robot == 2) {
            for (Noeud n1 : g.getNodes()) {
                if (robots[2].getX() == n1.getX() || robots[2].getY() == n1.getY()) {
                    if (g.existeChemin_X (n1 ,robots[2]) || g.existeChemin_Y (n1 ,robots[2])) {
                        if (!g.contrainteAllignee(robots[2], robots[0], n1)
                            && !g.contrainteAllignee(robots[2], robots[1], n1)) {
                            if (!n1.samePosition(robots[0]) && !n1.samePosition(robots[1])) {
                                if(robots[2].finDeLigne(n1,robots[0],robots[1])) {
                                    conf.add(new Configuration(robots[0], robots[1], n1));
                                }
                            }
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

    /**
     * Test des contraintes pour le robot 0
     * @param destination Noeud destination
     * @return boolean
     */
    public boolean testContraintes(Noeud destination) {

        if(robots[0].getX() == destination.getX() || robots[0].getY() == destination.getY()) {
            if (g.existeChemin_X (destination ,robots[0]) || g.existeChemin_Y (destination ,robots[0])) {
                if (!g.contrainteAllignee(robots[0], robots[1], destination)
                    && !g.contrainteAllignee(robots[0], robots[2], destination)) {
                    if (!destination.samePosition(robots[1]) && !destination.samePosition(robots[2])) {
                        return robots[0].finDeLigne(destination,robots[1],robots[2]);
                    }
                }
            }
        }
        return false;
    }


    /**
     * boucle de generate & test
     * @param source Noeud source
     * @param dest Noeud destination
     * @return Configuration a atteindre si le probleme est resolvable
     *  sinon retourne la configuration source
     */
    public Configuration bruteForce(Configuration source, Noeud dest) {
        objectif = dest;
        List<Configuration> visite = new ArrayList<Configuration>();
        List<Configuration> aTraiter = new ArrayList<Configuration>();
        List<Configuration> nouveauxCoups = new ArrayList<Configuration>();
        Configuration current;
        coups = new GraphConfig(source);
        aTraiter.add(coups.getNodes().get(0));
        int configMax = (int) Math.pow(g.getNodes().size(), 3);
        boolean valide;
        while (!aTraiter.isEmpty() || coups.getNodes().size() >= configMax) {
            current = aTraiter.get(0);
            aTraiter.remove(0);
            visite.add(current);

            if (aTraiter.contains(current)) {
                aTraiter.remove(current);
            }
            robots[0] = current.getV1();
            robots[1] = current.getV2();
            robots[2] = current.getV3();
            nouveauxCoups.clear();
            for (int i = 0; i < 3; i++) {
                for (Configuration c :access(i) ) {
                    nouveauxCoups.add(c);
                }
            }

            for (Configuration c : nouveauxCoups) {

                valide = true;
                for (Configuration c2 : visite) {
                    if (c2.eq(c) ) {
                        valide = false;
                    }
                }

                for (Configuration c2 : aTraiter) {
                    if (c2.eq(c) ) {
                        valide = false;
                    }
                }

                if (valide) {
                    coups.addConfig(c);
                    current.addDualLink(c, current.generateCost(c));
                    aTraiter.add(c);
                }

                if (c.getV1().samePosition(objectif)) {
                        //sortie standard
                        return c;
                    }
                }
        }
    //sortie d'erreur
        return source;
    }


}