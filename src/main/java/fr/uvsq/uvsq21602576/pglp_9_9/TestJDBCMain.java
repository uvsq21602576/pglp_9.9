package fr.uvsq.uvsq21602576.pglp_9_9;

import fr.uvsq.uvsq21602576.pglp_9_9.dao.DAO;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.FabriqueDAO;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.CreationObjetException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DeletionException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DoublonException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.InexistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.MisAJourException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.RechercheException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Carre;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;

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
     * Teste pour carré.
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
     * Teste pour Dessin.
     */
    public void testJDBCDessin() {
        DAO<Dessin> daoD = FabriqueDAO.getFabriqueDAO(FabriqueDAO.TypeDAO.JDBC)
                .getDessinDAO();
        
        // CREATE
        Carre c = new Carre("c1", new Point(4, 1), 20);
        Carre c2 = new Carre("c2", new Point(54, 1), 200);
        Dessin d = new Dessin("sousD");
        d.ajoute(c2);
        Dessin actual = new Dessin("actual");
        actual.ajoute(c);
        actual.ajoute(d);
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
        actual.ajoute(cM);
        actual.retire(c);
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
        //testMAIN.testJDBCCarre();
        testMAIN.testJDBCDessin();
    }
}
