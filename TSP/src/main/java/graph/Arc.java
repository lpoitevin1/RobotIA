package graph;

public class Arc {
    private Noeud debut;
    private Noeud fin;
    private double cout;




    public Arc (Noeud n1 , Noeud n2 , double c) {
        debut = n1;
        fin = n2;
        cout = c;
    }


    public Arc () {}



    public double getCout() {
        return cout;
    }

    public Noeud getDebut() {
        return debut;
    }

    public Noeud getFin() {
        return fin;
    }


    public void setCout(double cout) {
        this.cout = cout;
    }

    public void setDebut(Noeud debut) {
        this.debut = debut;
    }

    public void setFin(Noeud fin) {
        this.fin = fin;
    }


    /**
     * Test egalité Arc
     * @param a Arc a tester
     * @return boolean
     */
    public boolean isEquals(Arc a) {
        boolean deb = debut.equals(a.getDebut());
        boolean fi = fin.equals(a.getFin());

        return (cout == a.getCout() && deb && fi);

    }




    @Override
    public String toString() {
        return super.toString();
    }
}
