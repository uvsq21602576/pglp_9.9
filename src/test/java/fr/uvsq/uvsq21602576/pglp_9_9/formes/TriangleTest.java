package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Classe de tests pour Triangle.
 * @author Flora
 */
public class TriangleTest {

    /**
     * Teste le getPoint2.
     */
    @Test
    public void getPoint2Test() {
        Point p = new Point(0, 2);
        Triangle t = new Triangle("t1", new Point(0, 1), p, new Point(1, 0));
        Point p2 = t.getPoint2();
        assertFalse(p == p2);
        assertEquals(p, p2);
    }

    /**
     * Teste le getPoint3.
     */
    @Test
    public void getPoint3Test() {
        Point p = new Point(0, 2);
        Triangle t = new Triangle("t1", new Point(0, 1), new Point(1, 0), p);
        Point p3 = t.getPoint3();
        assertFalse(p == p3);
        assertEquals(p, p3);
    }

    /**
     * Teste la copie.
     */
    @Test
    public void copieTest() {
        Triangle t = new Triangle("t1", new Point(0, 1), new Point(0, 2),
                new Point(1, 0));
        Triangle copie = (Triangle) t.copie();
        assertFalse(t == copie);
        assertEquals(t, copie);
    }

    /**
     * Teste le déplacement.
     */
    @Test
    public void deplaceTest() {
        Triangle t = new Triangle("t1", new Point(0, 1), new Point(0, 2),
                new Point(1, 0));
        t.deplace(new Point(1, 1));
        Triangle expected = new Triangle("t1", new Point(1, 2), new Point(1, 3),
                new Point(2, 1));
        assertEquals(expected, t);
    }

    /**
     * Teste l'égalité correcte.
     */
    @Test
    public void equalsTest() {
        Triangle t = new Triangle("t1", new Point(0, 1), new Point(0, 2),
                new Point(1, 0));
        Triangle u = new Triangle("t1", new Point(0, 1), new Point(0, 2),
                new Point(1, 0));
        assertEquals(t, u);
    }

    /**
     * Teste l'égalité incorrecte par point 1.
     */
    @Test
    public void equalsP1NotTest() {
        Triangle t = new Triangle("t1", new Point(1, 1), new Point(0, 2),
                new Point(1, 0));
        Triangle u = new Triangle("t1", new Point(0, 1), new Point(0, 2),
                new Point(1, 0));
        assertNotEquals(t, u);
    }

    /**
     * Teste l'égalité incorrecte par point 2.
     */
    @Test
    public void equalsP2NotTest() {
        Triangle t = new Triangle("t1", new Point(0, 1), new Point(1, 2),
                new Point(1, 0));
        Triangle u = new Triangle("t1", new Point(0, 1), new Point(0, 2),
                new Point(1, 0));
        assertNotEquals(t, u);
    }

    /**
     * Teste l'égalité incorrecte par point 3.
     */
    @Test
    public void equalsP3NotTest() {
        Triangle t = new Triangle("t1", new Point(0, 1), new Point(0, 2),
                new Point(1, 1));
        Triangle u = new Triangle("t1", new Point(0, 1), new Point(0, 2),
                new Point(1, 0));
        assertNotEquals(t, u);
    }
}
