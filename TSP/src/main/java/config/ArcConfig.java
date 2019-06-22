package config;

public class ArcConfig {
        private Configuration debut;
        private Configuration fin;
        private double cout;

        public ArcConfig (Configuration n1, Configuration n2, double c) {
            debut = n1;
            fin = n2;
            cout = c;
        }


        public ArcConfig () {}



        public double getCout() {
            return cout;
        }

        public Configuration getDebut() {
            return debut;
        }

        public Configuration getFin() {
            return fin;
        }


        public void setCout(double cout) {
            this.cout = cout;
        }

        public void setDebut(Configuration debut) {
            this.debut = debut;
        }

        public void setFin(Configuration fin) {
            this.fin = fin;
        }


        @Override
        public String toString() {
            return super.toString();
        }
}

