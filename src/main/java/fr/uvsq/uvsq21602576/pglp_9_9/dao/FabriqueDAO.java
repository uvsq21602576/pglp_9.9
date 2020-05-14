package fr.uvsq.uvsq21602576.pglp_9_9.dao;

import fr.uvsq.uvsq21602576.pglp_9_9.formes.Carre;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Cercle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Rectangle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Triangle;

/**
 * Fabrique abstraite DAO.
 * Pour récupérer la bonne fabrique pour le type recherché.
 * @author Flora
 */
public abstract class FabriqueDAO {
    /**
     * Types de DAO implémentés.
     */
    public enum TypeDAO {
        /**
         * Avec une base de donnée.
         */
        JDBC;
    }

    /**
     * Retourne le DAO pour le Carre.
     * @return DAO pour Carre
     */
    public abstract DAO<Carre> getCarreDAO();

    /**
     * Retourne le DAO pour le Rectangle.
     * @return DAO pour Rectangle
     */
    public abstract DAO<Rectangle> getRectangleDAO();

    /**
     * Retourne le DAO pour le Triangle.
     * @return DAO pour Triangle
     */
    public abstract DAO<Triangle> getTriangleDAO();

    /**
     * Retourne le DAO pour le Cercle.
     * @return DAO pour Cercle
     */
    public abstract DAO<Cercle> getCercleDAO();

    /**
     * Retourne le DAO pour le Dessin.
     * @return DAO pour Dessin
     */
    public abstract DAO<Dessin> getDessinDAO();

    /**
     * Retorune la fabriqueDAO correspondante au type.
     * @param type typeDAO recherché
     * @return FabriqueDAO
     */
    public static FabriqueDAO getFabriqueDAO(final TypeDAO type) {
        if (type == TypeDAO.JDBC) {
            return new FabriqueDAOJDBC();
        }
        return null;
    }
}
