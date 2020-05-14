package fr.uvsq.uvsq21602576.pglp_9_9.dao;

import fr.uvsq.uvsq21602576.pglp_9_9.formes.Carre;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Cercle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Rectangle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Triangle;

/**
 * Fabrique DAO.
 * Fonctionne avec une base de donnée embarqué Derby.
 * @author Flora
 */
public class FabriqueDAOJDBC extends FabriqueDAO {

    /**
     * Retourne le DAO pour le Carre.
     * @return DAO pour Carre
     */
    @Override
    public DAO<Carre> getCarreDAO() {
        return new CarreDAOJDBC();
    }

    /**
     * Retourne le DAO pour le Rectangle.
     * @return DAO pour Rectangle
     */
    @Override
    public DAO<Rectangle> getRectangleDAO() {
        return new RectangleDAOJDBC();
    }

    /**
     * Retourne le DAO pour le Triangle.
     * @return DAO pour Triangle
     */
    @Override
    public DAO<Triangle> getTriangleDAO() {
        return new TriangleDAOJDBC();
    }

    /**
     * Retourne le DAO pour le Cercle.
     * @return DAO pour Cercle
     */
    @Override
    public DAO<Cercle> getCercleDAO() {
        return new CercleDAOJDBC();
    }

    /**
     * Retourne le DAO pour le Dessin.
     * @return DAO pour Dessin
     */
    @Override
    public DAO<Dessin> getDessinDAO() {
        return new DessinDAOJDBC();
    }

}
