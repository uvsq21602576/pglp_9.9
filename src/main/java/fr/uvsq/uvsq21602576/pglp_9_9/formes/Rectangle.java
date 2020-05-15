package fr.uvsq.uvsq21602576.pglp_9_9.formes;

/**
 * Représente un rectangle.
 * Une forme avec deux points.
 * @author Flora
 */
public class Rectangle extends Forme {
    /** Point en bas à droite. */
    private Point basDroit;

    /**
     * Constructeur.
     * Crée un rectangle avec deux points et un nom.
     * @param nom Nom de la forme.
     * @param hg Point haut gauche
     * @param bd Point bas droit
     */
    public Rectangle(final String nom, final Point hg, final Point bd) {
        super(nom, hg);
        this.basDroit = bd;
    }

    /**
     * Retourne le haché correspondant.
     * @return Haché
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((basDroit == null) ? 0 : basDroit.hashCode());
        return result;
    }

    /**
     * Teste si les deux Rectangles sont égaux.
     * Deux Rectangles sont égaux si leurs 2 points sont égaux, ainsi que leur
     * nom.
     * @param obj Rectangle à comparer
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
        if (!(obj instanceof Rectangle)) {
            return false;
        }
        Rectangle other = (Rectangle) obj;
        if (basDroit == null) {
            if (other.basDroit != null) {
                return false;
            }
        } else if (!basDroit.equals(other.basDroit)) {
            return false;
        }
        return true;
    }

    /**
     * Retourne la longueur du rectangle.
     * @return Longueur
     */
    public int getLongueur() {
        int l = this.basDroit.getX() - super.getPointReference().getX();
        return (l < 0) ? -l : l;
    }

    /**
     * Retourne une copie du point bas droit du rectangle.
     * @return Point bas droit
     */
    public Point getPointBasDroit() {
        return basDroit.copie();
    }

    /**
     * Deplace le rectangle selon le vecteur de déplacement dP.
     * Déplace les deux points selon le même vecteur.
     * @param dP Vecteur de déplacement
     */
    public void deplace(final Point dP) {
        super.deplace(dP);
        this.basDroit.deplace(dP);
    }

    /**
     * Retourne une représentation textuelle.
     * Sous la forme "Rectangle(HautGauche=?,BasDroit=?)"
     * @return Représentation textuelle
     */
    @Override
    public String toString() {
        return super.getNom() + " : Rectangle(HautGauche="
                + super.getPointReference().toString() + ",BasDroit="
                + this.basDroit.toString() + ")";
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
        s = s.concat("|-");
        return s.concat(this.toString());
    }
}
