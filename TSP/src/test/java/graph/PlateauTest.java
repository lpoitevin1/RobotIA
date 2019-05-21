package graph;

import Plateau.Plateau;
import org.junit.Before;
import org.junit.Test;

public class PlateauTest {


    private Plateau p;
    private Graphe g;


    @Before
    public void setup(){
        p = new Plateau ("nodeGrille","linkGrille");
        g = p.getG();

        System.out.println(g.getNodes().size());
        Noeud r1 = g.getNodes().get(0);
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
}
