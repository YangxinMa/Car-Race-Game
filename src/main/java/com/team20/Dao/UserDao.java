package com.team20.Dao;

import com.team20.Utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    static {

        Connection conn = null;
        Statement st = null;
//        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            st.execute("DROP DATABASE IF EXISTS test");

            st.execute("CREATE DATABASE test");

            st.execute("use test");

            st.execute("DROP TABLE IF EXISTS users");

            st.execute("CREATE TABLE users( id INT NOT NULL, username VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL)");

            st.executeUpdate("INSERT INTO users VALUE(1456,'2424@gmail.com','278782327')");

            st.executeUpdate("INSERT INTO users VALUE(1001,'abc@gmail.com','123456')");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(conn, st, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public static boolean checkLogin(String username, String password) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            st.execute("use test");
            String sql = "SELECT * FROM users WHERE username = '" + username + "' and password = '" + password + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}