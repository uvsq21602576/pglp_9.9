package fr.uvsq.uvsq21602576.pglp_9_9;

/**
 * Représente l'arret du programme.
 * Stocke un booléen,
 * Si le programme doit s'arréter, il est égal à True.
 * Sinon égal à false.
 * @author Flora
 */
public class Arret {
    /**
     * Est-ce que le programme doit s'arreter ?
     */
    private boolean arret;

    /**
     * Constructeur.
     * Crée un arret à false.
     */
    public Arret() {
        arret = false;
    }

    /**
     * Mets l'arret à true.
     */
    public void setArret() {
        arret = true;
    }

    /**
     * Teste si l'arret est à true.
     * @return true si le programme doit s'arreter,
     *         false sinon
     */
    public boolean isArret() {
        return arret;
    }
}
