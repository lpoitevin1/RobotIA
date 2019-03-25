package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


public class Graphe {
    private List<Noeud> nodes;

    Graphe() {
        nodes = new ArrayList<Noeud>();
    }

    public void setNodes(List<Noeud> nodes) {
        this.nodes = nodes;
    }



    /**
     * Ajoute le Noeud au graphe
     * @param n Noeud
     */
    public void addNode(Noeud n) {
        if (!nodes.contains(n)) {
            nodes.add(n);
         }

    }

    /**
     * accesseur nodes
     * @return List<Noeud>
     */
    public List<Noeud> getNodes() {
        return nodes;
    }




    /**
     * Retourne le meilleur arc
     * @param voisins Liste de voisin
     * @return Arc
     */
    public Arc meilleurVoisin(List<Arc> voisins) {

        int indice = 0;
        double curentMin = Double.MAX_VALUE;
        Arc a;

        for (int i = 0; i < voisins.size(); i++) {
            a = voisins.get(i);
            if (a.getCout() < curentMin) {
                curentMin = a.getCout();
                indice = i;

            }
        }

        return voisins.get(indice);
    }


    /**
     * Calcule le plus court chemin de proche en proche et choisis le meilleur voisin
     * @param source Noeud depart
     * @param dest Noeud arrive
     * @return Liste Arc
     */
    public List<Arc> dijkstra(Noeud source, Noeud dest) {

        resetVisited();
        PriorityQueue<Noeud> aTraiter = new PriorityQueue<Noeud>();
        List<Arc> voisins;
        Arc next;
        Noeud current;
        List<Arc> chemin = new ArrayList<Arc>();

        aTraiter.add(source);

        while (!aTraiter.isEmpty()) {

            current = aTraiter.poll();
            current.setVisite(true);

            //calcul des voisins du noeud actuel
            voisins = current.getArcs();

            //choix du meilleur voisin
            if (!voisins.isEmpty()) {
                next = meilleurVoisin(voisins);
            } else break;


            if (!next.getFin().isVisite() && next.getFin() != dest) {
                aTraiter.add(next.getFin());
            }
            chemin.add(next);
            System.out.println(next.getDebut().getNom() + " -> "
                            + next.getFin().getNom());
        }
        return chemin;
    }

    /**
     * 1 . Calcule la table des plus court chemins et des precedence (a partir du noeud source)
     * 2 . Exploite la table pour construire le plus court chemin vers la destination
     * @param source noeud source
     * @param dest noeud destination
     * @return Liste de noeud contenant le plus court chemin source -> destination
     */
    public List<Noeud> djikstraRoutage(Noeud source, Noeud dest) {
        List<Noeud> chemin = new ArrayList<Noeud>();
        resetVisited();
        List<Integer> aTraiter = new ArrayList<Integer>();
        Noeud curendNode;
        int index;

        double [] dist = new double [getNodes().size()];
        Noeud [] pred = new Noeud [getNodes().size()];
        for (Noeud n : getNodes()) {
            dist[n.getIdNoeud()] = Double.MAX_VALUE;
            aTraiter.add(n.getIdNoeud());

        }
        dist[source.getIdNoeud()] = 0;

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


            for(int i = 0;i < aTraiter.size(); i++ ) {
                if (aTraiter.get(i).equals(index)) {
                    aTraiter.remove(i);
                    break;
                }
            }

            curendNode = getNodes().get(index);


            for(Arc voisin : curendNode.getArcs()) {
                //on calcule la somme la distance pour acceder au noeud actuel + le cout de l'arc
                int idNext = voisin.getFin().getIdNoeud();
                double cost = dist[index] + voisin.getCout();
                if (cost < dist[idNext]) {
                    dist[idNext] = cost;
                    pred[idNext] = curendNode;

                    //System.out.println(voisin.getDebut().getNom() + " -> " + voisin.getFin().getNom());
                }
            }
        }

        //affichage txt de la table de routage
        System.out.println("Source : " + source.getNom() + " Destination : " + dest.getNom() );
        System.out.println(afficherDijkstraTxt(dist,pred));

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
    public String afficherDijkstraTxt(double [] dist , Noeud [] pred) {
        String retour = "";

        retour += "Destination : ";
        for (Noeud n : getNodes()) {
            retour +=  n.getNom() + "\t | ";
        }
        retour += "\n";

        retour += "Distance : ";
        for (double d : dist) {
            retour += d + "\t | ";
        }
        retour += "\n";

        retour += "Pred : \t";
        for (Noeud n : pred) {
            if (n != null) {
                retour += n.getNom() + "\t | ";
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
    public List<Noeud> exploiterTableRoutage(double [] dist,Noeud [] pred,Noeud source,Noeud dest) {
        List<Noeud> chemin = new ArrayList<Noeud>();
        List<Noeud> cheminInv  =  new ArrayList<Noeud>();
        PriorityQueue<Noeud> queue = new PriorityQueue<Noeud>();
        queue.add(dest);
        Noeud currentDest;
        Noeud currentPred;

        while (!queue.isEmpty()) {
            currentDest = queue.poll();
            if (dist[currentDest.getIdNoeud()] < Double.MAX_VALUE
                && pred[currentDest.getIdNoeud()] != null) {
                // la distance n'est pas maximale et le noeud a bien un predecesseur

                currentPred = pred[currentDest.getIdNoeud()];
                //System.out.println("current Dest " + currentDest.getNom() + " current Pred " + currentPred.getNom());
                cheminInv.add(currentDest);
                queue.add(currentPred);

            } else if (dist[currentDest.getIdNoeud()] == 0) {
                // cas du Noeud de depart (on ajoute le noeud au chemin et on sors)
                cheminInv.add(currentDest);
            }

        }

        // on réordonne le chemin pour le retour
        for (int i = cheminInv.size() - 1; i >=  0; i--) {
            chemin.add(cheminInv.get(i));
        }
        cheminInv.clear();
        return chemin;
    }

    /**
     * affiche le chemin en txt
     * @param chemin liste de noeuds
     */
    public String afficherChemin (List<Noeud> chemin) {
        String str = "";
        for (int i = 0 ;i < chemin.size() ; i++) {
            str += chemin.get(i).getNom();
            if (i < chemin.size() - 1) {
                str += " -> ";
            }
        }
        str += " \n";
        return str;
    }

    /**
     * remise a zero des boolean visite pour tous les noeuds
     */
    public void resetVisited() {
        for (Noeud n :nodes) {
            n.setVisite(false);
        }
    }


    /**
     * egalité entre deux liste de noeuds
     * @param l1 Liste de Noeud
     * @param l2 Liste de Noeud
     * @return Boolean
     */
    public boolean isEqualsList(List<Noeud> l1 ,List<Noeud> l2 ) {
        for (int i = 0 ; i < l1.size() ; i++) {
            if(!l1.get(i).equalsNode(l2.get(i))) {
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
    public boolean existeChemin (Noeud n1 ,Noeud n2) {
        List<Noeud> visite = new ArrayList<Noeud>();
        List<Noeud> aTraiter = new ArrayList<Noeud>();
        Noeud current;
        aTraiter.add(n1);
        while (!aTraiter.isEmpty()) {
            current = aTraiter.get(0);
            aTraiter.remove(0);
            for (Arc a : current.getArcs()) {
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
    public List<Noeud> plusCoutChemin(Noeud source,Noeud dest) {
        if (existeChemin (source,dest)) {
            return djikstraRoutage(source,dest);
        } else {
            return new ArrayList<Noeud>();
        }
    }


}
