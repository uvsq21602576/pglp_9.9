package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;

/**
 * Classe de tests pour Dessin.
 * @author Flora
 */
public class DessinTest {

    /**
     * Teste la copie.
     * @throws DejaExistantException si un nom déjà existant est ajouté
     */
    @Test
    public void copieTest() throws DejaExistantException {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        d.ajoute(c1);

        Dessin copie = (Dessin) d.copie();
        assertFalse(d == copie);
        assertEquals(d, copie);
    }

    /**
     * Teste l'ajout.
     * @throws DejaExistantException
     * @throws DejaExistantException si un nom déjà existant est ajouté
     */
    @Test
    public void ajouteTest() throws DejaExistantException {
        Dessin d = new Dessin("d1");
        assertTrue(d.getComposantsFils().isEmpty());
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        d.ajoute(c1);
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(c1);
        assertEquals(expected,
                new ArrayList<ComposantDessin>(d.getComposantsFils()));
    }

    /**
     * Teste le lancement d'exception.
     * Quand deux objet de meme noms sont ajouté au meme dessin.
     * @throws DejaExistantException si un nom déjà existant est ajouté
     */
    @Test(expected = DejaExistantException.class)
    public void ajouteExceptionTest() throws DejaExistantException {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        Carre c = new Carre("c1", new Point(2, 3), 10);
        d.ajoute(c1);
        d.ajoute(c);
    }

    /**
     * Teste l'ajouteTout.
     * @throws DejaExistantException si un nom déjà existant est ajouté
     */
    @Test
    public void ajouteToutTest() throws DejaExistantException {
        Dessin d = new Dessin("d1");

        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        Carre c2 = new Carre("c2", new Point(9, 3), 10);
        ArrayList<ComposantDessin> actual = new ArrayList<>();
        actual.add(c1);
        actual.add(c2);
        d.ajouteTout(actual);

        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(c1);
        expected.add(c2);
        assertEquals(expected,
                new ArrayList<ComposantDessin>(d.getComposantsFils()));
    }

    /**
     * Teste l'ajouteTout.
     * En cas de deux noms identiques.
     * @throws DejaExistantException si un nom déjà existant est ajouté
     */
    @Test(expected = DejaExistantException.class)
    public void ajouteToutExceptionTest() throws DejaExistantException {
        Dessin d = new Dessin("d1");

        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        Carre c2 = new Carre("c1", new Point(9, 3), 10);
        ArrayList<ComposantDessin> actual = new ArrayList<>();
        actual.add(c1);
        actual.add(c2);
        d.ajouteTout(actual);
    }

    /**
     * Teste le retirement.
     * @throws DejaExistantException si deux objet aux noms identiques sont
     *         ajouté dans dessin
     */
    @Test
    public void retireTest() throws DejaExistantException {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        d.ajoute(c1);
        Cercle c2 = new Cercle("c2", new Point(2, 5), 23);
        d.ajoute(c2);
        d.retire(c1);
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(c2);
        assertEquals(expected,
                new ArrayList<ComposantDessin>(d.getComposantsFils()));
    }

    /**
     * Teste le deplacement.
     * @throws DejaExistantException
     * @throws DejaExistantException si deux objet aux noms identiques sont
     *         ajouté dans dessin
     */
    @Test
    public void deplaceTest() throws DejaExistantException {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        d.ajoute(c1);
        Cercle c2 = new Cercle("c2", new Point(2, 5), 23);
        d.ajoute(c2);
        d.deplace(new Point(1, -1));

        Dessin expected = new Dessin("d1");
        Cercle c1M = new Cercle("c1", new Point(3, 2), 10);
        expected.ajoute(c1M);
        Cercle c2M = new Cercle("c2", new Point(3, 4), 23);
        expected.ajoute(c2M);

        assertEquals(expected, d);
    }

}
