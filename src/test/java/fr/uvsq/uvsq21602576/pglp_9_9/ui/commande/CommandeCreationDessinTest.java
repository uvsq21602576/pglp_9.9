package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Teste la commande de creation de dessin.
 * @author Flora
 */
public class CommandeCreationDessinTest {

    /**
     * Teste l'execution, sans erreur.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     */
    @Test
    public void executeTest()
            throws CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        Commande c = new CommandeCreationDessin(etat, "D");
        c.execute();
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(new Dessin("D"));
        assertEquals(expected,
                new ArrayList<>(etat.getDessinCourant().getComposantsFils()));
    }

    /**
     * Teste l'execution, avec un nom déjà dans le dessin.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     * @throws DejaExistantException En cas de composant déjà existant
     */
    @Test(expected = CommandeImpossibleException.class)
    public void executeMauvaisArgumentTest()
            throws CommandeImpossibleException, UndoImpossibleException, DejaExistantException {
        Etat etat = new Etat();
        etat.getDessinCourant().ajoute(new Dessin("D"));
        Commande c = new CommandeCreationDessin(etat, "D");
        c.execute();
    }

    /**
     * Teste l'annulation de la commande.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     */
    @Test
    public void undoTest()
            throws CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        CommandeUndoable c =
                new CommandeCreationDessin(etat, "D");
        c.execute();
        c.undo();
        assertTrue(etat.getDessinCourant().getComposantsFils().isEmpty());
    }
}
