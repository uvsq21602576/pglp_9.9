package fr.uvsq.uvsq21602576.pglp_9_9;

import fr.uvsq.uvsq21602576.pglp_9_9.dao.DAO;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.FabriqueDAO;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.CreationObjetException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DeletionException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DoublonException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.InexistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.MisAJourException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.RechercheException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Carre;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Cercle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Rectangle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Triangle;

/**
 * Classe Main pour tester JDBC.
 * @author Flora
 */
public enum TestJDBCMain {
    /**
     * Main test.
     */
    testMAIN;

    /**
     * Test pour carré.
     */
    public void testJDBCCarre() {
        DAO<Carre> daoC = FabriqueDAO.getFabriqueDAO(FabriqueDAO.TypeDAO.JDBC)
                .getCarreDAO();
        // CREATE
        Carre c = new Carre("c1", new Point(4, 1), 20);
        System.out.println(c.toString());
        try {
            daoC.create(c);
            System.out.println("Creation réussie.");
        } catch (DoublonException e) {
            System.err.println(e.getMessage());
        } catch (CreationObjetException e) {
            e.printStackTrace();
        }
        // FIND
        try {
            Carre c2 = daoC.find("c1");
            System.out.println("Retour : " + c2.toString());
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }

        // UPDATE
        Carre cM = new Carre("c1", new Point(5, 48), 22);
        System.out.println("Modifié : " + cM.toString());
        try {
            daoC.update(cM);
            System.out.println("Modification réussie.");
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (MisAJourException e) {
            e.printStackTrace();
        }
        try {
            Carre c2M = daoC.find("c1");
            System.out.println("Retour : " + c2M.toString());
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }

        // DELETE
        try {
            daoC.delete(c);
            System.out.println("Suppression réussie.");
        } catch (DeletionException e) {
            e.printStackTrace();
        }
        try {
            Carre c3 = daoC.find("c1");
            System.out.println("Retour : " + c3.toString());
        } catch (InexistantException e) {
            System.out.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test pour carré.
     */
    public void testJDBCCercle() {
        DAO<Cercle> daoC = FabriqueDAO.getFabriqueDAO(FabriqueDAO.TypeDAO.JDBC)
                .getCercleDAO();
        // CREATE
        Cercle c = new Cercle("c1", new Point(4, 1), 20);
        System.out.println(c.toString());
        try {
            daoC.create(c);
            System.out.println("Creation réussie.");
        } catch (DoublonException e) {
            System.err.println(e.getMessage());
        } catch (CreationObjetException e) {
            e.printStackTrace();
        }
        // FIND
        try {
            Cercle c2 = daoC.find("c1");
            System.out.println("Retour : " + c2.toString());
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }

        // UPDATE
        Cercle cM = new Cercle("c1", new Point(5, 48), 22);
        System.out.println("Modifié : " + cM.toString());
        try {
            daoC.update(cM);
            System.out.println("Modification réussie.");
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (MisAJourException e) {
            e.printStackTrace();
        }
        try {
            Cercle c2M = daoC.find("c1");
            System.out.println("Retour : " + c2M.toString());
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }

        // DELETE
        try {
            daoC.delete(c);
            System.out.println("Suppression réussie.");
        } catch (DeletionException e) {
            e.printStackTrace();
        }
        try {
            Cercle c3 = daoC.find("c1");
            System.out.println("Retour : " + c3.toString());
        } catch (InexistantException e) {
            System.out.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test pour rectangle.
     */
    public void testJDBCRectangle() {
        DAO<Rectangle> daoR = FabriqueDAO
                .getFabriqueDAO(FabriqueDAO.TypeDAO.JDBC).getRectangleDAO();
        // CREATE
        Rectangle r = new Rectangle("r1", new Point(4, 1), new Point(5, 4));
        System.out.println(r.toString());
        try {
            daoR.create(r);
            System.out.println("Creation réussie.");
        } catch (DoublonException e) {
            System.err.println(e.getMessage());
        } catch (CreationObjetException e) {
            e.printStackTrace();
        }
        // FIND
        try {
            Rectangle retour = daoR.find("r1");
            System.out.println("Retour : " + retour.toString());
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }

        // UPDATE
        Rectangle rM = new Rectangle("r1", new Point(5, 48), new Point(45, 8));
        System.out.println("Modifié : " + rM.toString());
        try {
            daoR.update(rM);
            System.out.println("Modification réussie.");
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (MisAJourException e) {
            e.printStackTrace();
        }
        try {
            Rectangle retour = daoR.find("r1");
            System.out.println("Retour : " + retour.toString());
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }

        // DELETE
        try {
            daoR.delete(r);
            System.out.println("Suppression réussie.");
        } catch (DeletionException e) {
            e.printStackTrace();
        }
        try {
            Rectangle retour = daoR.find("r1");
            System.out.println("Retour : " + retour.toString());
        } catch (InexistantException e) {
            System.out.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test pour rectangle.
     */
    public void testJDBCTriangle() {
        DAO<Triangle> daoT = FabriqueDAO
                .getFabriqueDAO(FabriqueDAO.TypeDAO.JDBC).getTriangleDAO();
        // CREATE
        Triangle t = new Triangle("t1", new Point(4, 1), new Point(5, 4),
                new Point(5, 4));
        System.out.println(t.toString());
        try {
            daoT.create(t);
            System.out.println("Creation réussie.");
        } catch (DoublonException e) {
            System.err.println(e.getMessage());
        } catch (CreationObjetException e) {
            e.printStackTrace();
        }
        // FIND
        try {
            Triangle retour = daoT.find("t1");
            System.out.println("Retour : " + retour.toString());
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }

        // UPDATE
        Triangle tM = new Triangle("t1", new Point(5, 48), new Point(45, 8),
                new Point(45, 8));
        System.out.println("Modifié : " + tM.toString());
        try {
            daoT.update(tM);
            System.out.println("Modification réussie.");
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (MisAJourException e) {
            e.printStackTrace();
        }
        try {
            Triangle retour = daoT.find("t1");
            System.out.println("Retour : " + retour.toString());
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }

        // DELETE
        try {
            daoT.delete(t);
            System.out.println("Suppression réussie.");
        } catch (DeletionException e) {
            e.printStackTrace();
        }
        try {
            Triangle retour = daoT.find("t1");
            System.out.println("Retour : " + retour.toString());
        } catch (InexistantException e) {
            System.out.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }
    }

    /**
     * Teste pour Dessin.
     */
    public void testJDBCDessin() {
        DAO<Dessin> daoD = FabriqueDAO.getFabriqueDAO(FabriqueDAO.TypeDAO.JDBC)
                .getDessinDAO();

        // CREATE
        Carre c = new Carre("c1", new Point(4, 1), 20);
        Carre c2 = new Carre("c2", new Point(54, 1), 200);
        Rectangle r = new Rectangle("r1", new Point(4, 1), new Point(5, 4));
        Rectangle r2 = new Rectangle("r2", new Point(5, 48), new Point(45, 8));
        Cercle ce = new Cercle("ce1", new Point(4, 1), 230);
        Cercle ce2 = new Cercle("ce2", new Point(7, 13), 220);
        Triangle t = new Triangle("t1", new Point(5, 48), new Point(45, 8),
                new Point(45, 8));
        Triangle t2 = new Triangle("t2", new Point(50987, 48),
                new Point(45987, 8), new Point(45, 8));
        Dessin d = new Dessin("sousD");
        try {
            d.ajoute(c2);
        } catch (DejaExistantException e1) {
            System.err.println(e1.getMessage());
        }
        try {
            d.ajoute(r);
        } catch (DejaExistantException e1) {
            System.err.println(e1.getMessage());
        }
        try {
            d.ajoute(r2);
        } catch (DejaExistantException e1) {
            System.err.println(e1.getMessage());
        }
        try {
            d.ajoute(ce);
        } catch (DejaExistantException e1) {
            System.err.println(e1.getMessage());
        }
        try {
            d.ajoute(t2);
        } catch (DejaExistantException e1) {
            System.err.println(e1.getMessage());
        }
        Dessin actual = new Dessin("actual");
        try {
            actual.ajoute(ce2);
        } catch (DejaExistantException e1) {
            System.err.println(e1.getMessage());
        }
        try {
            actual.ajoute(c);
        } catch (DejaExistantException e1) {
            System.err.println(e1.getMessage());
        }
        try {
            actual.ajoute(d);
        } catch (DejaExistantException e1) {
            System.err.println(e1.getMessage());
        }
        System.out.println(actual.toString());
        try {
            daoD.create(actual);
            System.out.println("Creation réussie.");
        } catch (DoublonException e) {
            System.err.println(e.getMessage());
        } catch (CreationObjetException e) {
            e.printStackTrace();
        }
        // FIND
        try {
            Dessin rendu = daoD.find("actual");
            System.out.println("Retour :\n" + rendu.toString());
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }

        // UPDATE
        Carre cM = new Carre("cM", new Point(5, 48), 22);
        try {
            actual.ajoute(cM);
        } catch (DejaExistantException e1) {
            System.err.println(e1.getMessage());
        }
        actual.retire(c);
        actual.retire(ce2);
        try {
            actual.ajoute(t);
        } catch (DejaExistantException e1) {
            System.err.println(e1.getMessage());
        }
        d.retire(r2);
        d.deplace(new Point(-1, -1));
        System.out.println("Modifié : " + actual.toString());
        try {
            daoD.update(actual);
            System.out.println("Modification réussie.");
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (MisAJourException e) {
            e.printStackTrace();
        }
        try {
            Dessin rendu = daoD.find("actual");
            System.out.println("Retour :\n" + rendu.toString());
        } catch (InexistantException e) {
            System.err.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }

        // DELETE
        try {
            daoD.delete(actual);
            System.out.println("Suppression réussie.");
        } catch (DeletionException e) {
            e.printStackTrace();
        }
        try {
            Dessin rendu = daoD.find("actual");
            System.out.println("Retour :\n" + rendu.toString());
        } catch (InexistantException e) {
            System.out.println(e.getMessage());
        } catch (RechercheException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main.
     * @param args arguments
     */
    public static void main(final String[] args) {
        // testMAIN.testJDBCCercle();
        // testMAIN.testJDBCCarre();
        // testMAIN.testJDBCRectangle();
        // testMAIN.testJDBCTriangle();
        testMAIN.testJDBCDessin();
    }
}
