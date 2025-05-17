    package com.example.DAO;
    import com.example.models.DatabaseConnection;
    import com.example.models.User;
    import com.example.utilities.PasswordUtil;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;

    public class UserDAO {
        static private int currentID;

        public static int getCurrentID() {
            return currentID;
        }
        public static boolean login(String username, String password) {
            String hashPassword = PasswordUtil.hashPassword(password);
            String sql = "SELECT * FROM User WHERE username=? AND userPassword=?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, username);
                    stmt.setString(2, hashPassword);
                    ResultSet rs = stmt.executeQuery();



                if (rs.next()) {
                    currentID = rs.getInt("userID"); // Gán userID vào currentID
                    System.out.println("Đăng nhập thành công. ID: " + currentID);
                    return true;
                }

                System.out.println("Đăng nhập thất bại.");
                return false;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }


        public static boolean register(User user) {
            String sql = "INSERT INTO user (userID,username,userPassword,fullName,email, phone) VALUES (?,?,?,?,?,?)";
            try(Connection conn = DatabaseConnection.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1,user.getUserID());
                stmt.setString(2,user.getUsername());
                stmt.setString(3,PasswordUtil.hashPassword(user.getPassword()));
                stmt.setString(4,user.getName());
                stmt.setString(5,user.getEmail());
                stmt.setString(6,user.getPhone());

                stmt.executeUpdate();
                System.out.println("Dang ky thanh cong");
                return true;

            } catch (SQLException e) {
                if (e.getMessage().contains("Duplicate")) {
                    System.out.println("Đăng kí không thành công");
                } else {
                    e.printStackTrace();
                }
                return false;
            }
        }
        public static User getUserById(int userID) {
            String sql = "SELECT * FROM user WHERE userID=?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new User(
                            rs.getInt("userID"),
                            rs.getString("username"),
                            rs.getString("userPassword"),
                            rs.getString("fullName"),
                            rs.getString("email"),
                            rs.getString("phone")

                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
