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



    public void addConfig(Configuration c) {
        Noeud n1 = c.getPositions()[0];
        Noeud n2 = c.getPositions()[1];
        Noeud n3 = c.getPositions()[2];
        Configuration mirror = new Configuration(n1,n3,n2);



       for (Configuration n : nodes) {
           if (n.eq(c) || n.eq(mirror)) {
               return;
           }
        }
        nodes.add(c);

    }



}
