package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DessinGlobalException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.NoFilsException;

/**
 * Classe de tests pour Dessin.
 * @author Flora
 */
public class DessinTest {

    /**
     * Teste le lancement d'une exception à la construction.
     * En cas de creation de dessin avec le meme nom que le global.
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     */
    @Test(expected = DessinGlobalException.class)
    public void constructeurExceptionTest() throws DessinGlobalException {
        Dessin d = new Dessin(Dessin.GLOBAL);
    }

    /**
     * Teste la copie.
     * @throws DejaExistantException si un nom déjà existant est ajouté
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     */
    @Test
    public void copieTest()
            throws DejaExistantException, DessinGlobalException {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        d.ajoute(c1);

        Dessin copie = (Dessin) d.copie();
        assertFalse(d == copie);
        assertEquals(d, copie);
    }

    /**
     * Teste l'ajout.
     * @throws DejaExistantException si un nom déjà existant est ajouté
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     */
    @Test
    public void ajouteTest()
            throws DejaExistantException, DessinGlobalException {
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
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     */
    @Test(expected = DejaExistantException.class)
    public void ajouteExceptionTest()
            throws DejaExistantException, DessinGlobalException {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        Carre c = new Carre("c1", new Point(2, 3), 10);
        d.ajoute(c1);
        d.ajoute(c);
    }

    /**
     * Teste l'ajouteTout.
     * @throws DejaExistantException si un nom déjà existant est ajouté
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     */
    @Test
    public void ajouteToutTest()
            throws DejaExistantException, DessinGlobalException {
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
     * En cas de deux noms identiques dans la liste à ajouter.
     * @throws DejaExistantException Si un nom déjà existant est ajouté
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     */
    @Test(expected = DejaExistantException.class)
    public void ajouteToutExceptionTest()
            throws DejaExistantException, DessinGlobalException {
        Dessin d = new Dessin("d1");

        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        Carre c2 = new Carre("c1", new Point(9, 3), 10);
        ArrayList<ComposantDessin> actual = new ArrayList<>();
        actual.add(c1);
        actual.add(c2);
        d.ajouteTout(actual);
        assertTrue(d.getComposantsFils().isEmpty());
    }
    
    /**
     * Teste l'ajouteTout.
     * En cas de deux noms identiques entre déjà dans le dessin et dehors.
     * @throws DejaExistantException Si un nom déjà existant est ajouté
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     */
    @Test(expected = DejaExistantException.class)
    public void ajouteToutException2Test()
            throws DejaExistantException, DessinGlobalException {
        Dessin d = new Dessin("d1");
        Rectangle r = new Rectangle("c1", new Point(2, 4), new Point(5, 9));
        d.ajoute(r);

        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        Carre c2 = new Carre("c2", new Point(9, 3), 10);
        ArrayList<ComposantDessin> actual = new ArrayList<>();
        actual.add(c1);
        actual.add(c2);
        d.ajouteTout(actual);
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(r);
        assertEquals(expected,  d.getComposantsFils());
    }

    /**
     * Teste le retirement avec composant.
     * @throws DejaExistantException si deux objet aux noms identiques sont
     *         ajouté dans dessin
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     */
    @Test
    public void retireTest()
            throws DejaExistantException, DessinGlobalException {
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
     * Teste le retirement avec nom de composant.
     * @throws DejaExistantException si deux objet aux noms identiques sont
     *         ajouté dans dessin
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     */
    @Test
    public void retireNomTest()
            throws DejaExistantException, DessinGlobalException {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        d.ajoute(c1);
        Cercle c2 = new Cercle("c2", new Point(2, 5), 23);
        d.ajoute(c2);
        d.retire("c1");
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(c2);
        assertEquals(expected,
                new ArrayList<ComposantDessin>(d.getComposantsFils()));
    }
    
    /**
     * Teste le getComposant.
     * @throws DejaExistantException si deux objet aux noms identiques sont
     *         ajouté dans dessin
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     * @throws NoFilsException Si aucun composant n'est trouvé
     */
    @Test
    public void getComposantTest()
            throws DejaExistantException, DessinGlobalException, NoFilsException {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        d.ajoute(c1);
        Cercle c2 = new Cercle("c2", new Point(2, 5), 23);
        d.ajoute(c2);
        ComposantDessin actual = d.getComposant("c1");
        assertEquals(c1, actual);
    }
    
    /**
     * Teste le getComposant, sans le composant.
     * @throws DejaExistantException si deux objet aux noms identiques sont
     *         ajouté dans dessin
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     * @throws NoFilsException Si aucun composant n'est trouvé
     */
    @Test(expected = NoFilsException.class)
    public void getComposantExceptionTest()
            throws DejaExistantException, DessinGlobalException, NoFilsException {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        d.ajoute(c1);
        Cercle c2 = new Cercle("c2", new Point(2, 5), 23);
        d.ajoute(c2);
        ComposantDessin actual = d.getComposant("c3");
    }

    /**
     * Teste le deplacement.
     * @throws DejaExistantException si deux objet aux noms identiques sont
     *         ajouté dans dessin
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     */
    @Test
    public void deplaceTest()
            throws DejaExistantException, DessinGlobalException {
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
    
    /**
     * Teste la duplication.
     * Sans problème.
     * @throws DejaExistantException si deux objet aux noms identiques sont
     *         ajouté dans dessin
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     * @throws NoFilsException Si aucun fils ne correspond au composant demandé
     */
    @Test
    public void copierTest() throws DessinGlobalException, DejaExistantException, NoFilsException {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        d.ajoute(c1);
        d.copier("c1", "c1bis");
        
        Dessin expected = new Dessin("d1");
        Cercle c1M = new Cercle("c1", new Point(2, 3), 10);
        expected.ajoute(c1M);
        Cercle c2M = new Cercle("c1bis", new Point(2, 3), 10);
        expected.ajoute(c2M);

        assertEquals(expected, d);
    }
    
    /**
     * Teste la duplication.
     * Snas fils à copier.
     * @throws DejaExistantException si deux objet aux noms identiques sont
     *         ajouté dans dessin
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     * @throws NoFilsException Si aucun fils ne correspond au composant demandé
     */
    @Test(expected = NoFilsException.class)
    public void copierNoFilsTest() throws DessinGlobalException, DejaExistantException, NoFilsException {
        Dessin d = new Dessin("d1");
        d.copier("c1", "c1bis");
    }
    
    /**
     * Teste la duplication.
     * Avec un composant de meme nom déjà présent.
     * @throws DejaExistantException si deux objet aux noms identiques sont
     *         ajouté dans dessin
     * @throws DessinGlobalException Si un dessin a le même nom que le global
     * @throws NoFilsException Si aucun fils ne correspond au composant demandé
     */
    @Test(expected = DejaExistantException.class)
    public void copierMemeNomTest() throws DessinGlobalException, DejaExistantException, NoFilsException {
        Dessin d = new Dessin("d1");
        Cercle c1 = new Cercle("c1", new Point(2, 3), 10);
        Cercle c1bis = new Cercle("c1bis", new Point(2, 3), 10);
        d.ajoute(c1);
        d.ajoute(c1bis);
        d.copier("c1", "c1bis");
    }

}
