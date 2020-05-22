package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.NoFilsException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande pour déplacer un composant du dessin.
 * @author Flora
 */
public class CommandeDeplace implements CommandeUndoable {

    /** Etat du logiciel. */
    private Etat etat;
    /** Nom du composant à déplacer. */
    private String nom;
    /** Vecteur de déplacement. */
    private Point vecteur;

    /**
     * Constructeur.
     * Crée une commande avec l'etat du logiciel, le nom du composant à déplacer
     * eet le vecteur de déplacement.
     * @param e Etat du logiciel
     * @param n Nom du composant à déplacer
     * @param v Vecteur de déplacement
     */
    public CommandeDeplace(final Etat e, final String n, final Point v) {
        this.etat = e;
        this.nom = n;
        this.vecteur = v;
    }

    /**
     * Execute la commande.
     * Déplace le composant auquel correpsond le nom, situé dans le dessin
     * courant.
     * @throws CommandeImpossibleException Si aucun composant ne possède ce nom.
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        try {
            this.etat.getDessinCourant().deplace(nom, vecteur);
        } catch (NoFilsException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Déplace le même composant du vecteur opposé à celui utilisé.
     */
    @Override
    public void undo() throws UndoImpossibleException {
        try {
            this.etat.getDessinCourant().deplace(nom,
                    new Point(-vecteur.getX(), -vecteur.getY()));
        } catch (NoFilsException e) {
            throw new UndoImpossibleException(e.getMessage());
        }
    }

}
