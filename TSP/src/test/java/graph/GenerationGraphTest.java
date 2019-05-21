package graph;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class GenerationGraphTest {
    private LectureFichier lect;
    private Graphe g;
    private Noeud n1;
    private Noeud n2;
    private Noeud n3;

    /**
     * setup the Graph
     */
    @Before
    public void setUp() {
        /*
               n3 __ n1 __ n2
                   1    1
         */
        g = new Graphe();

        lect = new LectureFichier("nodesTest","linksTest");

        n1 = new Noeud(0,"A" , 0,0 );
        n2 = new Noeud(1,"B" , 1 ,0 );
        n3 = new Noeud(2,"C" , -1,0 );


        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);



        n1.addLink(n2 ,1, 'O');
        n1.addLink(n3,1, 'E');

    }

    @Test
    public void Dijkstra(){
        List<Noeud> chemin = g.djikstraRoutage(n1,n3);
        System.out.println(g.detailTrajet(chemin));
    }

    @Test
    public void lectureNoeuds(){
        lect.lectureNoeuds();
    }


    @Test
    public void generationAuto() {
        g.construirereGrapheLecture("nodesTest", "linksTest");

        Noeud n1 = g.getNodes().get(0);
        Noeud n2 = g.getNodes().get(1);
        List<Noeud> chemin;

        chemin = g.djikstraRoutage(n1,n2);
        System.out.println(g.detailTrajet(chemin));

        System.out.println("------------------------------");
        n2 = g.getNodes().get(3);
        chemin = g.djikstraRoutage(n1,n2);
        System.out.println(g.detailTrajet(chemin));

        System.out.println("------------------------------");
        n2 = g.getNodes().get(4);
        chemin = g.djikstraRoutage(n1,n2);
        System.out.println(g.detailTrajet(chemin));
    }


}
