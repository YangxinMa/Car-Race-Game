package com.team20.Dao;

import com.team20.Utils.JdbcUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RankDao {

    Map<String, Float> map = new HashMap<String, Float>();

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

            st.execute("DROP TABLE IF EXISTS ranks");

            st.execute("CREATE TABLE ranks( username VARCHAR(50) NOT NULL, score FLOAT NOT NULL)");

            st.execute("ALTER TABLE ranks ADD PRIMARY KEY (username)");

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

    public void save(String username, Float score){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            st.execute("use test");
            String sql = "insert into ranks (username, score) values('" + username + "'"+", " + score + ") on duplicate key update score=values(score)";
            st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Map<String, Float> getData(){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            st.execute("use test");
            String sql = "select * from ranks order by score desc";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                map.put(rs.getString("username"), rs.getFloat("score"));
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
        return map;
    }

    public String addUser(String username){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            st.execute("use test");
            String sql = "insert into ranks (username, score) values('" + username  +  "'" +", " + 0 + ") on duplicate key update score=values(score)";
            st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return username;
    }

}
