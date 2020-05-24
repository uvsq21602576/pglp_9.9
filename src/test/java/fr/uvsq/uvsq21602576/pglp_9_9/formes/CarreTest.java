package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

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

    /**
     * Teste l'égalité correcte.
     */
    @Test
    public void equalsTest() {
        Carre c = new Carre("c1", new Point(1, 2), 10);
        Carre d = new Carre("c1", new Point(1, 2), 10);
        assertEquals(c, d);
    }

    /**
     * Teste l'égalité incorrecte.
     */
    @Test
    public void equalsNotTest() {
        Carre c = new Carre("c1", new Point(1, 2), 10);
        Carre d = new Carre("c1", new Point(1, 2), 20);
        assertNotEquals(c, d);
    }

    /**
     * Teste l'égalité incorrecte avec Rectangle.
     */
    @Test
    public void equalsRectangleTest() {
        Carre c = new Carre("c1", new Point(1, 2), 10);
        Rectangle r = new Rectangle("c1", new Point(1, 2), new Point(11, 12));
        assertNotEquals(c, r);
    }
}
