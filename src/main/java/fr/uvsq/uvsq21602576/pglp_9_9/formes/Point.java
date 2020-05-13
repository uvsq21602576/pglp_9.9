package fr.uvsq.uvsq21602576.pglp_9_9.formes;

/**
 * Réprésente un point en 2 dimensions.
 * @author Flora
 */
public class Point implements Deplacable {
    /** Coordonnées X. */
    private int x;
    /** Coordonnées Y. */
    private int y;

    /**
     * Constructeur.
     * Crée un point à partir de ces coordonnées.
     * @param coordX Coordonnée X
     * @param coordY Coordonnée Y
     */
    public Point(final int coordX, final int coordY) {
        this.x = coordX;
        this.y = coordY;
    }

    /**
     * Deplace le point selon le vecteur de déplacment dP.
     * @param dP Vecteur de déplacement
     */
    public void deplace(final Point dP) {
        this.x += dP.getX();
        this.y += dP.getY();
    }

    /**
     * Retourne la coordonnées X.
     * @return X
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la coordonnées Y.
     * @return Y
     */
    public int getY() {
        return y;
    }

    /**
     * Retourne le haché correspondant.
     * @return Haché
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    /**
     * Teste si les deux Points sont égaux.
     * Deux Points sont égaux s'ils ont les memes coordonées.
     * @param obj Point à comparer
     * @return true si égaux, false sinon
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        if (x != other.x) {
            return false;
        }
        if (y != other.y) {
            return false;
        }
        return true;
    }

    /**
     * Retourne une représentation textuelle.
     * Sous la forme "(X, Y)"
     * @return Représentation textuelle
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
