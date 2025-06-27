import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DemoJDBC {
    public static void main(String[] args) throws Exception {
        /*

        Import packages (import java.sql.*;)
        Load drivers
        Register drivers
        Create connection
        Create statement
        Execute statement
        Close

         */

        int uId = 14;
        String name = "ji bhai";
        int marks = 70;
        int marksIdUpdate = 2;
        int deletedId = 7;

        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/myFirstDB";
        String uName = "postgres";
        String pass = "Hulkbuster#008";
        String query = "select * from student ";
        String updatedName = "phr se update ho gya maaz";

        String queryInsert = "insert into student values (?, ?, ?)";
        String queryUpdate = "update student set sname = ? where sid = ?  ";
        String queryDelete = "delete from student where  sid = ? ";


        DemoJDBC myClass = new DemoJDBC();

        Connection con = DriverManager.getConnection(url, uName, pass);
        System.out.println("Connection Established");


        Statement st = con.createStatement();
        ResultSet rt = st.executeQuery(query);

        System.out.println("===================");
        //getting values
        myClass.printResultSetValues(rt);

        //Insert Values
//        boolean insertresult = st.execute(queryInsert);
        PreparedStatement insertResult = con.prepareStatement(queryInsert);
        insertResult.setInt(1,uId);
        insertResult.setString(2,name);
        insertResult.setInt(3,marks);
        insertResult.execute();

        System.out.println("After inserting");

        //getting values
        System.out.println("===================");
        myClass.printResultSetValues(st.executeQuery(query));

        //update values
        PreparedStatement updateResult = con.prepareStatement(queryUpdate);
        updateResult.setString(1, updatedName);
        updateResult.setInt(2, marksIdUpdate);
        updateResult.execute();

        //getting values
        System.out.println("===================");
        myClass.printResultSetValues(st.executeQuery(query));

        //delete values
//        boolean deleteResult = st.execute(queryDelete);
        PreparedStatement deletedResult = con.prepareStatement(queryDelete);
        deletedResult.setInt(1,deletedId);

        //getting values
        System.out.println("===================");
        myClass.printResultSetValues(st.executeQuery(query));


        con.close();
        System.out.println("Connection Closed");

    }

    public void printResultSetValues(ResultSet rt) {
        try {
            while (rt.next()) {
                System.out.print(rt.getInt("sid") + "-");
                System.out.print(rt.getString(2) + "-");
                System.out.println(rt.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
