package config;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    private GraphConfig g;

    public Dijkstra (GraphConfig graph) {
        g = graph;
    }


    public GraphConfig getG() {
        return g;
    }

    public void setG(GraphConfig g) {
        this.g = g;
    }

    /**
     * 1 . Calcule la table des plus court chemins et des precedence (a partir du noeud source)
     * 2 . Exploite la table pour construire le plus court chemin vers la destination
     * @param source noeud source
     * @param dest noeud destination
     * @return Liste de noeud contenant le plus court chemin source -> destination
     */
    public List<Configuration> djikstraRoutage(Configuration source, Configuration dest) {
        List<Configuration> chemin;
        List<Integer> aTraiter = new ArrayList<Integer>();
        Configuration curendNode;
        int index;

        double [] dist = new double [g.getNodes().size()];
        Configuration [] pred = new Configuration [g.getNodes().size()];
        for (Configuration n : g.getNodes()) {
            dist[n.getiD()] = Double.MAX_VALUE;
            aTraiter.add(n.getiD());
        }
        dist[source.getiD()] = 0;
        while (!aTraiter.isEmpty()) {
            //on traite le noeud ayant la distance la plus faible
            if (aTraiter.size() > 1) {
                double distMin = Double.MAX_VALUE;
                index = 0;
                for (int i = 0;i < dist.length;i++) {
                    if ((dist[i] < distMin ) && aTraiter.contains(i)) {
                        distMin = dist[i];
                        index = i;
                    }
                }
            } else  {
                // cas ou la queue n'a plus qu'un element
                index = aTraiter.get(0);
                aTraiter.remove(0);
            }
            for (int i = 0;i < aTraiter.size(); i++ ) {
                if (aTraiter.get(i).equals(index)) {
                    aTraiter.remove(i);
                    break;
                }
            }

            curendNode = g.getNodes().get(index);

            for (ArcConfig voisin : curendNode.getVoisin()) {
                //on calcule la somme la distance pour acceder au noeud actuel + le cout de l'arc
                int idNext = voisin.getFin().getiD();
                double cost = dist[index] + voisin.getCout();
                if (cost < dist[idNext]) {
                    dist[idNext] = cost;
                    pred[idNext] = curendNode;
                }
            }
        }

        //affichage txt de la table de routage
        /*System.out.println("Source : "
            + source.printConfig()+ "  Destination : "
            + dest.printConfig() + "  "
        );
        System.out.println(afficherDijkstraTxt(dist,pred));*/


        // affichage des Noeud destination et de leur Noeud predecesseur
        /*
        for (int i = 0; i < dist.length; i++) {
            if (pred[i] != null) {
                System.out.print(g.getNodes().get(i).printConfig() +" "+
                    pred[i].printConfig() + " " + dist[i] + "\n");
            }
        }*/

        //exploitation de la table
        chemin = exploiterTableRoutage(dist,pred,source,dest);

        //affichage du chemin
        System.out.println(afficherChemin (chemin));
        return chemin;
    }



    /**
     * procedure bourée de System.out.print qui permet d'avoir la table de routage de Djikstra
     * @param dist Table des distance de la source vers les noeuds [idNoeud1,IdNoeud2,...]
     * @param pred Table des  pour arriver a la distance optimale
     */
    public String afficherDijkstraTxt(double [] dist , Configuration [] pred) {
        String retour = "";

        retour += "Destination : ";
        for (Configuration n : g.getNodes()) {
            retour +=  n.printConfig() + "\t | ";
        }
        retour += "\n";

        retour += "Distance : ";
        for (double d : dist) {
            retour += d + "\t | ";
        }
        retour += "\n";

        retour += "Pred : \t";
        for (Configuration n : pred) {
            if (n != null) {
                retour += n.printConfig() + "\t | ";
            } else {
                retour +="NULL"+"\t | ";
            }
        }

        return retour;
    }


    /**
     * Parcours la table des plus court chemin et determine le plus court chemin pour le noeud source -> noeud dest
     * @param dist Liste des distance calculé par Dijkstra
     * @param pred Liste des Noeud precedents calculé par Dijkstra
     * @param source Noeud source
     * @param dest Noeud destination
     * @return Liste de noeud (representant le chemin a parcourir)
     */
    public List<Configuration> exploiterTableRoutage(double [] dist,Configuration [] pred,Configuration source,Configuration dest) {
        List<Configuration> chemin = new ArrayList<Configuration>();
        List<Configuration> cheminInv  =  new ArrayList<Configuration>();
        PriorityQueue<Configuration> queue = new PriorityQueue<Configuration>();
        queue.add(dest);
        Configuration currentDest;
        Configuration currentPred;
        while (!queue.isEmpty()) {
            currentDest = queue.poll();
            if (dist[currentDest.getiD()] < Double.MAX_VALUE
                && pred[currentDest.getiD()] != null) {
                // la distance n'est pas maximale et le noeud a bien un predecesseur

                currentPred = pred[currentDest.getiD()];
                //System.out.println(currentDest.printConfig());
                cheminInv.add(currentDest);
                queue.add(currentPred);

            } else if (dist[currentDest.getiD()] == 0) {
                // cas du Noeud de depart (on ajoute le noeud au chemin et on sors)
                cheminInv.add(currentDest);
            }

        }

        // on réordonne le chemin pour le retour
        for (int i = cheminInv.size() - 1; i >=  0; i--) {
            chemin.add(cheminInv.get(i));
        }
        cheminInv.clear();

        /*
        for(int i = 0 ; i < chemin.size(); i++) {
            int j = i+1;
            if (j < chemin.size() ) {
                System.out.println(chemin.get(i).printConfig() +" -> " + chemin.get(j).printConfig());
                System.out.println(chemin.get(i).estVoisin(chemin.get(j)));
            }
        }*/
        return chemin;
    }


    /**
     * affiche le chemin en txt
     * @param chemin liste de noeuds
     */
    public String afficherChemin (List<Configuration> chemin) {
        String str = "";
        for (int i = 0 ;i < chemin.size() ; i++) {
            str +=  chemin.get(i).printConfig();
            if (i < chemin.size() - 1) {
                str += " -> ";
            }
        }
        str += " \n";
        return str;
    }




    /**
     * egalité entre deux liste de noeuds
     * @param l1 Liste de Noeud
     * @param l2 Liste de Noeud
     * @return Boolean
     */
    public boolean isEqualsList(List<Configuration> l1 ,List<Configuration> l2 ) {
        for (int i = 0 ; i < l1.size() ; i++) {
            if(!l1.get(i).eq(l2.get(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * verrifie si il existe au moins un chemin entre n1 et n2
     * @param n1 noeud
     * @param n2 noeud
     * @return boolean
     */
    public boolean existeChemin (Configuration n1 ,Configuration n2) {
        List<Configuration> visite = new ArrayList<Configuration>();
        List<Configuration> aTraiter = new ArrayList<Configuration>();
        Configuration current;
        aTraiter.add(n1);
        while (!aTraiter.isEmpty()) {
            current = aTraiter.get(0);
            aTraiter.remove(0);
            for (ArcConfig a : current.getVoisin()) {
                if (a.getFin() == n2) {
                    return true;
                } else if (!visite.contains(a.getFin())) {
                    aTraiter.add(a.getFin());
                    visite.add(a.getFin());
                }
            }

        }
        return false;
    }

    /**
     * appel a djikstra apres avoir vérifier l'existance d'un chemin dans le graphe
     * @param source noeud source
     * @param dest noeud destination
     * @return liste de noeuds representant le chemin si il existe (vide sinon)
     */
    public List<Configuration> plusCoutChemin(Configuration source,Configuration dest) {
        if (existeChemin (source,dest)) {
            return djikstraRoutage(source,dest);
        } else {
            return new ArrayList<Configuration>();
        }
    }











}
