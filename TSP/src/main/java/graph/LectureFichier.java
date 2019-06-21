package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LectureFichier {
    public static String REPERORY_PATH = System.getProperty("user.dir") + "/src/main/ressources/";
    private String  nodesPath;
    private String linksPath;


    public LectureFichier(String nodes, String links) {
        nodesPath = REPERORY_PATH + nodes;
        linksPath = REPERORY_PATH + links;
    }

    public static void setReperoryPath(String reperoryPath) {
        REPERORY_PATH = reperoryPath;
    }

    public static String getReperoryPath() {
        return REPERORY_PATH;
    }

    public String getLinksPath() {
        return linksPath;
    }

    public String getNodesPath() {
        return nodesPath;
    }

    public void setLinksPath(String linksPath) {
        this.linksPath = linksPath;
    }

    public void setNodesPath(String nodesPath) {
        this.nodesPath = nodesPath;
    }

    /**
     * Lis un fichier et genere les noeuds d'un graphe
     * @return Liste de noeuds
     */
    public ArrayList<Noeud> lectureNoeuds () {
        ArrayList<Noeud> nodes = new ArrayList<Noeud>();
        try {
            String ligne;
            int id;
            String name;
            double x;
            double y;
            BufferedReader fichier = new BufferedReader(new FileReader(nodesPath));
            while ((ligne = fichier.readLine()) != null) {
                String[] out = ligne.split(" ");


                id = Integer.parseInt(out[0]);
                name = out [1];
                x = Double.parseDouble(out[2]);
                y = Double.parseDouble(out[3]);


                nodes.add(new Noeud(id,name,x,y));
            }
            fichier.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nodes;
    }

    /**
     * lis un fichier et édite les arcs d'un graphe
     * @param nodes Liste de Noeuds du graphe
     */
    public void lectureArcs (List<Noeud> nodes) {
        try {
            String ligne;
            BufferedReader fichier = new BufferedReader(new FileReader(linksPath));
            String n1;
            String n2;
            double dist;
            char dir;
            Noeud node1 = new Noeud();
            Noeud node2 = new Noeud();
            while ((ligne = fichier.readLine()) != null) {
                String[] out = ligne.split(" ");
                if (out.length < 3) {
                    break;
                } else {
                    n1 = out[0];
                    n2 = out[1];

                    dist = Double.parseDouble(out[2]);
                    //dir = out[3].charAt(0);
                }

                for(Noeud n : nodes) {
                    if (n.getNom().equals(n1)) {
                        node1 = n;
                        break;
                    }
                }

                for(Noeud n : nodes) {
                    if (n.getNom().equals(n2)) {
                        node2 = n;
                        break;
                    }
                }

                if (!node1.getNom().equals("") && !node2.getNom().equals("")) {
                    node1.addDualLink(node2,dist);
                }

            }
            fichier.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}



