import java.sql.*;



public class Program {
    public static void main(String[] args) {

        Connection conn = null;


        Statement st = null;

        try {
            conn = DB.getConn();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 0 WHERE DepartmentId = 1 ");
            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 20 WHERE DepartmentId = 2 ");

            conn.commit();

            System.out.println("Rows1 affected: " + rows1);
            System.out.println("Rows2 affected: " + rows2);
        }
        catch (SQLException e){
            try {
                conn.rollback();
                throw new DbException(e.getMessage());
            }
            catch (SQLException e1){
                e.printStackTrace();
            }
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}