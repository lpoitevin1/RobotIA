package config;


import Plateau.Plateau;
import graph.Graphe;
import graph.Noeud;
import org.junit.Before;
import org.junit.Test;


public class configTest {

    private Graphe g;
    private Plateau p;
    private GraphConfig graph;




    @Before
    public void setup(){
        p = new Plateau ("nodeGrille","linkGrille");
        g = p.getG();

        System.out.println(g.getNodes().size());
        p.getRobots()[0] = g.getNodes().get(0);
        p.getRobots()[1] = g.getNodes().get(1);
        p.getRobots()[2] = g.getNodes().get(2);
        p.setObjectif(g.getNodes().get(3));

    }



    @Test
    public void graphConfig(){
        Noeud n0 = g.getNodes().get(0);
        Noeud n1 = g.getNodes().get(1);
        Noeud n2 = g.getNodes().get(2);


        Configuration init = new Configuration(p.getRobots());
        graph = new GraphConfig(init);

        Configuration c1 = new Configuration(n0,n1,n2);
        Configuration c2 = new Configuration(n0,n1,n2);
        Configuration c3 = new Configuration(n0,n2,n1);
        Configuration cr = new Configuration(n1,n2,n0);

        graph.addConfig(c1);
        graph.addConfig(c2);
        graph.addConfig(c3);
        graph.addConfig(cr);


        init.addVoisins(c1);
        init.addVoisins(cr);
        c1.addVoisins(c2);
        c1.addVoisins(c3);

        for(Configuration c : graph.getNodes()) {
            System.out.println(c.afficher());
        }

    }
}
