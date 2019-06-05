package graph;

import Plateau.Plateau;
import config.Configuration;
import config.Dijkstra;
import org.junit.Before;
import org.junit.Test;
import sun.security.krb5.Config;

import java.util.List;

public class PlateauTest {

    private Dijkstra dik;
    private Plateau p;
    private Graphe g;
    private Noeud r1;


    @Before
    public void setup(){
        p = new Plateau ("nodeGrille","linkGrille");
        g = p.getG();

        r1 = g.getNodes().get(0);
        Noeud r2 = g.getNodes().get(1);
        Noeud r3 = g.getNodes().get(2);
        Noeud obj = g.getNodes().get(3);

        p.setRobots(r1,r2,r3);
        p.setObjectif(obj);

    }


    @Test
    public void TSP() {

        Noeud r1 = p.getRobots()[0];
        Noeud obj = p.getObjectif();
        g.djikstraRoutage(r1,obj);
    }



    @Test
    public void access(){
        Noeud r1 = p.getRobots()[0];
        Noeud r2 = p.getRobots()[1];
        Noeud r3 = p.getRobots()[2];


        List<Configuration> l = p.access (0);

    }


    @Test
    public void bruteForce() {
        Noeud n15 = g.getNodes().get(15);

        Configuration source = new Configuration(p.getRobots());
        Configuration result = p.bruteForce(source,n15);

        dik = new Dijkstra(p.getCoups());
        System.out.println(dik.existeChemin(source,result));

        System.out.println(source.printConfig());
        System.out.println(result.printConfig());

        dik.djikstraRoutage(source,result);

        //System.out.println(dik.getG().afficher());


    }
}
