package fr.uvsq.uvsq21602576.pglp_9_9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DeplacementImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DessinGlobalException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;

/**
 * Teste la classe Etat.
 * @author Flora
 */
public class EtatTest {
    
    /**
     * Teste la méthode getTotaliteDessinCourant.
     * @throws DeplacementImpossibleException Si le déplacement dans le sous
     *         dessin est impossible
     * @throws DejaExistantException Si un composant du meme nom existe déjà
     *         dans le dessin
     * @throws DessinGlobalException Si le dessin prend le meme nom que le
     *         dessin global
     */
    @Test
    public void getTotaliteDessinCourantTest() throws DeplacementImpossibleException,
            DejaExistantException, DessinGlobalException {
        Etat etat = new Etat();
        Dessin d = new Dessin("d");
        etat.getDessinCourant().ajoute(d);
        etat.voirSousDessin("d");
        Dessin d2 = new Dessin("d2");
        etat.getDessinCourant().ajoute(d2);
        etat.voirSousDessin("d2");
        Dessin actual = etat.getTotaliteDessinCourant();
        assertTrue(d == actual);
    }
    
    /**
     * Teste la méthode getTotaliteDessinCourant, avec global.
     * @throws DeplacementImpossibleException Si le déplacement dans le sous
     *         dessin est impossible
     * @throws DejaExistantException Si un composant du meme nom existe déjà
     *         dans le dessin
     * @throws DessinGlobalException Si le dessin prend le meme nom que le
     *         dessin global
     */
    @Test
    public void getTotaliteDessinCourantGlobalTest() throws DeplacementImpossibleException,
            DejaExistantException, DessinGlobalException {
        Etat etat = new Etat();
        Dessin actual = etat.getTotaliteDessinCourant();
        assertEquals(Dessin.GLOBAL, actual.getNom());
    }

    /**
     * Teste la méthode voir sousDessin avec un seul sous dessin.
     * @throws DeplacementImpossibleException SI le déplacement dans le sous
     *         dessin est impossible
     * @throws DejaExistantException Si un composant du meme nom existe déjà
     *         dans le dessin
     * @throws DessinGlobalException Si le dessin prend le meme nom que le
     *         dessin global
     */
    @Test
    public void voirSousDessinTest() throws DeplacementImpossibleException,
            DejaExistantException, DessinGlobalException {
        Etat etat = new Etat();
        Dessin d = new Dessin("d");
        etat.getDessinCourant().ajoute(d);
        etat.voirSousDessin("d");
        assertEquals(d, etat.getDessinCourant());
        assertTrue(etat.getParenteDessin().isEmpty());
    }

    /**
     * Teste la méthode voir sousDessin avec deux sous dessins.
     * @throws DeplacementImpossibleException Si le déplacement dans le sous
     *         dessin est impossible
     * @throws DejaExistantException Si un composant du meme nom existe déjà
     *         dans le dessin
     * @throws DessinGlobalException Si le dessin prend le meme nom que le
     *         dessin global
     */
    @Test
    public void voirSousDessin2Test() throws DeplacementImpossibleException,
            DejaExistantException, DessinGlobalException {
        Etat etat = new Etat();
        Dessin d = new Dessin("d");
        etat.getDessinCourant().ajoute(d);
        etat.voirSousDessin("d");
        Dessin ssD = new Dessin("ssD");
        etat.getDessinCourant().ajoute(ssD);
        etat.voirSousDessin("ssD");
        assertEquals(ssD, etat.getDessinCourant());
        ArrayList<Dessin> expected = new ArrayList<>();
        expected.add(d);
        assertEquals(expected, etat.getParenteDessin());
    }

    /**
     * Teste la méthode voir sousDessin sans sous dessin dans la dessin.
     * @throws DeplacementImpossibleException SI le déplacement dans le sous
     *         dessin est impossible
     */
    @Test(expected = DeplacementImpossibleException.class)
    public void voirSousDessinAucunTest()
            throws DeplacementImpossibleException {
        Etat etat = new Etat();
        etat.voirSousDessin("d");
    }

    /**
     * Teste la méthode retourSurDessin avec parent global.
     * @throws DeplacementImpossibleException SI le déplacement est impossible
     * @throws DejaExistantException Si un composant du meme nom existe déjà
     *         dans le dessin
     * @throws DessinGlobalException Si le dessin prend le meme nom que le
     *         dessin global
     */
    @Test
    public void retourSurDessinGlobalTest() throws DejaExistantException,
            DeplacementImpossibleException, DessinGlobalException {
        Etat etat = new Etat();
        Dessin d = new Dessin("d");
        etat.getDessinCourant().ajoute(d);
        etat.voirSousDessin("d");
        etat.retourSurDessin();
        assertEquals(Dessin.GLOBAL, etat.getDessinCourant().getNom());
        assertTrue(etat.getParenteDessin().isEmpty());
    }

    /**
     * Teste la méthode retourSurDessin avec parent dessin.
     * @throws DeplacementImpossibleException SI le déplacement est impossible
     * @throws DejaExistantException Si un composant du meme nom existe déjà
     *         dans le dessin
     * @throws DessinGlobalException Si le dessin prend le meme nom que le
     *         dessin global
     */
    @Test
    public void retourSurDessinTest() throws DejaExistantException,
            DessinGlobalException, DeplacementImpossibleException {
        Etat etat = new Etat();
        Dessin d = new Dessin("d");
        etat.getDessinCourant().ajoute(d);
        etat.voirSousDessin("d");
        Dessin ssD = new Dessin("ssD");
        etat.getDessinCourant().ajoute(ssD);
        etat.voirSousDessin("ssD");
        etat.retourSurDessin();
        assertEquals(d, etat.getDessinCourant());
        assertTrue(etat.getParenteDessin().isEmpty());
    }

    /**
     * Teste la méthode retourSurDessin sans dessin.
     * @throws DeplacementImpossibleException SI le déplacement est impossible
     */
    @Test(expected = DeplacementImpossibleException.class)
    public void retourSurDessinExceptionTest()
            throws DeplacementImpossibleException {
        Etat etat = new Etat();
        etat.retourSurDessin();
    }
}
