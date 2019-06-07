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
    private Dijkstra dikstra;




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
        Noeud n3 = g.getNodes().get(3);
        Noeud n4 = g.getNodes().get(4);
        Noeud n5 = g.getNodes().get(5);


        Configuration init = new Configuration(n0,n1,n2);
        graph = new GraphConfig(init);


        Configuration c1 = new Configuration(n0,n1,n3);
        Configuration c2 = new Configuration(n0,n1,n4);

        Configuration c3 = new Configuration(n4,n1,n3);
        Configuration c4 = new Configuration(n0,n2,n3);

        Configuration c5 = new Configuration(n4,n1,n2);
        Configuration c6 = new Configuration(n4,n1,n0);



        graph.addConfig(c1);
        graph.addConfig(c2);
        graph.addConfig(c3);
        graph.addConfig(c4);
        graph.addConfig(c5);
        graph.addConfig(c6);




        init.addDualLink(c2,2);
        init.addDualLink(c3,3);

        c1.addDualLink(c3,2);
        c1.addDualLink(c4,3);

        c3.addDualLink(c5,2);
        c3.addDualLink(c6,3);





        dikstra  = new Dijkstra(graph);

       System.out.println(graph.afficher());

        dikstra.djikstraRoutage(init,c6);

    }



    @Test
    public void tesvoisin() {

        Noeud n0 = g.getNodes().get(0);
        Noeud n1 = g.getNodes().get(1);
        Noeud n2 = g.getNodes().get(2);

        Configuration init = new Configuration(n0,n1,n2);
        graph = new GraphConfig(init);
    }


    @Test
    public void contrainteRobot () {
        Noeud n0 = new Noeud(0,"",0,0);
        Noeud n1 = new Noeud(0,"",0,1);
        Noeud n2 = new Noeud(0,"",0,2);
        Noeud n3 = new Noeud(0,"",1,2);
        Noeud n4 = new Noeud(0,"",6,2);


        assert(g.contrainteAllignee(n0,n1,n2));
        assert(g.contrainteAllignee(n2,n1,n0));
        assert(!g.contrainteAllignee(n0,n2,n2));
        assert(!g.contrainteAllignee(n0,n3,n2));
        assert(g.contrainteAllignee(n2,n3,n4));
    }
}
