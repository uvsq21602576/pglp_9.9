package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.NoFilsException;

/**
 * Représente un Dessin.
 * Un dessin est un groupe de forme.
 * @author Flora
 */
public class Dessin implements ComposantDessin {
    /** Nom du dessin. */
    private String nom;
    /**
     * Liste de composant de dessin.
     * Tous les composants ont des noms différents.
     */
    private HashMap<String, ComposantDessin> composantsFils;

    /**
     * Constructeur.
     * Crée un dessin vide avec un nom.
     * @param n Nom du dessin
     */
    public Dessin(final String n) {
        this.nom = n;
        this.composantsFils = new HashMap<>();
    }

    /**
     * Renvoie la copie exacte du dessin.
     * @return Copie exacte du dessin
     */
    @Override
    public ComposantDessin copie() {
        Dessin copie = new Dessin(this.nom);
        for (ComposantDessin c : composantsFils.values()) {
            try {
                copie.ajoute(c.copie());
            } catch (DejaExistantException e) {
                System.err.println(e.getMessage());
            }
        }
        return copie;
    }

    /**
     * Ajoute un composant au dessin.
     * @param c Composant à ajouter
     * @throws DejaExistantException Si un composant du meme nom existe déjà
     *         dans le dessin
     */
    public void ajoute(final ComposantDessin c) throws DejaExistantException {
        if (composantsFils.containsKey(c.getNom())) {
            throw new DejaExistantException(c.getNom(), this.nom);
        }
        composantsFils.put(c.getNom(), c);
    }

    /**
     * Ajoute une liste de composants au dessin.
     * @param listeC Liste de composants à ajouter
     * @throws DejaExistantException Si un composant de la liste est déjà dans
     *         le dessin, ou si plusieurs composant avec un meme noms se trouve
     *         dans la liste.
     */
    public void ajouteTout(final List<ComposantDessin> listeC)
            throws DejaExistantException {
        ArrayList<String> listeNom = new ArrayList<>();
        for (ComposantDessin c : listeC) {
            if (composantsFils.containsKey(c.getNom())
                    || listeNom.contains(c.getNom())) {
                throw new DejaExistantException(c.getNom(), this.nom);
            }
            listeNom.add(c.getNom());
        }
        for (ComposantDessin c : listeC) {
            composantsFils.put(c.getNom(), c);
        }
    }

    /**
     * Retire un composant du dessin.
     * @param c Composant à retirer
     */
    public void retire(final ComposantDessin c) {
        this.composantsFils.remove(c.getNom(), c);
    }
    

    /**
     * Duplique un composant dans ce dessin.
     * @param aCopier Nom du composant à copier
     * @param copie Nom de la copie
     * @return Copie du composant
     * @throws DejaExistantException Si un composant du nom de la copie existe
     *         déjà
     * @throws NoFilsException Si aucun composant du nom de aCopier existe
     */
    public ComposantDessin copier(final String aCopier, final String copie)
            throws DejaExistantException, NoFilsException {
        if (composantsFils.containsKey(copie)) {
            throw new DejaExistantException(copie, this.nom);
        }
        if (!composantsFils.containsKey(aCopier)) {
            throw new NoFilsException(aCopier, this.nom);
        }
        ComposantDessin c = this.composantsFils.get(aCopier).copie();
        c.setNom(copie);
        this.ajoute(c);
        return c;
    }

    /**
     * Deplace un certain composant Fils.
     * @param n Nom du composant fils à déplacer
     * @param dP vecteur de déplacement
     * @throws NoFilsException Si aucun fils de ce nom existe
     */
    public void deplace(final String n, final Point dP) throws NoFilsException {
        if (!composantsFils.containsKey(n)) {
            throw new NoFilsException(n, this.nom);
        }
        this.composantsFils.get(n).deplace(dP);
    }

    /**
     * Deplace tous les composantFils.
     * @param dP vecteur de déplacement
     */
    @Override
    public void deplace(final Point dP) {
        for (ComposantDessin c : composantsFils.values()) {
            c.deplace(dP);
        }
    }

    /**
     * Retourne le haché correspondant.
     * @return Haché
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((composantsFils == null) ? 0 : composantsFils.hashCode());
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        return result;
    }

    /**
     * Teste si les deux Dessins sont égaux.
     * Deux Dessins sont égaux s'ils possèdent les mêmes composants fils et le
     * même nom.
     * @param obj Dessin à comparer
     * @return true si égaux, false sinon
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Dessin)) {
            return false;
        }
        Dessin other = (Dessin) obj;
        if (composantsFils == null) {
            if (other.composantsFils != null) {
                return false;
            }
        } else if (!composantsFils.equals(other.composantsFils)) {
            return false;
        }
        if (nom == null) {
            if (other.nom != null) {
                return false;
            }
        } else if (!nom.equals(other.nom)) {
            return false;
        }
        return true;
    }

    /**
     * Retourne le nom du dessin.
     * @return Nom du dessin
     */
    public String getNom() {
        return nom;
    }

    /**
     * Change le nom du dessin pour celui en paramètre.
     * @param n Nouveau du dessin
     */
    public void setNom(final String n) {
        this.nom = n;
    }

    /**
     * Retourne la collection de Composant, non modifiable.
     * @return collection non modifibale de composants
     */
    public Collection<ComposantDessin> getComposantsFils() {
        return Collections.unmodifiableCollection(this.composantsFils.values());
    }

    /**
     * Retourne une représentation textuelle du dessin.
     * C'est-à-dire son nom et son contenu.
     * @return Représentation textuelle du dessin
     */
    public String toString() {
        return this.toString(0);
    }

    /**
     * Retourne une représentation textuelle du dessin.
     * Avec des tabulations pour la profondeur.
     * C'est-à-dire son nom et son contenu.
     * @param profondeur Profondeur dans l'arbre de Composant
     * @return Représentation textuelle du dessin
     */
    @Override
    public String toString(final int profondeur) {
        String s = "";
        for (int i = 0; i < profondeur; i++) {
            s = s.concat("    ");
        }
        s = s.concat("|- " + this.nom + " :\n");
        for (ComposantDessin c : composantsFils.values()) {
            s = s.concat(c.toString(profondeur + 1));
            s = s.concat("\n");
        }
        return s;
    }

}
