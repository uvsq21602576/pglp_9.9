package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Classe de tests pour Triangle.
 * @author Flora
 */
public class TriangleTest {

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
}
