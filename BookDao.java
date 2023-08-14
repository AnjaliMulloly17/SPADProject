package mainlibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao {
    public static int save(String callno,String name,String author,String publisher,int quantity) throws SQLException {
        int status = 0;
        try(Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO books(callno,name,author,publisher,quantity) VALUES(?,?,?,?,?)");
            ps.setString(1, callno);
            ps.setString(2, name);
            ps.setString(3, author);
            ps.setString(4, publisher);
            ps.setInt(5, quantity);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean publisherValidate(String publisher) throws SQLException {
        boolean status = false;
        try(Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Publisher WHERE PublisherName = ?");
            ps.setString(1, publisher);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static int addPublisher(String publisher) throws SQLException {
        int status = 0;
        try(Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Publisher(PublisherName) VALUES(?)");
            ps.setString(1, publisher);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static int saveBook(String bookN, String authorN, String publisherN, String shelfN, String rowN, String genreN) throws SQLException {
        int status = 0;
        try(Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Books(BookName,Author,Genre,Publisher,Shelf,Row) VALUES(?,?,?,?,?,?)");
            ps.setString(1, bookN);
            ps.setString(2, authorN);
            ps.setString(3, genreN);
            ps.setString(4, publisherN);
            ps.setString(5, shelfN);
            ps.setString(6, rowN);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static int delete(int bookID) throws SQLException {
        int status = 0;
        try(Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Books WHERE BookID = ?");
            ps.setInt(1, bookID);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
