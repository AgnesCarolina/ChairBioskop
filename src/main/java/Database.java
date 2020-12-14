import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class Database{
    //
    //simpan,tambah(update (statues)),tampil data
    //update, select v

    public Connection db;//interface
    public Database() throws SQLException, ClassNotFoundException {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        db = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "system");

    }

    public Vector<Chair> selectChair() throws SQLException {
        String qry = "SELECT * FROM CHAIR";
        java.sql.Statement s1 = db.createStatement();
        ResultSet rs = s1.executeQuery(qry);
        Vector<Chair> chairDatas = new Vector<>();
        while (rs.next()) {
            String Chair_No = rs.getString("Chair_No");
            String Status = rs.getString("Status");
            chairDatas.add(new Chair(Chair_No,Status));
        }
        return chairDatas;
    }

    public boolean isConnected(){
        return( db != null);
    }

}