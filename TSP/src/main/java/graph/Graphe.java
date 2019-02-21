package graph;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class Graphe {
    private List<Noeud> nodes;
    private List<Arc> arcs;

    Graphe() {
        nodes = new ArrayList<Noeud>();
        arcs = new ArrayList<Arc>();
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
     * ajoute l'arete n1 -> n2 , n2 -> n1
     * @param n1 Noeud
     * @param n2 Noeud
     */
    public void addLink(Noeud n1, Noeud n2 , double cost) {



        Arc a = new Arc(n1, n2, cost);
        if (!arcs.contains(a)) {
            arcs.add(a);
        }



    }


    public List<Arc> getArcs() {
        return arcs;
    }

    /**
     * accesseur nodes
     * @return List<Noeud>
     */
    public List<Noeud> getNodes() {
        return nodes;
    }




    /**
     * calcule la distance pour parcourir l'arete (n1,n2) si elle existe sinon retourne Double.MaxValue
     * @param n1 Noeud depart
     * @param n2 Noeud arrivée
     * @return distance de l'arete
     */
    public double calculerDistance (Noeud n1, Noeud n2) {
        for(Arc a : arcs) {
            if (a.getDebut().equals(n1) && a.getFin().equals(n2)) {
                return a.getCout();
            }
        }
        return Double.MAX_VALUE;
    }


    /**
     * affiche les arcs
     */
    public void printArcs() {
        for(Arc a : arcs) {
            System.out.println(a.getDebut().getNom()
                                + " -> " + a.getFin().getNom()
                                +" cout : "+a.getCout());
        }
    }


    /**
     * retourne la liste des arcs a partir du noeud source
     * @param n Noeud
     * @return liste d'arcs
     */
    public List<Arc> getVoisins (Noeud n) {
        List<Arc> voisins = new ArrayList<Arc>();
        for(Arc a : arcs) {
           if (a.getDebut().equals(n)) {
               voisins.add(a);
           }
        }
        return voisins;
    }

    /**
     * Retourne le meilleur arc
     * @param voisins Liste de voisin
     * @return Arc
     */
    public Arc meilleurVoisin (List<Arc> voisins) {

        int indice = 0;
        double curentMin = Double.MAX_VALUE;
        Arc a;

        for (int i = 0; i < voisins.size() ; i++) {
            a = voisins.get(i);
            if(a.getCout() < curentMin) {
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
    public List<Arc> Dijkstra (Noeud source , Noeud dest) {
        resetVisited();
        PriorityQueue<Noeud> aTraiter = new PriorityQueue<Noeud>();
        List<Arc> voisins;
        Arc next;
        aTraiter.add(source);
        Noeud current;
        List<Arc> chemin = new ArrayList<Arc>();

        while (!aTraiter.isEmpty()) {
            current = aTraiter.poll();
            current.setVisite(true);


            //calcul des voisins du noeud actuel
            voisins = getVoisins (current);

            //choix du meilleur voisin
            if (!voisins.isEmpty()) {
                next = meilleurVoisin(voisins);
            } else break;



            if (!next.getFin().isVisite() && next.getFin() != dest) {
                aTraiter.add(next.getFin());
            }
            chemin.add(next);
            System.out.println(next.getDebut().getNom() + " -> " + next.getFin().getNom());

        }
        return chemin;
    }







    public void resetVisited(){
        for(Noeud n :nodes) {
            n.setVisite(false);
        }
    }






















    }
