package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DessinGlobalException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Cercle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Teste la commande ajoute dessin.
 * @author Flora
 */
public class CommandeRetireDessinTest {

    /**
     * Teste l'execution, sans erreur.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     * @throws DessinGlobalException Si un dessin est nommé comme le global
     * @throws DejaExistantException Si un dessin du meme nom existe deja
     */
    @Test
    public void executeTest()
            throws DejaExistantException, DessinGlobalException,
            CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        Cercle c = new Cercle("c", new Point(1, 1), 10);
        Dessin d = new Dessin("D");
        d.ajoute(c);
        etat.getDessinCourant().ajoute(d);
        Commande com = new CommandeRetireDessin(etat, "c", "D");
        com.execute();
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(c);
        expected.add(d);
        assertEquals(expected,
                new ArrayList<>(etat.getDessinCourant().getComposantsFils()));
    }

    /**
     * Teste l'execution, sans composant à déplacer.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     * @throws DessinGlobalException Si un dessin est nommé comme le global
     * @throws DejaExistantException Si un dessin du meme nom existe deja
     */
    @Test(expected = CommandeImpossibleException.class)
    public void executeSansComposantTest()
            throws DejaExistantException, DessinGlobalException,
            CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        Dessin d = new Dessin("D");
        etat.getDessinCourant().ajoute(d);
        Commande com = new CommandeRetireDessin(etat, "c", "D");
        com.execute();
    }

    /**
     * Teste l'execution, sans dessin où deplacer.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     */
    @Test(expected = CommandeImpossibleException.class)
    public void executeSansDessinTest()
            throws CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        Commande com = new CommandeRetireDessin(etat, "c", "D");
        com.execute();
    }

    /**
     * Teste l'execution, avec dessin non dessin.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     * @throws DejaExistantException Si un composant du même nom existe déjà
     */
    @Test(expected = CommandeImpossibleException.class)
    public void executeAvecDessinNonDessinTest()
            throws CommandeImpossibleException, UndoImpossibleException,
            DejaExistantException {
        Etat etat = new Etat();
        Cercle c = new Cercle("c", new Point(1, 1), 10);
        etat.getDessinCourant().ajoute(c);
        Commande com = new CommandeRetireDessin(etat, "c", "c");
        com.execute();
    }

    /**
     * Teste l'execution, avec un composant ayant le même nom dans dessin.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     * @throws DessinGlobalException Si un dessin est nommé comme le global
     * @throws DejaExistantException Si un dessin du meme nom existe deja
     */
    @Test(expected = CommandeImpossibleException.class)
    public void executeDejaExistantTest()
            throws DejaExistantException, DessinGlobalException,
            CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        Cercle c = new Cercle("c", new Point(1, 1), 10);
        Dessin d = new Dessin("D");
        d.ajoute(c);
        etat.getDessinCourant().ajoute(d);
        etat.getDessinCourant().ajoute(new Cercle("c", new Point(2, 3), 13));
        Commande com = new CommandeRetireDessin(etat, "c", "D");
        com.execute();
    }

    /**
     * Teste l'annulation.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     * @throws DessinGlobalException Si un dessin est nommé comme le global
     * @throws DejaExistantException Si un dessin du meme nom existe deja
     */
    @Test
    public void undoTest() throws DejaExistantException, DessinGlobalException,
            CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        Cercle c = new Cercle("c", new Point(1, 1), 10);
        Dessin d = new Dessin("D");
        d.ajoute(c);
        etat.getDessinCourant().ajoute(d);
        CommandeUndoable com = new CommandeRetireDessin(etat, "c", "D");
        com.execute();
        com.undo();
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(d);
        assertEquals(expected,
                new ArrayList<>(etat.getDessinCourant().getComposantsFils()));
    }
}
