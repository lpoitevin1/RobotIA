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
    public void addNode(Noeud n){
        if(!nodes.contains(n)){
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
     * Fournis une table des plus court chemins vers tous les noeuds
     * @param source noeud source
     */
    public void djikstrav2(Noeud source) {
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
            } else {
                index = aTraiter.get(0);
                aTraiter.remove(0);
            }


            for(int i = 0;i < aTraiter.size(); i++ ) {
                if (aTraiter.get(i).equals(index)){
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

                    System.out.println(voisin.getDebut().getNom() + " -> " + voisin.getFin().getNom());
                }
            }
        }

        afficherDijkstraTxt(dist,pred);

    }



    /**
     * procedure bourée de System.out.print qui permet d'avoir la table de routage de Djikstra
     * @param dist Table des distance de la source vers les noeuds [idNoeud1,IdNoeud2,...]
     * @param pred Table des Next Hop pour arriver a la distance optimale
     */
    public void afficherDijkstraTxt(double [] dist , Noeud [] pred){

        System.out.println("");
        System.out.print("Destination : ");
        for(Noeud n : getNodes()) {
            System.out.print( n.getNom() + "\t | ");
        }
        System.out.println("");

        System.out.print("Distance : ");
        for (double d : dist) {
            System.out.print(d + "\t | ");
        }
        System.out.println("");

        System.out.print("Next Hop : ");
        for (Noeud n : pred) {
            if (n != null) {
                System.out.print(n.getNom() + "\t  | ");
            } else {
                System.out.print("NULL"+"\t | ");
            }
        }
        System.out.println("");

    }

    /**
     * remise a zero des boolean visite pour tous les noeuds
     */
    public void resetVisited(){
        for(Noeud n :nodes) {
            n.setVisite(false);
        }
    }


}
