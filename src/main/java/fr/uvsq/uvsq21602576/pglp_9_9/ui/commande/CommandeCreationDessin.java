package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande.
 * Pour crée une dessin et l'enregistrer dans le dessin courant du moteurdessin.
 * @author Flora
 */
public class CommandeCreationDessin implements CommandeUndoable {
    /** Etat actuel du logiciel. */
    private Etat etat;
    /** Nom du dessin. */
    private String nom;
    /** Dessin créé. */
    private Dessin dessin;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur et les arguments de création du carre.
     * @param e Etat du logiciel
     * @param n Nom du dessin 
     */
    public CommandeCreationDessin(final Etat e, final String n) {
        this.etat = e;
        this.nom = n;
        dessin = null;
    }

    /**
     * Execute la commande.
     * Crée un dessin avec les arguments ennoncé, si cela est possible
     * et l'insert dans le dessin courant.
     * @throws CommandeImpossibleException Si les arguments ne sont pas du bon
     *         type, ou bon nombre. Ou si le dessin courant a déjà une forme du
     *         même nom.
     */
    @Override
    public void execute()
            throws CommandeImpossibleException, UndoImpossibleException {
        this.dessin = new Dessin(nom);
        try {
            this.etat.getDessinCourant().ajoute(this.dessin);
        } catch (DejaExistantException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Retire le carre précédemment créé du dessin.
     */
    @Override
    public void undo() throws UndoImpossibleException {
        this.etat.getDessinCourant().retire(dessin);
    }

}
