package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Classe de tests pour Dessin.
 * @author Flora
 */
public class DessinTest {

    /**
     * Teste l'ajout.
     */
    @Test
    public void ajouteTest() {
        Dessin d = new Dessin("d1");
        assertTrue(d.getComposantsFils().isEmpty());
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        d.ajoute(c1);
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(c1);
        assertEquals(expected, d.getComposantsFils());
    }

    /**
     * Teste le retirement.
     */
    @Test
    public void retireTest() {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        d.ajoute(c1);
        Cercle c2 = new Cercle("c2", new Point(2, 5), 23);
        d.ajoute(c2);
        d.retire(c1);
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(c2);
        assertEquals(expected, d.getComposantsFils());
    }

    /**
     * Teste le deplacement.
     */
    @Test
    public void deplaceTest() {
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
