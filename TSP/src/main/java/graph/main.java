package graph;

public class main {
    public static void main (String [] args) {


            /*          A
                      /   \
            *        B      C
            *         \   /   \
            *           D  ___ E
            *
            * */


            Graphe g = new Graphe();

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


            g.addLink(n1, n2 ,1);
            g.addLink(n1, n3,4);

            g.addLink(n2, n4,8);
            g.addLink(n3, n4,7);

            g.addLink(n3, n5,1);
            g.addLink(n4, n5,8);



        }
    }

