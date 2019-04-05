package graph;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphTest {

    private Graphe g;
    private Noeud n1;
    private Noeud n2;
    private Noeud n3;
    private Noeud n4;
    private Noeud n5;
    private Noeud n6;

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
            *           D  ___ E  __ F
            *               8      1
            * */


         n1 = new Noeud(0,"A" );
         n2 = new Noeud(1,"B" );
         n3 = new Noeud(2,"C" );
         n4 = new Noeud(3,"D" );
         n5 = new Noeud(4,"E" );
         n6 = new Noeud(5,"F" );

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);
        g.addNode(n6);


        n1.addLink(n2 ,1, 'N');
        n1.addLink(n3,4, 'S');

        n2.addLink(n4,8, 'O');
        n3.addLink(n4,7, 'E');

        n3.addLink(n5,1, 'E');
        n4.addLink(n5,8, 'O');

        n5.addLink(n6,1, 'N');



    }




    @Test
    public void ajouterNode(){
        Noeud N6 = new Noeud(5,"F" );
        g.addNode(N6);

        assertEquals(g.getNodes().size(),7);

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


        assertEquals(n1.calculerDistance(n2),1,0.1);
        assertEquals(n1.calculerDistance(n5),Double.MAX_VALUE,0.1);
    }

    @Test
    public void voisin(){


        List<Arc> voisin;
        List<Arc> attendu = new ArrayList<Arc>();
        attendu.add(new Arc(n3,n4,7, 'b'));
        attendu.add(new Arc(n3,n5,1, 'g'));

        Arc best;
        Arc bestAttendu = new Arc(n3,n5,1, 'b');
        voisin = n3.getArcs();


        for(int i =0 ; i< attendu.size() ; i++){
            assert(attendu.get(i).isEquals(voisin.get(i)));
        }

        //best = g.meilleurVoisin(voisin);

        //assert(best.isEquals(bestAttendu));
    }


    @Test
    public void DijkstraRoutageBasique() {
        List<Noeud> attendu = new ArrayList<Noeud>();
        List<Noeud> res = new ArrayList<Noeud>();

        attendu.add(n1);
        attendu.add(n3);
        attendu.add(n5);
        attendu.add(n6);

        res = g.djikstraRoutage(n1,n6);
        assert(g.isEqualsList(attendu,res));





        g.djikstraRoutage(n3,n5);

    }


    @Test
    public void CheckPath() {
        // boucle infinie :)
        // merci de lancer lalgo sur un chemin QUI EXISTE
        Noeud n7 = new Noeud(6,"G" );
        g.addNode(n7);

        assert(g.existeChemin(n1,n6));
        assert(!g.existeChemin(n1,n7));


    }


    @Test
    public void TSP() {
        Noeud n7 = new Noeud(6,"G" );
        g.addNode(n7);

        List<Noeud> attendu = new ArrayList<Noeud>();
        attendu.add(n1);
        attendu.add(n2);

        List<Noeud> res = g.plusCoutChemin(n1,n2);
        assert (g.isEqualsList(res,attendu));


        res = g.plusCoutChemin(n1,n7);
        attendu = new ArrayList<Noeud>();
        assert (g.isEqualsList(res,attendu));

    }

    @Test
    public void angleTest() {
        assertEquals(180, n1.getArcs().get(0).angle(n1.getArcs().get(1)));  //N - S
        assertEquals(-90, n1.getArcs().get(0).angle(n2.getArcs().get(0)));  //N - O
        assertEquals(0, n2.getArcs().get(0).angle(n2.getArcs().get(0)));  //O - O
        assertEquals(0, n1.getArcs().get(0).angle(n1.getArcs().get(0)));  //N - N
        assertEquals(0, n1.getArcs().get(1).angle(n1.getArcs().get(1)));  //S - S
        assertEquals(-90, n1.getArcs().get(1).angle(n3.getArcs().get(0)));  //S - E
        assertEquals(0, n3.getArcs().get(0).angle(n3.getArcs().get(0)));  //E - E
    }


    @Test
    public void detailTrajet() {
        List<Noeud> chemin =g.djikstraRoutage(n1,n6);
        System.out.println(g.detailTrajet(chemin));
    }





















}
