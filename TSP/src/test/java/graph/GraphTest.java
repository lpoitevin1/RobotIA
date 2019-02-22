package graph;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphTest {

    private Graphe g;

    /**
     * setup the Graph
     */
    @Before
    public void setUp() {
        g = new Graphe();

            /*          A
                     1 /   \ 4
            *        B      C
            *       8 \  7/   \ 1
            *           D  ___ E
            *               8
            * */


        Noeud n1 = new Noeud(0,"A" );
        Noeud n2 = new Noeud(1,"B" );
        Noeud n3 = new Noeud(2,"C" );
        Noeud n4 = new Noeud(3,"D" );
        Noeud n5 = new Noeud(4,"E" );

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);


        n1.addLink(n2 ,1);
        n1.addLink(n3,4);

        n2.addLink(n4,8);
        n3.addLink(n4,7);

        n3.addLink(n5,1);
        n4.addLink(n5,8);



    }




    @Test
    public void ajouterNode(){
        Noeud N6 = new Noeud(5,"F" );
        g.addNode(N6);

        assertEquals(g.getNodes().size(),6);

    }

    @Test
    public void printArcs(){

        for (Noeud n : g.getNodes()) {
            n.printArcs();
            System.out.println ();
        }
    }


    @Test
    public void calculerDistance(){
        Noeud n1 = g.getNodes().get(0);
        Noeud n2 = g.getNodes().get(1);
        Noeud n3 = g.getNodes().get(2);
        Noeud n5 = g.getNodes().get(4);

        assertEquals(n1.calculerDistance(n2),1,0.1);
        assertEquals(n1.calculerDistance(n5),Double.MAX_VALUE,0.1);
    }

    @Test
    public void voisin(){
        Noeud n1 = g.getNodes().get(0);
        Noeud n2 = g.getNodes().get(1);
        Noeud n3 = g.getNodes().get(2);
        Noeud n4 = g.getNodes().get(3);
        Noeud n5 = g.getNodes().get(4);

        List<Arc> voisin;
        List<Arc> attendu = new ArrayList<Arc>();
        attendu.add(new Arc(n3,n4,7));
        attendu.add(new Arc(n3,n5,1));

        Arc best;
        Arc bestAttendu = new Arc(n3,n5,1);
        voisin = n3.getArcs();


        for(int i =0 ; i< attendu.size() ; i++){
            assert(attendu.get(i).isEquals(voisin.get(i)));
        }

        //best = g.meilleurVoisin(voisin);

        //assert(best.isEquals(bestAttendu));
    }



    @Test
    public void Dijkstra() {

        Noeud n1 = g.getNodes().get(0);
        Noeud n3 = g.getNodes().get(2);
        Noeud n5 = g.getNodes().get(4);
        g.dijkstra(n1,n5);

    }

    @Test
    public void Dijkstra2() {

        Noeud n1 = g.getNodes().get(0);
        Noeud n3 = g.getNodes().get(2);
        Noeud n5 = g.getNodes().get(4);
        g.djikstrav2(n1);

    }






















}
