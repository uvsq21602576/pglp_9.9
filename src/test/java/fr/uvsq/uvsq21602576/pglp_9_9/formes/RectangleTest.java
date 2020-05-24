package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Classe de Test pour Rectangle.
 * @author Flora
 */
public class RectangleTest {

    /**
     * Teste le getPointBasDroit.
     */
    @Test
    public void getPointBasDroit() {
        Point p = new Point(3, 4);
        Rectangle r = new Rectangle("r1", new Point(0, 0), p);
        Point bd = r.getPointBasDroit();
        assertFalse(p == bd);
        assertEquals(p, bd);
    }

    /**
     * Teste la copie.
     */
    @Test
    public void copieTest() {
        Rectangle r = new Rectangle("r1", new Point(0, 0), new Point(3, 4));
        Rectangle copie = (Rectangle) r.copie();
        assertFalse(r == copie);
        assertEquals(r, copie);
    }

    /**
     * Teste le déplacement.
     */
    @Test
    public void deplaceTest() {
        Rectangle r = new Rectangle("r1", new Point(0, 0), new Point(3, 4));
        r.deplace(new Point(1, -1));
        Rectangle expected =
                new Rectangle("r1", new Point(1, -1), new Point(4, 3));
        assertEquals(expected, r);
    }

    /**
     * Teste l'égalité correcte.
     */
    @Test
    public void equalsTest() {
        Rectangle r = new Rectangle("r1", new Point(0, 0), new Point(3, 4));
        Rectangle s = new Rectangle("r1", new Point(0, 0), new Point(3, 4));
        assertEquals(r, s);
    }

    /**
     * Teste l'égalité incorrecte, point haut-gauche.
     */
    @Test
    public void equalsHGNotTest() {
        Rectangle r = new Rectangle("r1", new Point(1, 0), new Point(3, 4));
        Rectangle s = new Rectangle("r1", new Point(0, 0), new Point(3, 4));
        assertNotEquals(r, s);
    }

    /**
     * Teste l'égalité incorrecte, point bas-droit.
     */
    @Test
    public void equalsBDNotTest() {
        Rectangle r = new Rectangle("r1", new Point(0, 0), new Point(3, 4));
        Rectangle s = new Rectangle("r1", new Point(0, 0), new Point(4, 4));
        assertNotEquals(r, s);
    }
}
