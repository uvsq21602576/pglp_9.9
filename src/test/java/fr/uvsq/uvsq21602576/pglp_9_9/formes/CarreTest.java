package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Classe de test pour Carré.
 * @author Flora
 */
public class CarreTest {

    /**
     * Teste la copie.
     */
    @Test
    public void copieTest() {
        Carre c = new Carre("c1", new Point(3, 4), 10);
        Carre copie = (Carre) c.copie();
        assertFalse(c == copie);
        assertEquals(c, copie);
    }

    /**
     * Teste le déplacement.
     */
    @Test
    public void deplaceTest() {
        Carre c = new Carre("c1", new Point(3, 4), 10);
        c.deplace(new Point(-1, -1));
        Carre expected = new Carre("c1", new Point(2, 3), 10);
        assertEquals(expected, c);
    }
}
