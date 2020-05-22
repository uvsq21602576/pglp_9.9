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
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Rectangle;

/**
 * DAO JDBC.
 * Pour Rectangle.
 * @author Flora
 */
public class RectangleDAOJDBC extends DAO<Rectangle> {

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
     * Crée la tables utile pour Rectangle.
     * C'est à dire : Rectangle.
     * @param conn Connexion à la base de donnée
     * @throws CreationTableException En cas d'erreur lors de la creation des
     *         tables.
     */
    static void createTable(final Connection conn)
            throws CreationTableException {
        try {
            DessinDAOJDBC.createTable(conn);
            if (!tableExists("rectangle", conn)) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("Create table rectangle ("
                            + "nom varchar(30) not null, "
                            + "hgX int not null, " + "hgY int not null,"
                            + "bdX int not null, " + "bdY int not null,"
                            + "dessinID int not null,"
                            + "primary key (nom, dessinID), "
                            + "foreign key (dessinID) "
                            + "references dessin(dessinID))");
                }
            }
        } catch (MetaDataException | SQLException e) {
            throw new CreationTableException("rectangle", e.getMessage());
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
     * Insert l'objet rectangle dans la base de donnée.
     * Via la connexion conn.
     * @param obj Rectangle à insérer
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connexion à la base de donnée
     * @throws InsertionException En cas d'erreur lors de l'insertion de données
     * @throws CreationTableException En cas d'erreur lors de la creation des
     *         tables
     * @throws DoublonException En cas de duplication de clé, si l'objet obj
     *         existe déjà
     */
    static void insert(final Rectangle obj, final int dessinID,
            final Connection conn) throws InsertionException,
            CreationTableException, DoublonException {
        try {
            createTable(conn);
            try (PreparedStatement insertRectangle = conn.prepareStatement(
                    "INSERT INTO rectangle values (?, ?, ?, ?, ?, ?)")) {
                remplitStatement(insertRectangle, obj.getNom(),
                        obj.getPointReference().getX(),
                        obj.getPointReference().getY(),
                        obj.getPointBasDroit().getX(),
                        obj.getPointBasDroit().getY(), dessinID);
                insertRectangle.execute();
            }
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            throw new DoublonException(obj.getNom());
        } catch (SQLException | RemplissageStatementException e) {
            throw new InsertionException(obj.getNom(), e.getMessage());
        }
    }

    /**
     * Créer le rectangle obj dans la base de donnée.
     * @param obj Retangle à créer
     * @return le rectangle créé
     * @throws CreationObjetException En cas d'erreru à la création de l'objet.
     * @throws DoublonException En cas de duplication de clé, si l'objet obj
     *         existe déjà
     */
    @Override
    public Rectangle create(final Rectangle obj)
            throws CreationObjetException, DoublonException {
        Rectangle result = null;
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
     * Lit tous les rectangle dans la base de donnée.
     * Rectangles appartenant au dessin dessinID
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connection à la base de donnée
     * @return Liste de Rectangles lus
     * @throws LectureException En cas d'erreur lors de la lecture
     */
    static List<ComposantDessin> readAll(final int dessinID,
            final Connection conn) throws LectureException {
        ArrayList<ComposantDessin> listeRectangle = new ArrayList<>();
        try {
            if (!tableExists("rectangle", conn)) {
                return listeRectangle;
            }
        } catch (MetaDataException e) {
        }
        try (PreparedStatement selectRectangle = conn.prepareStatement(
                "SELECT * FROM rectangle " + "WHERE dessinID = ?")) {
            remplitStatement(selectRectangle, dessinID);
            try (ResultSet rs = selectRectangle.executeQuery()) {
                while (rs.next()) {
                    listeRectangle.add(new Rectangle(rs.getString("nom"),
                            new Point(rs.getInt("hgX"), rs.getInt("hgY")),
                            new Point(rs.getInt("bdX"), rs.getInt("bdY"))));
                }
            }
        } catch (SQLException | RemplissageStatementException e) {
            throw new LectureException("rectangles", e.getMessage());
        }
        return listeRectangle;
    }

    /**
     * Lit un rectangle dans la base de donnée.
     * Rectangle accessible par son nom id et l'id de son dessin.
     * @param id Nom du rectangle à lire
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connection à la base de donnée
     * @return Rectangle lu
     * @throws LectureException En cas d'erreur lors de la lecture
     * @throws InexistantException Si le rectangle id n'existe pas
     */
    static Rectangle read(final String id, final int dessinID,
            final Connection conn)
            throws LectureException, InexistantException {
        try {
            if (!tableExists("rectangle", conn)) {
                throw new InexistantException(id);
            }
        } catch (MetaDataException e) {
        }
        try (PreparedStatement selectRectangle = conn.prepareStatement(
                "SELECT * FROM rectangle WHERE nom = ? AND dessinID = ?")) {
            remplitStatement(selectRectangle, id, dessinID);
            try (ResultSet rs = selectRectangle.executeQuery()) {
                if (rs.next()) {
                    Rectangle c = new Rectangle(rs.getString("nom"),
                            new Point(rs.getInt("hgX"), rs.getInt("hgY")),
                            new Point(rs.getInt("bdX"), rs.getInt("bdY")));
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
     * Recherche un rectangle dans la base de donnée.
     * Rectangle accessible par son nom id.
     * @param id Nom du rectangle recherché
     * @return Rectangle lu, null si erreur
     * @throws RechercheException En cas d'erreur lors de la recherche
     * @throws InexistantException Si le rectangle id n'existe pas
     */
    @Override
    public Rectangle find(final String id)
            throws InexistantException, RechercheException {
        Rectangle result = null;
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
     * Modifie le rectangle dans la base de donnée.
     * Lui assigne ces nouveaux paramètres.
     * Accessible via son nom et l'id de son dessin.
     * @param obj Rectangle à modifier
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connection à la base de donnée
     * @throws InexistantException Si le rectangle obj n'existe pas dans la base
     *         de
     *         donnée
     * @throws ModificationException En cas d'erreur pendant la modification.
     */
    static void modify(final Rectangle obj, final int dessinID,
            final Connection conn)
            throws InexistantException, ModificationException {
        try {
            if (!tableExists("rectangle", conn)) {
                throw new InexistantException(obj.getNom());
            }
        } catch (MetaDataException e) {
        }
        try {
            try (PreparedStatement selectRectangle =
                    conn.prepareStatement("SELECT * FROM rectangle "
                            + "WHERE nom = ? AND dessinID = ?")) {
                remplitStatement(selectRectangle, obj.getNom(), dessinID);
                try (ResultSet rs = selectRectangle.executeQuery()) {
                    if (!rs.next()) {
                        throw new InexistantException(obj.getNom());
                    }
                }
            }
            try (PreparedStatement updateRectangle = conn
                    .prepareStatement("UPDATE rectangle SET hgX = ?, hgY = ?, "
                            + "bdX = ?, bdY = ? "
                            + "WHERE nom = ? AND dessinID = ?")) {
                remplitStatement(updateRectangle,
                        obj.getPointReference().getX(),
                        obj.getPointReference().getY(),
                        obj.getPointBasDroit().getX(),
                        obj.getPointBasDroit().getY(), obj.getNom(), dessinID);
                updateRectangle.execute();
            }
        } catch (SQLException | RemplissageStatementException e) {
            throw new ModificationException(obj.getNom(), e.getMessage());
        }
    }

    /**
     * Met à jour le rectangle dans la base de donnée.
     * Lui assigne ces nouveaux paramètres.
     * Accessible via son nom.
     * @param obj Rectangle à mettre à jour
     * @throws InexistantException Si le rectangle obj n'existe pas dans la base
     *         de
     *         donnée
     * @throws MisAJourException En cas d'erreur pendant la mise à jour.
     */
    @Override
    public Rectangle update(final Rectangle obj)
            throws InexistantException, MisAJourException {
        Rectangle result = null;
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
     * Supprime les rectangle appartenant à dessinID de la base de données.
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppressAll(final int dessinID, final Connection conn)
            throws SuppressionException {
        try {
            if (tableExists("rectangle", conn)) {
                try (PreparedStatement deleteRectangle = conn.prepareStatement(
                        "DELETE FROM rectangle " + "WHERE dessinID = ?")) {
                    remplitStatement(deleteRectangle, dessinID);
                    deleteRectangle.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException("retangles", e.getMessage());
        }
    }

    /**
     * Supprime les rectangles appartenant à dessinID de la base de données.
     * Dont les noms ne sont pas dans noms.
     * @param noms Liste de noms de rectangle non à supprimer
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppressAllBut(final Object[] noms, final int dessinID,
            final Connection conn) throws SuppressionException {
        try {
            if (tableExists("rectangle", conn)) {
                String sql = "DELETE FROM rectangle " + "WHERE dessinID = ?";
                for (int i = 0; i < noms.length; i++) {
                    sql = sql.concat(" AND nom <> ?");
                }
                try (PreparedStatement deleteRectangle =
                        conn.prepareStatement(sql)) {
                    Object[] att = new Object[noms.length + 1];
                    att[0] = dessinID;
                    for (int i = 0; i < noms.length; i++) {
                        att[i + 1] = noms[i];
                    }
                    remplitStatement(deleteRectangle, att);
                    deleteRectangle.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException("rectangles", e.getMessage());
        }
    }

    /**
     * Supprime le rectangle obj de la base de donnée.
     * Accessible par son nom et l'id de son dessin.
     * @param id Nom de l'objet à supprimer
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppress(final String id, final int dessinID,
            final Connection conn) throws SuppressionException {
        try {
            if (tableExists("rectangle", conn)) {
                try (PreparedStatement deleteRectangle =
                        conn.prepareStatement("DELETE FROM rectangle "
                                + "WHERE nom = ? AND dessinID = ?")) {
                    remplitStatement(deleteRectangle, id, dessinID);
                    deleteRectangle.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException(id, e.getMessage());
        }
    }

    /**
     * Efface le rectangle obj de la base de donnée.
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
