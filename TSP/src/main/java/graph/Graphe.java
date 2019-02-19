package graph;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Graphe {
    private int tailleG;
    private LinkedList<Noeud> voisins[];

    Graphe(int taille) {
        tailleG = taille;
        voisins = new LinkedList[taille];
        for (int i = 0; i < taille; ++i)
            voisins[i] = new LinkedList();
    }

    public void addNode(Noeud n1, Noeud n2) {

        voisins[n1.getIdNoeud()].add(n2);
        voisins[n2.getIdNoeud()].add(n1);

    }


    /**
     * premier parcours de graphe
     * @param source Noeud de départ du parcours
     */
    public void parcours (Noeud source) {

        // tableau pour vérifier quel noeud a deja été parcouru

        boolean dejaVisite[] = new boolean[tailleG];
        List<Noeud> queue = new ArrayList<Noeud>();
        //on marque la source comme deja parcourue et on l'affiche (sa lettre)
        dejaVisite[source.getIdNoeud()] = true;
        queue.add(source);

        while (queue.size() != 0) {

            //on prend comme element courant la tete de liste (tant qu'elle n'est pas vide
            source = queue.get(0);
            queue.remove(0);

            //on affiche ses voisins
            Iterator<Noeud> i = voisins[source.getIdNoeud()].listIterator();
            while (i.hasNext()) {
                Noeud voisin = i.next();
                if (!dejaVisite[voisin.getIdNoeud()]) {
                    System.out.println(source.getNom() + " -> " + voisin.getNom()
                        + " distance : " + calculerDistance(source, voisin));
                    dejaVisite[voisin.getIdNoeud()] = true;
                    queue.add(voisin);

                }

            }
        }

    }


    /**
     * affiche les voinsins d'un noeud
     */
    public void afficher (Noeud source) {

            Iterator<Noeud> i = voisins[source.getIdNoeud()].listIterator();
            while (i.hasNext()) {
                Noeud voisin = i.next();

                System.out.println(source.getNom() + " -> " + voisin.getNom()
                    + " distance : " + calculerDistance(source, voisin));

            }


    }


    /**
     * Retourne la distance entre le Noeud N1 et N2
     * @param n1 Noeud
     * @param n2 Noeud
     * @return double
     */
    public double calculerDistance (Noeud n1, Noeud n2){
        return Math.abs(n1.gePosX() - n2.gePosX()) + Math.abs(n1.gePosY() - n2.gePosY());
    }



}
