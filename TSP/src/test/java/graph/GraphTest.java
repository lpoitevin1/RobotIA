package graph;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class GraphTest {

    private Graphe g;

    /**
     * setup the Graph
     */
    @Before
    public void setUp() {
        g = new Graphe(6);
    }

    /**
     * Add a node
     */
    @Test
    public void calculDistance() {
        Noeud N1 = new Noeud(0,"A" , 0 ,0);
        Noeud N2 = new Noeud(1,"B" , 1 ,1);
        Noeud N3 = new Noeud(2,"C" , 2 ,2);

       assertEquals(g.calculerDistance(N1,N2),2,0.1);
       assertEquals(g.calculerDistance(N1,N3),4,0.1);
        assertEquals(g.calculerDistance(N3,N2),2,0.1);
    }

    @Test
    public void parcours() {
        Graphe g2 = new Graphe(6);

        Noeud N1 = new Noeud(0,"A" , 0 ,0);
        Noeud N2 = new Noeud(1,"B" , 1 ,1);
        Noeud N3 = new Noeud(2,"C" , 2 ,2);
        Noeud N4 = new Noeud(3,"D" , 2 ,2);
        Noeud N5 = new Noeud(4,"E" , 4 ,6);


        g2.addNode(N1, N1);
        g2.addNode(N1, N2); //(A,B)
        g2.addNode(N1, N3); //(A,C)
        g2.addNode(N1, N4); //(A,D)

        g2.addNode(N2, N3);

        g2.addNode(N3, N4);
        g2.addNode(N3, N5);





        g2.parcours(N1);
    }



    @Test
    public void affiche() {
        Graphe g2 = new Graphe(6);

        Noeud N1 = new Noeud(0,"A" , 0 ,0);
        Noeud N2 = new Noeud(1,"B" , 1 ,1);
        Noeud N3 = new Noeud(2,"C" , 2 ,2);
        Noeud N4 = new Noeud(3,"D" , 2 ,2);
        Noeud N5 = new Noeud(4,"E" , 4 ,6);

        g2.addNode(N1, N2); //(A,B)
        g2.addNode(N1, N3); //(A,C)
        g2.addNode(N1, N4); //(A,D)

        g2.addNode(N2, N3);

        g2.addNode(N3, N4);
        g2.addNode(N3, N5);



        g2.afficher(N3);
    }



}
