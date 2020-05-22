package fr.uvsq.uvsq21602576.pglp_9_9.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.ConnectionException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.CreationObjetException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.CreationTableException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DeletionException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DoublonException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.InexistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.InsertionException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.JDBCException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.LectureException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.MetaDataException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.MisAJourException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.ModificationException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.RechercheException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.RemplissageStatementException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.SuppressionException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Cercle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;

/**
 * DAO JDBC.
 * Pour Cercle.
 * @author Flora
 */
public class CercleDAOJDBC extends DAO<Cercle> {

    /**
     * URL pour la base de donnée.
     */
    private static final String DB_URL =
            "jdbc:derby:donneesPourDB\\jdbcDB;create=true";

    /**
     * Retourne la connexion à la base de donnée.
     * @return Connexion à la base de donnée
     * @throws ConnectionException En cas d'erreur à la connexion
     */
    private Connection getConnection() throws ConnectionException {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    /**
     * Teste si la table name existe.
     * Dans la base de donnée connectée à conn.
     * @param name Nom de la table
     * @param conn Connexion avec la base de donnée.
     * @return true si elle existe, false sinon
     * @throws MetaDataException En cas d'erreur lors de la lecture des méta
     *         données de la base de donnée.
     */
    private static boolean tableExists(final String name, final Connection conn)
            throws MetaDataException {
        DatabaseMetaData dbmd;
        try {
            dbmd = conn.getMetaData();
            try (ResultSet rs =
                    dbmd.getTables(null, null, name.toUpperCase(), null)) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new MetaDataException(e.getMessage());
        }
        return false;
    }

    /**
     * Crée la tables utile pour Cercle.
     * C'est à dire : Cercle.
     * @param conn Connexion à la base de donnée
     * @throws CreationTableException En cas d'erreur lors de la creation des
     *         tables.
     */
    static void createTable(final Connection conn)
            throws CreationTableException {
        try {
            DessinDAOJDBC.createTable(conn);
            if (!tableExists("cercle", conn)) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("Create table cercle ("
                            + "nom varchar(30) not null, " + "cX int not null, "
                            + "cY int not null," + "rayon int not null, "
                            + "dessinID int not null,"
                            + "primary key (nom, dessinID), "
                            + "foreign key (dessinID) "
                            + "references dessin(dessinID))");
                }
            }
        } catch (MetaDataException | SQLException e) {
            throw new CreationTableException("cercle", e.getMessage());
        }
    }

    /**
     * Remplit les différentes valeurs du statement préparé.
     * Avec les objects values mis en paramêtre.
     * @param statement PreparedStatement à remplir
     * @param values Valeurs du preparedStatement
     * @throws RemplissageStatementException En cas d'erreur lors du
     *         remplissage.
     */
    public static void remplitStatement(final PreparedStatement statement,
            final Object... values) throws RemplissageStatementException {
        try {
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
        } catch (SQLException e) {
            throw new RemplissageStatementException(e.getMessage());
        }
    }

    /**
     * Insert l'objet cercle dans la base de donnée.
     * Via la connexion conn.
     * @param obj Cercle à insérer
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connexion à la base de donnée
     * @throws InsertionException En cas d'erreur lors de l'insertion de données
     * @throws CreationTableException En cas d'erreur lors de la creation des
     *         tables
     * @throws DoublonException En cas de duplication de clé, si l'objet obj
     *         existe déjà
     */
    static void insert(final Cercle obj, final int dessinID,
            final Connection conn) throws InsertionException,
            CreationTableException, DoublonException {
        try {
            createTable(conn);
            try (PreparedStatement insertCercle = conn.prepareStatement(
                    "INSERT INTO cercle values (?, ?, ?, ?, ?)")) {
                remplitStatement(insertCercle, obj.getNom(),
                        obj.getPointReference().getX(),
                        obj.getPointReference().getY(), obj.getRayon(),
                        dessinID);
                insertCercle.execute();
            }
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            throw new DoublonException(obj.getNom());
        } catch (SQLException | RemplissageStatementException e) {
            throw new InsertionException(obj.getNom(), e.getMessage());
        }
    }

    /**
     * Créer le cercle obj dans la base de donnée.
     * @param obj Cercle à créer
     * @return le cercle créer
     * @throws CreationObjetException En cas d'erreur à la création de l'objet.
     * @throws DoublonException En cas de duplication de clé, si l'objet obj
     *         existe déjà
     */
    @Override
    public Cercle create(final Cercle obj)
            throws CreationObjetException, DoublonException {
        Cercle result = null;
        try (Connection conn = getConnection()) {
            try {
                conn.setAutoCommit(false);
                insert(obj, -1, conn);
                conn.commit();
                result = obj;
            } catch (SQLException | JDBCException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (DoublonException e) {
            throw e;
        } catch (JDBCException | SQLException e) {
            throw new CreationObjetException(obj.getNom(), e.getMessage());
        }
        return result;
    }

    /**
     * Lit tous les cercles dans la base de donnée.
     * Cercle apparetenant au dessin dessinID
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connection à la base de donnée
     * @return Liste de Cercles lus
     * @throws LectureException En cas d'erreur lors de la lecture
     */
    static List<ComposantDessin> readAll(final int dessinID,
            final Connection conn) throws LectureException {
        ArrayList<ComposantDessin> listeCercle = new ArrayList<>();
        try {
            if (!tableExists("cercle", conn)) {
                return listeCercle;
            }
        } catch (MetaDataException e) {
        }
        try (PreparedStatement selectCercle = conn.prepareStatement(
                "SELECT * FROM cercle " + "WHERE dessinID = ?")) {
            remplitStatement(selectCercle, dessinID);
            try (ResultSet rs = selectCercle.executeQuery()) {
                while (rs.next()) {
                    listeCercle.add(new Cercle(rs.getString("nom"),
                            new Point(rs.getInt("cX"), rs.getInt("cY")),
                            rs.getInt("rayon")));
                }
            }
        } catch (SQLException | RemplissageStatementException e) {
            throw new LectureException("cercles", e.getMessage());
        }
        return listeCercle;
    }

    /**
     * Lit un cercle dans la base de donnée.
     * Cercle accessible par son nom id et l'id de son dessin.
     * @param id Nom du cercle à lire
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connection à la base de donnée
     * @return Cercle lu
     * @throws LectureException En cas d'erreur lors de la lecture
     * @throws InexistantException Si le cercle id n'existe pas
     */
    static Cercle read(final String id, final int dessinID,
            final Connection conn)
            throws LectureException, InexistantException {
        try {
            if (!tableExists("cercle", conn)) {
                throw new InexistantException(id);
            }
        } catch (MetaDataException e) {
        }
        try (PreparedStatement selectCercle = conn.prepareStatement(
                "SELECT * FROM cercle " + "WHERE nom = ? AND dessinID = ?")) {
            remplitStatement(selectCercle, id, dessinID);
            try (ResultSet rs = selectCercle.executeQuery()) {
                if (rs.next()) {
                    Cercle c = new Cercle(rs.getString("nom"),
                            new Point(rs.getInt("cX"), rs.getInt("cY")),
                            rs.getInt("rayon"));
                    return c;
                } else {
                    throw new InexistantException(id);
                }
            }
        } catch (SQLException | RemplissageStatementException e) {
            throw new LectureException(id, e.getMessage());
        }
    }

    /**
     * Recherche un cercle dans la base de donnée.
     * Cercle accessible par son nom id.
     * @param id Nom du cercle recherché
     * @return Cercle lu, null si erreur
     * @throws RechercheException En cas d'erreur lors de la recherche
     * @throws InexistantException Si le cercle id n'existe pas
     */
    @Override
    public Cercle find(final String id)
            throws InexistantException, RechercheException {
        Cercle result = null;
        try (Connection conn = getConnection()) {
            try {
                conn.setAutoCommit(false);
                result = read(id, -1, conn);
                conn.commit();
            } catch (JDBCException | SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (InexistantException e) {
            throw e;
        } catch (JDBCException | SQLException e) {
            throw new RechercheException(id, e.getMessage());
        }
        return result;
    }

    /**
     * Modifie le cercle dans la base de donnée.
     * Lui assigne ces nouveau paramêtre.
     * Accessible via son nom et l'id de son dessin.
     * @param obj Cercle à modifié
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connection à la base de donnée
     * @throws InexistantException Si le cercle obj n'existe pas dans la base de
     *         donnée
     * @throws ModificationException En cas d'erreur pendant la modification.
     */
    static void modify(final Cercle obj, final int dessinID,
            final Connection conn)
            throws InexistantException, ModificationException {
        try {
            if (!tableExists("cercle", conn)) {
                throw new InexistantException(obj.getNom());
            }
        } catch (MetaDataException e) {
        }
        try {
            try (PreparedStatement selectCercle =
                    conn.prepareStatement("SELECT * FROM cercle "
                            + "WHERE nom = ? AND dessinID = ?")) {
                remplitStatement(selectCercle, obj.getNom(), dessinID);
                try (ResultSet rs = selectCercle.executeQuery()) {
                    if (!rs.next()) {
                        throw new InexistantException(obj.getNom());
                    }
                }
            }
            try (PreparedStatement updateCercle = conn.prepareStatement(
                    "UPDATE cercle SET cX = ?, cY = ?, rayon = ? "
                            + "WHERE nom = ? AND dessinID = ?")) {
                remplitStatement(updateCercle, obj.getPointReference().getX(),
                        obj.getPointReference().getY(), obj.getRayon(),
                        obj.getNom(), dessinID);
                updateCercle.execute();
            }
        } catch (SQLException | RemplissageStatementException e) {
            throw new ModificationException(obj.getNom(), e.getMessage());
        }
    }

    /**
     * Met à jour le cercle dans la base de donnée.
     * Lui assigne ces nouveaux paramètres.
     * Accessible via son nom.
     * @param obj Cercle à mettre à jour
     * @throws InexistantException Si le cercle obj n'existe pas dans la base de
     *         donnée
     * @throws MisAJourException En cas d'erreur pendant la mise à jour.
     */
    @Override
    public Cercle update(final Cercle obj)
            throws InexistantException, MisAJourException {
        Cercle result = null;
        try (Connection conn = getConnection()) {
            try {
                conn.setAutoCommit(false);
                modify(obj, -1, conn);
                conn.commit();
                result = obj;
            } catch (JDBCException | SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (InexistantException e) {
            throw e;
        } catch (JDBCException | SQLException e) {
            throw new MisAJourException(obj.getNom(), e.getMessage());
        }
        return result;
    }

    /**
     * Supprime les cercles appartenant à dessinID de la base de données.
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppressAll(final int dessinID, final Connection conn)
            throws SuppressionException {
        try {
            if (tableExists("cercle", conn)) {
                try (PreparedStatement deleteCercle = conn.prepareStatement(
                        "DELETE FROM cercle " + "WHERE dessinID = ?")) {
                    remplitStatement(deleteCercle, dessinID);
                    deleteCercle.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException("cercles", e.getMessage());
        }
    }

    /**
     * Supprime les cercles appartenant à dessinID de la base de données.
     * Dont les noms ne sont pas dans noms.
     * @param noms Liste de noms de cercle non à supprimer
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppressAllBut(final Object[] noms, final int dessinID,
            final Connection conn) throws SuppressionException {
        try {
            if (tableExists("cercle", conn)) {
                String sql = "DELETE FROM cercle " + "WHERE dessinID = ?";
                for (int i = 0; i < noms.length; i++) {
                    sql = sql.concat(" AND nom <> ?");
                }
                try (PreparedStatement deleteCercle =
                        conn.prepareStatement(sql)) {
                    Object[] att = new Object[noms.length + 1];
                    att[0] = dessinID;
                    for (int i = 0; i < noms.length; i++) {
                        att[i + 1] = noms[i];
                    }
                    remplitStatement(deleteCercle, att);
                    deleteCercle.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException("cercles", e.getMessage());
        }
    }

    /**
     * Supprime le cercle obj de la base de donnée.
     * Accessible par son nom et l'id de son dessin.
     * @param id Nom de l'objet à supprimer
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppress(final String id, final int dessinID,
            final Connection conn) throws SuppressionException {
        try {
            if (tableExists("cercle", conn)) {
                try (PreparedStatement deleteCercle =
                        conn.prepareStatement("DELETE FROM cercle "
                                + "WHERE nom = ? AND dessinID = ?")) {
                    remplitStatement(deleteCercle, id, dessinID);
                    deleteCercle.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException(id, e.getMessage());
        }
    }

    /**
     * Efface le cercle obj de la base de donnée.
     * Accessible par son nom.
     * @param id Nom de l'objet à effacer
     * @throws DeletionException En cas d'erreur lors de l'effacement
     */
    @Override
    public void delete(final String id) throws DeletionException {
        try (Connection conn = getConnection()) {
            try {
                conn.setAutoCommit(false);
                suppress(id, -1, conn);
                conn.commit();
            } catch (JDBCException | SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (JDBCException | SQLException e) {
            throw new DeletionException(id, e.getMessage());
        }
    }

}
