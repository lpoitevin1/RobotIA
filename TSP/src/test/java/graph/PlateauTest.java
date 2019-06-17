package graph;

import Plateau.Plateau;
import config.Configuration;
import config.Dijkstra;
import org.junit.Before;
import org.junit.Test;


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
        Noeud obj = g.getNodes().get(15);

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
        System.out.println(g.printFormeGrille());
        System.out.println(p.print());

        Configuration source = new Configuration(p.getRobots());
        Configuration result = p.bruteForce(source,p.getObjectif());

        dik = new Dijkstra(p.getCoups());
        //System.out.println(dik.existeChemin(source,result));

        //System.out.println(source.printConfig());
        //System.out.println(result.printConfig());

        dik.djikstraRoutage(source,result);

        //System.out.println(dik.getG().afficher());

    }




    @Test
    public void grilleAvecPassage() {
        p = new Plateau ("nodeGrille_3","linkGrille_3");
        g = p.getG();
        System.out.println(g.printFormeGrille());

        Noeud r2 = g.getNodes().get(1);
        Noeud r3 = g.getNodes().get(13);
        Noeud obj = g.getNodes().get(11);

        p.setRobots(r1,r2,r3);
        p.setObjectif(obj);

        System.out.println(p.print());

        Configuration source = new Configuration(p.getRobots());
        Configuration result = p.bruteForce(source,p.getObjectif());

        System.out.println(dik.existeChemin(source,result));
        System.out.println(source.printConfig());
        System.out.println(result.printConfig());

        dik = new Dijkstra(p.getCoups());
        dik.djikstraRoutage(source,result);


        //boucle infinie
    }




    @Test
    public void TestChemin(){
        p = new Plateau ("nodeGrille_3","linkGrille_3");
        g = p.getG();

        System.out.println(g.printFormeGrille());

        assert(g.existeChemin(g.getNodes().get(0), g.getNodes().get(1)));
        assert(g.existeChemin(g.getNodes().get(0), g.getNodes().get(3)));
        assert(g.existeChemin(g.getNodes().get(0), g.getNodes().get(5)));

        assert(g.existeChemin_X(g.getNodes().get(0), g.getNodes().get(1)));
        assert(!g.existeChemin_X(g.getNodes().get(0), g.getNodes().get(5)));
        assert(!g.existeChemin_X(g.getNodes().get(0), g.getNodes().get(3)));

        assert(g.existeChemin_Y(g.getNodes().get(0), g.getNodes().get(12)));
        assert(!g.existeChemin_Y(g.getNodes().get(0), g.getNodes().get(1)));
        assert(!g.existeChemin_Y(g.getNodes().get(0), g.getNodes().get(5)));

    }
}
