package fr.uvsq.uvsq21602576.pglp_9_9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeUndoable;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Représente un historique.
 * Contient une liste de commande.
 * Peut effectuer un retour en arrière.
 * @author Flora
 */
public class Historique {
    /**
     * Liste des commandes.
     * Ne contient que les commandes pouvant être annulées.
     */
    private ArrayList<CommandeUndoable> historique;

    /**
     * Constructeur.
     * Crée un historique vide.
     */
    public Historique() {
        historique = new ArrayList<CommandeUndoable>();
    }

    /**
     * Ajoute une commande à l'historique.
     * @param c Commande à ajouter.
     */
    public void ajoute(final CommandeUndoable c) {
        historique.add(c);
    }

    /**
     * Effectue un retour en arrière.
     * Annule la dernière commande ajouté.
     * @throws UndoImpossibleException Si l'annulation est impossible.
     */
    public void retourArriere() throws UndoImpossibleException {
        if (historique.isEmpty()) {
            throw new UndoImpossibleException("L'historique est vide.");
        }
        CommandeUndoable c = historique.remove(historique.size() - 1);
        try {
            c.undo();
        } catch (UndoImpossibleException e) {
            historique.add(c);
            throw e;
        }
    }

    /**
     * Retourne l'historique.
     * Sous forme d'une liste de commandes non modifiable.
     * @return liste de commandes non modifiable
     */
    public List<CommandeUndoable> getHistorique() {
        return Collections.unmodifiableList(historique);
    }

}
