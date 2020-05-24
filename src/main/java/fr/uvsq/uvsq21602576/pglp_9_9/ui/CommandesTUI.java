package fr.uvsq.uvsq21602576.pglp_9_9.ui;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.Commande;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeAfficheDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeAfficheTout;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeAjouteDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCharger;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCopie;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCreationCarre;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCreationCercle;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCreationDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCreationRectangle;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCreationTriangle;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeDeplace;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeExit;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeRetireDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeSauvegarde;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeSortirDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeSupprime;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeSupprimeSauvegarde;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeUndo;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeVoirDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.MauvaiseUtilisationException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.NoCommandException;

/**
 * Enumération de commandes.
 * Permet la liaison entre la commande tapée et l'interface commande.
 * @author Flora
 */
public enum CommandesTUI {
    /** Commande pour annuler la précédente saisie. */
    UNDO("annule", 0) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Arguments non admis.");
            }
            return new CommandeUndo(etat.getHistorique());
        }
    },
    /** Commande pour quitter. */
    EXIT("quitter", 0) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Arguments non admis.");
            }
            return new CommandeExit(ui);
        }
    },
    /** Commande pour créer un carré. */
    CREATION_CARRE("carre", 2) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable == null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin d'une variable.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof Point)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le premier argument doit être un point.");
            }
            Point hg = (Point) arguments[iArg];
            iArg++;
            if (!(arguments[iArg] instanceof Integer)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le deuxième argument doit être un entier.");
            }
            int longueur = (int) arguments[iArg];
            return new CommandeCreationCarre(etat, variable, hg, longueur);
        }
    },
    /** Commande pour créer un cercle. */
    CREATION_CERCLE("cercle", 2) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable == null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin d'une variable.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof Point)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le premier argument doit être un point.");
            }
            Point centre = (Point) arguments[iArg];
            iArg++;
            if (!(arguments[iArg] instanceof Integer)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le deuxième argument doit être un entier.");
            }
            int rayon = (int) arguments[iArg];
            return new CommandeCreationCercle(etat, variable, centre, rayon);
        }
    },
    /** Commande pour créer un rectangle. */
    CREATION_RECTANGLE("rectangle", 2) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable == null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin d'une variable.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof Point)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le premier argument doit être un point.");
            }
            Point hg = (Point) arguments[iArg];
            iArg++;
            if (!(arguments[iArg] instanceof Point)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le deuxième argument doit être un point.");
            }
            Point bd = (Point) arguments[iArg];
            return new CommandeCreationRectangle(etat, variable, hg, bd);
        }
    },
    /** Commande pour créer un triangle. */
    CREATION_TRIANGLE("triangle", 3) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable == null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin d'une variable.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof Point)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le premier argument doit être un point.");
            }
            Point p1 = (Point) arguments[iArg];
            iArg++;
            if (!(arguments[iArg] instanceof Point)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le deuxième argument doit être un point.");
            }
            Point p2 = (Point) arguments[iArg];
            iArg++;
            if (!(arguments[iArg] instanceof Point)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le troisième argument doit être un point.");
            }
            Point p3 = (Point) arguments[iArg];
            return new CommandeCreationTriangle(etat, variable, p1, p2, p3);
        }
    },
    /** Commande pour créer un dessin. */
    CREATION_DESSIN("dessin", 0) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable == null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin d'une variable.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Aucun argument admis.");
            }
            return new CommandeCreationDessin(etat, variable);
        }
    },
    /** Commande pour copier une variable. */
    COPIE("copie", 1) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable == null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin d'une variable.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof String)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "L'argument doit être un nom de variable.");
            }
            String nom = (String) arguments[iArg];
            return new CommandeCopie(etat, variable, nom);
        }
    },
    /** Commande pour déplacer une variable. */
    DEPLACE("deplace", 2) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof String)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le premier argument doit être un nom de variable.");
            }
            String nom = (String) arguments[iArg];
            iArg++;
            if (!(arguments[iArg] instanceof Point)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le deuxième argument doit être un vecteur Point.");
            }
            Point v = (Point) arguments[iArg];
            return new CommandeDeplace(etat, nom, v);
        }
    },
    /** Commande pour affficher le dessin courant. */
    AFFICHE("affiche", 0) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Arguments non admis.");
            }
            return new CommandeAfficheDessin(ui);
        }
    },
    /** Commande pour afficher tous les dessins. */
    AFFICHE_TOUT("afficheTout", 0) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Arguments non admis.");
            }
            return new CommandeAfficheTout(ui);
        }
    },
    /** Commande pour ajouter une variable dans un dessin. */
    AJOUTE("ajoute", 2) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof String)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le premier argument doit être un nom de variable.");
            }
            String nom = (String) arguments[iArg];
            iArg++;
            if (!(arguments[iArg] instanceof String)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le premier argument doit être un nom de variable.");
            }
            String nomDessin = (String) arguments[iArg];
            return new CommandeAjouteDessin(etat, nom, nomDessin);
        }
    },
    /** Commande pour retirer une variable d'un dessin. */
    RETIRE("retire", 2) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof String)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le premier argument doit être un nom de variable.");
            }
            String nom = (String) arguments[iArg];
            iArg++;
            if (!(arguments[iArg] instanceof String)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Le premier argument doit être un nom de variable.");
            }
            String nomDessin = (String) arguments[iArg];
            return new CommandeRetireDessin(etat, nom, nomDessin);
        }
    },
    /** Commande pour supprimer une variable. */
    SUPPRIME("supprime", 1) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof String)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "L'argument doit être un nom de variable.");
            }
            String nom = (String) arguments[iArg];
            return new CommandeSupprime(etat, nom);
        }
    },
    /** Commande pour voir un autre dessin. */
    VOIR("voir", 1) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof String)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "L'argument doit être un nom de variable.");
            }
            String nom = (String) arguments[iArg];
            return new CommandeVoirDessin(etat, nom);
        }
    },
    /** Commande pour sortir du dessin courant. */
    SORTIR("sortir", 0) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Arguments non admis.");
            }
            return new CommandeSortirDessin(etat);
        }
    },
    /** Commande pour sauvegarder un dessin. */
    SAUVEGARDE("sauvegarde", 0) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Arguments non admis.");
            }
            return new CommandeSauvegarde(etat, ui);
        }
    },
    /** Commande pour charger un dessin sauvegardé. */
    CHARGE("charge", 1) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof String)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "L'argument doit être un nom de dessin.");
            }
            String nom = (String) arguments[iArg];
            return new CommandeCharger(etat, nom);
        }
    },
    /** Commande pour supprimer une sauvegarde. */
    SUPPR_SAUVEGARDE("supprimeSauvegarde", 1) {
        @Override
        public Commande getCommande(final String variable,
                final boolean parentheses, final Object[] arguments,
                final Etat etat, final DrawingUI ui)
                throws MauvaiseUtilisationException {
            if (variable != null) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Variable non admise.");
            }
            if (!parentheses) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Besoin de parenthèses.");
            }
            if (arguments.length != super.nbArguments) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "Nombre d'arguments doit être " + super.nbArguments
                                + ".");
            }
            int iArg = 0;
            if (!(arguments[iArg] instanceof String)) {
                throw new MauvaiseUtilisationException(super.nomFonction,
                        "L'argument doit être un nom de dessin.");
            }
            String nom = (String) arguments[iArg];
            return new CommandeSupprimeSauvegarde(nom);
        }
    };

    /** Nom de la fontion correspondant à la commande. */
    private final String nomFonction;
    /** Nombre d'arguments nécéssaires à la commande. */
    private final int nbArguments;

    /**
     * COnstructeur.
     * Crée une CommandesTUI avec le nom de sa fonction correspondante et le
     * nombre d'arguments necessaires.
     * @param nom Nom de la fontion.
     * @param nb Nombre d'argument nécessaire pour la commande
     */
    CommandesTUI(final String nom, final int nb) {
        this.nomFonction = nom;
        this.nbArguments = nb;
    }

    /**
     * Retorune la commande associée avec le nom de la fonction écrit.
     * @param nom Nom de la fonction
     * @return CommandesTUI
     * @throws NoCommandException Si le nom de la fonction écrit ne correspond à
     *         aucune commande.
     */
    public static CommandesTUI getCommandesTUI(final String nom)
            throws NoCommandException {
        for (CommandesTUI c : CommandesTUI.values()) {
            if (c.nomFonction.equals(nom)) {
                return c;
            }
        }
        throw new NoCommandException(nom);
    }

    /**
     * Retorune la commande associée avec les arguments décrits.
     * Si les arguments ne sont pas correcte par rapport à la commande, renvoie
     * une exception.
     * @param variable Nom de la variable indiquée, peut être nulle
     * @param parentheses Présence de parenthèse
     * @param arguments Arguments entre parenthèse, peut être vide
     * @param etat Etat du logiciel
     * @param ui InterfaceUtilisateur utilisée
     * @return Commande associée
     * @throws MauvaiseUtilisationException Si la commande est mal utilisée
     */
    public abstract Commande getCommande(String variable, boolean parentheses,
            Object[] arguments, Etat etat, DrawingUI ui)
            throws MauvaiseUtilisationException;
}
