package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DeplacementImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DessinGlobalException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Teste la commande de deplacement dans un sous-dessin.
 * @author Flora
 */
public class CommandeVoirDessinTest {

    /**
     * Teste l'execution, sans erreur.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     * @throws DessinGlobalException Si un dessin est nommé comme le global
     * @throws DejaExistantException Si un dessin du meme nom existe deja
     * @throws DeplacementImpossibleException Si un sous dessin est inexistant
     */
    @Test
    public void executeTest() throws CommandeImpossibleException,
            UndoImpossibleException, DessinGlobalException,
            DejaExistantException, DeplacementImpossibleException {
        Etat etat = new Etat();
        Dessin d = new Dessin("D");
        etat.getDessinCourant().ajoute(d);
        Commande c = new CommandeVoirDessin(etat, "D");
        c.execute();
        assertEquals(d, etat.getDessinCourant());
        assertTrue(etat.getParenteDessin().isEmpty());
    }

    /**
     * Teste l'annulation de la commande.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     * @throws DessinGlobalException Si un dessin est nommé comme le global
     * @throws DejaExistantException Si un dessin du meme nom existe deja
     * @throws DeplacementImpossibleException Si un sous dessin est inexistant
     */
    @Test
    public void undoTest() throws CommandeImpossibleException,
            UndoImpossibleException, DessinGlobalException,
            DeplacementImpossibleException, DejaExistantException {
        Etat etat = new Etat();
        Dessin d = new Dessin("D");
        etat.getDessinCourant().ajoute(d);
        CommandeUndoable c = new CommandeVoirDessin(etat, "D");
        c.execute();
        c.undo();
        assertEquals(Dessin.GLOBAL, etat.getDessinCourant().getNom());
        assertTrue(etat.getParenteDessin().isEmpty());
    }
}
