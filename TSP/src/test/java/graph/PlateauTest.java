package graph;

import Plateau.Plateau;
import config.Configuration;
import config.Dijkstra;
import config.GraphConfig;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

public class PlateauTest {

    private Dijkstra dik;
    private Plateau p;
    private Graphe g;


    @Before
    public void setup(){
        p = new Plateau ("nodeGrille","linkGrille");
        g = p.getG();

        Noeud r1 = g.getNodes().get(0);
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
    public void grilleCarree() {
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

        Noeud r1 = g.getNodes().get(0);
        Noeud r2 = g.getNodes().get(1);
        Noeud r3 = g.getNodes().get(13);
        Noeud obj = g.getNodes().get(11);

        p.setRobots(r1,r2,r3);
        p.setObjectif(obj);


        Configuration source = new Configuration(p.getRobots());
        Configuration result = p.bruteForce(source,p.getObjectif());
        if(source!=result) {
            dik = new Dijkstra(p.getCoups());
            dik.plusCoutChemin(source,result);
        }

    }

    @Test
    public void TestfinLigne() {
        p = new Plateau ("nodeGrille_3","linkGrille_3");
        g = p.getG();

        Noeud n0 = g.getNodes().get(0);
        Noeud n1 = g.getNodes().get(1);

        Noeud v1 = g.getNodes().get(13);
        Noeud v2 = g.getNodes().get(3);


        assert(n0.finDeLigne(n1,g.getNodes().get(11),g.getNodes().get(15)));
        assert(n0.finDeLigne(g.getNodes().get(8),g.getNodes().get(12),n1));
        assert(!n0.finDeLigne(g.getNodes().get(4),g.getNodes().get(12),n1));
        assert(!n1.finDeLigne(g.getNodes().get(5),g.getNodes().get(13),g.getNodes().get(0)));
        assert(n1.finDeLigne(g.getNodes().get(9),g.getNodes().get(13),g.getNodes().get(0)));
    }




    @Test
    public void TestFichier4x4(){
        /**
         * 12 13 X 15
         * 8  9  10 11
         * 4  5  X  7
         * 0  1  X  3
         */

        p = new Plateau ("nodeGrille_3","linkGrille_3");
        g = p.getG();


        assert(g.existeChemin(g.getNodes().get(0), g.getNodes().get(1)));
        assert(g.existeChemin(g.getNodes().get(0), g.getNodes().get(8)));
        assert(g.existeChemin(g.getNodes().get(8), g.getNodes().get(11)));
        assert(g.existeChemin(g.getNodes().get(13), g.getNodes().get(12)));
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

    @Test
    public void TestFichier5x5(){
        /**
         *
         * 20   21  22  23  24
         * 15   16  17  18  19
         * 10   11  12  13  14
         * 5    6   7   8   9
         * 0    1   2   3   4
         */

        p = new Plateau ("node_grille5x5","link_grille5x5");
        g = p.getG();

        for(int i = 0 ; i < 4 ; i++) {
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+1)));
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+5)));
        }

        for(int i = 5 ; i < 9 ; i++) {
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+1)));
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+5)));
        }

        for(int i = 10 ; i < 14 ; i++) {
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+1)));
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+5)));
        }

        for(int i = 15 ; i < 19 ; i++) {
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+1)));
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+5)));
        }

        for(int i = 20 ; i < 24 ; i++) {
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+1)));
        }
    }

    @Test
    public void TestFichierMur5x5() {
        /**
         *
         * 20   21  22  23  24
         * 15   X  17  X  19
         * 10   X  X   X  14
         * 5    6   7   8   9
         * 0    1   2   3   4
         */

        p = new Plateau ("node_grille_mur5x5","link_grille_mur5x5");
        g = p.getG();

        for(int i = 0 ; i < 4 ; i++) {
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+1)));
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+5)));
        }


        for(int i = 5 ; i < 9 ; i++) {
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+1)));
        }

        for(int i = 0 ; i < 4 ; i++) {
            assert(g.getNodes().get(i*5).estVoisin(g.getNodes().get((i+1)*5)));
            assert(g.getNodes().get( 4 +(i*5) ).estVoisin(g.getNodes().get( 4 + (i+1)*5)));
        }


        for(int i = 20 ; i < 24 ; i++) {
            assert(g.getNodes().get(i).estVoisin(g.getNodes().get(i+1)));
        }

        assert(!g.getNodes().get(6).estVoisin(g.getNodes().get(11)));
        assert(!g.getNodes().get(7).estVoisin(g.getNodes().get(12)));
        assert(!g.getNodes().get(8).estVoisin(g.getNodes().get(13)));
        assert(!g.getNodes().get(10).estVoisin(g.getNodes().get(11)));
        assert(!g.getNodes().get(15).estVoisin(g.getNodes().get(16)));
        assert(!g.getNodes().get(16).estVoisin(g.getNodes().get(17)));
        assert(!g.getNodes().get(17).estVoisin(g.getNodes().get(18)));
        assert(!g.getNodes().get(12).estVoisin(g.getNodes().get(13)));
        assert(!g.getNodes().get(14).estVoisin(g.getNodes().get(13)));
        assert(!g.getNodes().get(19).estVoisin(g.getNodes().get(18)));
        assert(!g.getNodes().get(23).estVoisin(g.getNodes().get(18)));
        assert(!g.getNodes().get(16).estVoisin(g.getNodes().get(21)));
        assert(g.getNodes().get(17).estVoisin(g.getNodes().get(22)));


    }

    @Test
    public void testsimple5x5() {
        /**
         *
         * 20   21  22  23  24
         * 15   16  17  18  19
         * 10   11  12  13  14
         * 5    6   7   8   9
         * 0    1   2   3   4
         */

        p = new Plateau ("node_grille5x5","link_grille5x5");
        g = p.getG();


        Noeud r1 = g.getNodes().get(0);
        Noeud r2 = g.getNodes().get(1);
        Noeud r3 = g.getNodes().get(2);
        Noeud obj = g.getNodes().get(24);

        p.setRobots(r1,r2,r3);
        p.setObjectif(obj);

        Configuration source = new Configuration(p.getRobots());
        Configuration result = p.bruteForce(source,p.getObjectif());

        if(source!=result) {
            dik = new Dijkstra(p.getCoups());
            dik.plusCoutChemin(source,result);
        }
    }

    @Test
    public void testGrilleMur5x5() {
        /**
         *
         * 20   21  22  23  24
         * 15   X  17  X  19
         * 10   X  X   X  14
         * 5    6   7   8   9
         * 0    1   2   3   4
         */


        p = new Plateau ("node_grille_mur5x5","link_grille_mur5x5");
        g = p.getG();

        Noeud r1 = g.getNodes().get(0);
        Noeud r2 = g.getNodes().get(1);
        Noeud r3 = g.getNodes().get(2);
        Noeud obj = g.getNodes().get(17);

        p.setRobots(r1,r2,r3);
        p.setObjectif(obj);

        Configuration source = new Configuration(p.getRobots());
        Configuration result = p.bruteForce(source,p.getObjectif());

        if(source!=result) {
            dik = new Dijkstra(p.getCoups());
            dik.plusCoutChemin(source,result);
        }
    }


    @Test
    public void test2_5x5() {
        /**
         *
         * 20   21  22  23  24
         * 15   16  17  18  19
         * 10   11  12  13  14
         * 5    6   7   8   9
         * 0    1   2   3   4
         */

        p = new Plateau ("node_grille5x5","link_grille5x5");
        g = p.getG();


        Noeud r1 = g.getNodes().get(0);
        Noeud r2 = g.getNodes().get(1);
        Noeud r3 = g.getNodes().get(2);
        Noeud obj = g.getNodes().get(18);

        p.setRobots(r1,r2,r3);
        p.setObjectif(obj);

        Configuration source = new Configuration(p.getRobots());
        Configuration result = p.bruteForce(source,p.getObjectif());

        if(source!=result) {
            dik = new Dijkstra(p.getCoups());
            dik.plusCoutChemin(source,result);
        }
    }



    @Test
    public void TestfichierGrille_3() {


        /**
         * 12 13 X 15
         * 8  9  10 11
         * 4  5  X  7
         * 0  1  X  3
         */

        p = new Plateau ("nodeGrille_3","linkGrille_3");
        g = p.getG();

        Noeud r0 = g.getNodes().get(0);
        Noeud r1 = g.getNodes().get(1);
        Noeud r2 = g.getNodes().get(13);

        Noeud obj = g.getNodes().get(15);

        p.setRobots(r0,r1,r2);
        p.setObjectif(obj);


        assert(p.testContraintes( g.getNodes().get(12)));
        assert(!p.testContraintes( g.getNodes().get(1)));
        assert(!p.testContraintes( g.getNodes().get(8)));


        r0 = g.getNodes().get(1);
        r1 = g.getNodes().get(3);
        r2 = g.getNodes().get(13);
        obj = g.getNodes().get(15);

        p.setRobots(r0,r1,r2);
        p.setObjectif(obj);


        assert(!p.testContraintes( g.getNodes().get(13)));
        assert(p.testContraintes( g.getNodes().get(9)));
        assert(!p.testContraintes( g.getNodes().get(5)));
        assert(p.testContraintes( g.getNodes().get(0)));
        assert(!p.testContraintes( g.getNodes().get(3)));



        r0 = g.getNodes().get(0);
        r1 = g.getNodes().get(1);
        r2 = g.getNodes().get(4);
        obj = g.getNodes().get(11);

        p.setRobots(r0,r1,r2);
        p.setObjectif(obj);

        List<Configuration> ret = p.access(0);
        System.out.println("robot 0");
        for(Configuration c : ret ) {
            System.out.println(c.printConfig());
        }

        r0 = g.getNodes().get(4);
        r1 = g.getNodes().get(0);
        r2 = g.getNodes().get(1);
        p.setRobots(r0,r1,r2);
        ret = p.access(1);
        System.out.println("robot 1");
        for(Configuration c : ret ) {
            System.out.println(c.printConfig());
        }


        r0 = g.getNodes().get(1);
        r1 = g.getNodes().get(4);
        r2 = g.getNodes().get(0);
        p.setRobots(r0,r1,r2);

        ret = p.access(2);
        System.out.println("robot 2");
        for(Configuration c : ret ) {
            System.out.println(c.printConfig());
        }


    }




}
