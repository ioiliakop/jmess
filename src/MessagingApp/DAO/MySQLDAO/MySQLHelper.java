package MessagingApp.DAO.MySQLDAO;

import MessagingApp.DBConnection.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class MySQLHelper {

    static int SQLDeleteById(String SQLString, long id){
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQLString)) {

            pstmt.setLong(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted == 1) {
                return rowsDeleted;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    static int SQLUpdateVarcharFieldById(String SQLString, String updatedField, long id){
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQLString)) {

            pstmt.setString(1, updatedField);
            pstmt.setLong(2, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated == 1) {
                return rowsUpdated;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
