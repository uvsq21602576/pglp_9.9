package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Classe de tests de la class Point.
 * @author Flora
 */
public class PointTest {

    /**
     * Teste le déplacement.
     */
    @Test
    public void deplaceTest() {
        Point p = new Point(1, 3);
        p.deplace(new Point(3, -2));
        Point expected = new Point(4, 1);
        assertEquals(expected, p);
    }
}
