package config;
import graph.Noeud;
import java.util.ArrayList;
import java.util.List;

public class GraphConfig {
    private List<Configuration> nodes;


    public GraphConfig(Configuration initiale ) {
        nodes = new ArrayList<Configuration>();
        nodes.add(initiale);
    }

    public GraphConfig() {
            nodes = new ArrayList<Configuration>();
    }


    public List<Configuration> getNodes() {
        return nodes;
    }


    public void setNodes(List<Configuration> nodes) {
        this.nodes = nodes;
    }


    /**
     * Verifie si la nouvelle configuration est valide , si oui l'ajoute au graphe
     * @param c position des robots
     */
    public void addConfig(Configuration c) {
        c.setiD(nodes.size());
        Noeud n1 = c.getV1();
        Noeud n2 = c.getV2();
        Noeud n3 = c.getV3();
        int id = c.getiD();
        Configuration mirror = new Configuration(id,n1,n3,n2);

       for (Configuration n : nodes) {
           if (n.eq(c) || n.eq(mirror)) {
               return;
           }
        }
        nodes.add(c);

    }



    public String afficher() {
        String s ="";

        for (Configuration p : nodes) {
            s += " [ "
                + p.getV1().getNom() + " ; "
                + p.getV2().getNom() + " ; "
                + p.getV3().getNom()  + " ]";
        }

        return s;
    }


    /**
     * generation des configuration voisines en backtrack qui respectent la regle du jeu
     * @return Configuration voisines
     */
    public List<Configuration> generationVoisin(Configuration c) {
        Noeud n1 = c.getV1();
        Noeud n2 = c.getV2();
        Noeud n3 = c.getV3();

        //generate
       return new ArrayList<Configuration>();
    }
}
