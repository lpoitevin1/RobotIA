package graph;

public class main {
    public static void main (String [] args) {

            Graphe g = new Graphe(6);

            Noeud N1 = new Noeud(0,"A" , 2 ,2);
            Noeud N2 = new Noeud(1,"B" , 2 ,3);
            Noeud N3 = new Noeud(2,"C" , 3 ,3);
            Noeud N4 = new Noeud(3,"D" , 2 ,9);
            Noeud N5 = new Noeud(4,"E" , 5 ,3);
            Noeud N6 = new Noeud(5,"F" , 3 ,7);

            g.addNode(N1, N1);
            g.addNode(N1, N2);
            g.addNode(N1, N3);

            g.addNode(N2, N3);

            g.addNode(N3, N4);
            g.addNode(N3, N5);

            g.addNode(N4, N6);



            g.parcours(N1);





        }
    }

