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


            n1.addLink(n2 ,1);
            n1.addLink(n3,4);

            n2.addLink(n4,8);
            n3.addLink(n4,7);

            n3.addLink(n5,1);
            n4.addLink(n5,8);



        }
    }

