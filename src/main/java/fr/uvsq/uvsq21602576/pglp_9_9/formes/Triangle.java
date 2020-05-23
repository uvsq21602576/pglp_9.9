package fr.uvsq.uvsq21602576.pglp_9_9.formes;

/**
 * Représente un triangle.
 * Forme de trois points.
 * @author Flora
 */
public class Triangle extends Forme {
    /** Deuxième point. */
    private Point point2;
    /** Troisième point. */
    private Point point3;

    /**
     * Constructeur.
     * Crée un triangle à l'aide de 3 points.
     * @param n Nom de la variable
     * @param p1 Premier point
     * @param p2 Deuxième point
     * @param p3 Troisième point
     */
    public Triangle(final String n, final Point p1, final Point p2,
            final Point p3) {
        super(n, p1);
        this.point2 = p2;
        this.point3 = p3;
    }

    /**
     * Deplace le triangle selon le vecteur de déplacment dP.
     * Déplace les trois points selon le même vecteur.
     * @param dP Vecteur de déplacement
     */
    @Override
    public void deplace(final Point dP) {
        super.deplace(dP);
        this.point2.deplace(dP);
        this.point3.deplace(dP);
    }

    /**
     * Retourne une copie du deuxième point du Triangle.
     * @return Copie du deuxième point
     */
    public Point getPoint2() {
        return this.point2.copie();
    }

    /**
     * Retourne une copie du troisième point du Triangle.
     * @return Copie du troisième point
     */
    public Point getPoint3() {
        return this.point3.copie();
    }

    /**
     * Renvoie la copie exacte du triangle.
     * @return Copie exacte du triangle
     */
    @Override
    public ComposantDessin copie() {
        return new Triangle(super.getNom(), super.getPointReference(),
                this.point2, this.point3);
    }

    /**
     * Retourne le haché correspondant.
     * @return Haché
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((point2 == null) ? 0 : point2.hashCode());
        result = prime * result + ((point3 == null) ? 0 : point3.hashCode());
        return result;
    }

    /**
     * Teste si les deux Triangles sont égaux.
     * Deux Triangles sont égaux si leurs 3 points sont égaux, ainsi que leur
     * nom.
     * @param obj Triangle à comparer
     * @return true si égaux, false sinon
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Triangle)) {
            return false;
        }
        Triangle other = (Triangle) obj;
        if (point2 == null) {
            if (other.point2 != null) {
                return false;
            }
        } else if (!point2.equals(other.point2)) {
            return false;
        }
        if (point3 == null) {
            if (other.point3 != null) {
                return false;
            }
        } else if (!point3.equals(other.point3)) {
            return false;
        }
        return true;
    }

    /**
     * Retourne une représentation textuelle.
     * Sous la forme "Triangle(point1=?,point2=?,point3=?)"
     * @return Représentation textuelle
     */
    @Override
    public String toString() {
        return super.getNom() + " : Triangle(point1="
                + super.getPointReference().toString() + ",point2="
                + this.point2.toString() + ",point3=" + this.point3.toString()
                + ")";
    }

    /**
     * Retourne une représentation textuelle.
     * Avec des tabulations pour la profondeur.
     * @param profondeur Profondeur dans l'arbre de Composant
     * @return Représentation textuelle
     */
    @Override
    public String toString(final int profondeur) {
        String s = "";
        for (int i = 0; i < profondeur; i++) {
            s = s.concat("    ");
        }
        s = s.concat("|- ");
        return s.concat(this.toString());
    }
}
