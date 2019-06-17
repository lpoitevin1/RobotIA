package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class Graphe {
    private List<Noeud> nodes;

    public Graphe() {
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
     * 1 . Calcule la table des plus court chemins et des precedence (a partir du noeud source)
     * 2 . Exploite la table pour construire le plus court chemin vers la destination
     * @param source noeud source
     * @param dest noeud destination
     * @return Liste de noeud contenant le plus court chemin source -> destination
     */
    public List<Noeud> djikstraRoutage(Noeud source, Noeud dest) {
        List<Noeud> chemin = new ArrayList<Noeud>();
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

    /**
     * Affiche les commandes a effectuer pour parcourir le plus court chemin (angles et noeuds)
     * détail du trajet apres Dijkstra
     * @param chemin liste de Noeuds
     * @return String
     */
    public String detailTrajet(List<Noeud> chemin) {
        String retour = "";
        Noeud n1 = new Noeud();
        Noeud n2 = new Noeud();
        Noeud n3 = new Noeud();
        if (chemin.size() > 2) {
            while (chemin.size() >= 3) {
                n1 = chemin.get(0);
                n2 = chemin.get(1);
                n3 = chemin.get(2);

                chemin.remove(2);
                chemin.remove(1);
                chemin.remove(0);

                retour += "\n";
                retour += n1.getNom() + "->" + n2.getNom() + "\n";
                retour += angle(n1,n2,n3) + "\n";
                retour += n2.getNom() + "->" + n3.getNom() + "\n";
            }

            while (!chemin.isEmpty()) {
                if (chemin.size() > 1) {
                    n1 = n3;
                    n2 = chemin.get(0);
                    n3 = chemin.get(1);
                    chemin.remove(1);
                    chemin.remove(0);
                    System.out.println(n1.getNom());
                } else {
                    n1 = n2;
                    n2 = n3;
                    n3 = chemin.get(0);
                    chemin.remove(0);
                }

                retour += angle(n1,n2,n3) + "\n";
                retour += n2.getNom() + "->" + n3.getNom() + "\n";
            }
            return retour;
        } else {
            if (chemin.size() == 2) {
                return chemin.get(0).getNom() +" -> " + chemin.get(1).getNom();
            } else {
                return "";
            }
        }
    }


    /**
     * Edite les noeuds du grahe en fonction de fichier de noeuds arc/noeuds fichier
     * @param fichierNode noeuds du graphe
     * @param fichierLinks aretes du graphe
     */
    public void construirereGrapheLecture(String fichierNode, String fichierLinks) {

        LectureFichier lect = new LectureFichier(fichierNode,fichierLinks);
        nodes = lect.lectureNoeuds();
        lect.lectureArcs(nodes);
    }


    /**
     * Pour 3 noeuds n1,n2,n3 donne l'angle correspondant
     * @param n1 Noeud
     * @param n2 Noeud
     * @param n3 Noeud
     * @return angle
     */
    public int angle (Noeud n1 , Noeud n2 , Noeud n3) {
        if(n1.getX() == n2.getX() && n2.getX() == n3.getX()
            || n1.getY() == n2.getY() && n2.getY() == n3.getY()) {
            return 0;
        } else if( n1.getY() < n2.getY() && n1.getX() > n3.getX()
            ||n1.getY() > n2.getY() && n1.getX() < n3.getX()) {
            return -90;
        } else return 90;
    }

    /**
     * retourne le noeud en fin de chaine
     * @param n
     * @return
     */
    public List<Noeud> parcoursX( Noeud n) {
        List<Noeud> parcours = new ArrayList<Noeud>();
        List<Noeud> visited  =  new ArrayList<Noeud>();
        List<Noeud> aTraiter = new ArrayList<Noeud>();
        aTraiter.add(n);
        Noeud currentDest;
        while(!aTraiter.isEmpty()) {
            currentDest = aTraiter.get(0);
            aTraiter.remove(0);
            for(Arc a : currentDest.getArcs()) {
                if (a.getFin().getX() == n.getX() && !visited.contains(a.getFin())) {
                    parcours.add(a.getFin());
                    visited.add(a.getFin());
                    aTraiter.add(a.getFin());
                }
            }
        }

        return parcours;
    }



    /**
     * retourne le noeud en fin de chaine
     * @param n
     * @return
     */
    public List<Noeud> parcoursY( Noeud n) {
        List<Noeud> parcours = new ArrayList<Noeud>();
        List<Noeud> visited  =  new ArrayList<Noeud>();
        List<Noeud> aTraiter = new ArrayList<Noeud>();
        aTraiter.add(n);
        Noeud currentDest;
        while(!aTraiter.isEmpty()) {
            currentDest = aTraiter.get(0);
            aTraiter.remove(0);
            for(Arc a : currentDest.getArcs()) {
                if (a.getFin().getY() == n.getY() && !visited.contains(a.getFin())) {
                    parcours.add(a.getFin());
                    visited.add(a.getFin());
                    aTraiter.add(a.getFin());
                }
            }
        }

        return parcours;
    }

    /**
     * vrai si le Noeud 1 -> Noeud 2 -> Noeud 3 sur la meme ligne/ colonne
     *            Noeud 3 <- Noeud 2 <- Noeud 1
     * @param n1 Depart
     * @param n2 Noeud a tester
     * @param n3 Objectif
     * @return Boolean
     */
    public boolean contrainteAllignee(Noeud n1 , Noeud n2 , Noeud n3) {
        if(n1.getX() == n2.getX() && n2.getX() == n3.getX()) {
            if( (n1.getY() < n2.getY() && n2.getY() < n3.getY()) ||
                (n1.getY() > n2.getY() && n2.getY() > n3.getY())) {
                return true;
            } else {
                return false;
            }
        }else if (n1.getY() == n2.getY() && n2.getY() == n3.getY()) {
            if( (n1.getX() < n2.getX() && n2.getX() < n3.getX()) ||
                (n1.getX() > n2.getX() && n2.getX() > n3.getX())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    public String printFormeGrille() {
            String s ="";
            int dim_y = 0;
            int dim_x = 0;
            for (Noeud n : nodes) {
                if ((int)n.getX() > dim_x) {
                    dim_x = (int)n.getX();
                }
                if ((int)n.getY() > dim_y) {
                    dim_y = (int)n.getY();
                }
            }
            //System.out.println(dim_x + " " + dim_y);
            Noeud graphe [][] = new Noeud[dim_x + 1][dim_y + 1];
            for (int i = 0; i < graphe.length; i++) {
                for (int j = 0; j < graphe[i].length; j++) {
                    graphe[i][j] = new Noeud();
                }
            }
            for (Noeud n : nodes) {
                graphe[(int)n.getX()][(int)n.getY()] = n;
            }


            for (int i = 0; i < graphe.length; i++) {
                for (int j = 0 ; j < graphe[i].length ; j++) {
                    if(graphe[i][j].getArcs().size() > 0) {
                        s += graphe[i][j].getNom() + "\t";
                    } else {
                        s += " X ";
                    }
                }
                s += "\n";
            }
            return s;
    }


    /**
     * Existe un chemin sur la meme ligne
     * @param n1
     * @param n2
     * @return
     */
    public boolean existeChemin_X (Noeud n1 ,Noeud n2) {
        List<Noeud> visite = new ArrayList<Noeud>();
        List<Noeud> aTraiter = new ArrayList<Noeud>();
        Noeud current;
        aTraiter.add(n1);
        while (!aTraiter.isEmpty()) {
            current = aTraiter.get(0);
            aTraiter.remove(0);
            for (Arc a : current.getArcs()) {
                if (a.getFin() == n2 && a.getFin().getX() == n1.getX()) {
                    return true;
                } else if (!visite.contains(a.getFin()) && a.getFin().getX() == n1.getX()) {
                    aTraiter.add(a.getFin());
                    visite.add(a.getFin());
                }
            }

        }
        return false;
    }


    /**
     * Existe un chemin sur la meme colonne
     * @param n1
     * @param n2
     * @return
     */
    public boolean existeChemin_Y (Noeud n1 ,Noeud n2) {
        List<Noeud> visite = new ArrayList<Noeud>();
        List<Noeud> aTraiter = new ArrayList<Noeud>();
        Noeud current;
        aTraiter.add(n1);
        while (!aTraiter.isEmpty()) {
            current = aTraiter.get(0);
            aTraiter.remove(0);
            for (Arc a : current.getArcs()) {
                if (a.getFin() == n2 && a.getFin().getY() == n1.getY()) {
                    return true;
                } else if (!visite.contains(a.getFin()) && a.getFin().getY() == n1.getY()) {
                    aTraiter.add(a.getFin());
                    visite.add(a.getFin());
                }
            }

        }
        return false;
    }



}
