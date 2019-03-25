package graph;

public class Arc {
    private Noeud debut;
    private Noeud fin;
    private double cout;
    private char direction;



    public Arc (Noeud n1 , Noeud n2 , double c, char d) {
        debut = n1;
        fin = n2;
        cout = c;
        direction = d;
    }



    public double getCout() {
        return cout;
    }

    public Noeud getDebut() {
        return debut;
    }

    public Noeud getFin() {
        return fin;
    }

    public char getDirection() { return direction; }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public void setDebut(Noeud debut) {
        this.debut = debut;
    }

    public void setFin(Noeud fin) {
        this.fin = fin;
    }

    public void setDirection(char d) {
        this.direction = d;
    }


    public boolean isEquals(Arc a) {
        boolean deb = debut.equals(a.getDebut());
        boolean fi = fin.equals(a.getFin());

        return (cout == a.getCout() && deb && fi);

    }

    public int angle (Arc a) {
        int angle = 0;

        if ((this.direction == 'N' || this.direction == 'S' || this.direction == 'O' || this.direction == 'E')
            && (a.direction == 'N' || a.direction == 'S' || a.direction == 'O' || a.direction == 'E')) {
            if (this.direction == a.direction) {
                angle = 0;
            } else if (this.direction == 'N' && a.direction == 'E'
                    || this.direction == 'O' && a.direction == 'N'
                    || this.direction == 'E' && a.direction == 'S'
                    || this.direction == 'S' && a.direction == 'O') {
                angle = 90;
            } else if (this.direction == 'E' && a.direction == 'N'
                    || this.direction == 'O' && a.direction == 'S'
                    || this.direction == 'S' && a.direction == 'E'
                    || this.direction == 'N' && a.direction == 'O') {
                angle = -90;
            } else {
                angle = 180;
            }
        }

        return angle;
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
