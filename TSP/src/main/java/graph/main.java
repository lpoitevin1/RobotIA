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

            Noeud n1 = new Noeud(0);
            Noeud n2 = new Noeud(1);
            Noeud n3 = new Noeud(2);
            Noeud n4 = new Noeud(3);
            Noeud n5 = new Noeud(4);

            g.addNode(n1);
            g.addNode(n2);
            g.addNode(n3);
            g.addNode(n4);
            g.addNode(n5);


            n1.addDualLink(n2 ,1);
            n1.addDualLink(n3,4);

            n2.addDualLink(n4,8);
            n3.addDualLink(n4,7);

            n3.addDualLink(n5,1);
            n4.addDualLink(n5,8);

        }
}

