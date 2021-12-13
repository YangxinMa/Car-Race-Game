package com.team20.Dao;

import com.team20.Utils.JdbcUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RankDao {

//    Map<String, Float> map = new HashMap<String, Float>();


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

//        Connection conn = null;
//        Statement st = null;
//        ResultSet rs = null;
//
//        try {
//            conn = JdbcUtils.getConnection();
//            st = conn.createStatement();
//
//            st.execute("use test");
//
//            boolean check = true;
//            String sql = "select * from ranks where username ='" + username + "'";
//            rs = st.executeQuery(sql);
//            while (rs.next()){
//                if(score > rs.getFloat("score")){
//                    check = false;
//                }
//            }
//            if (!check){
//                sql = "insert into ranks (username, score) values('" + username + "'"+", " + score + ") on duplicate key update score=values(score)";
//                st.executeUpdate(sql);
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                JdbcUtils.release(conn, st, rs);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            conn = JdbcUtils.getConnection();

            String sql = "use test";

            st = conn.prepareStatement(sql);

            st.execute();

            boolean check = true;
            sql = "select * from ranks where username = ?";

            st = conn.prepareStatement(sql);

            st.setString(1,username);

            rs = st.executeQuery();
            while (rs.next()){
                if(score > rs.getFloat("score")){
                    check = false;
                }
            }

            if(!check) {

                sql = "insert into ranks (username, score) values(?, ?) on duplicate key update score=values(score)";
                ;

                st = conn.prepareStatement(sql);

                st.setString(1, username);
                st.setFloat(2, score);
                st.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public List<Map> getData(){
        List<Map> userList = new ArrayList<>();
//        map = new HashMap<String, Float>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            st.execute("use test");
//            st.executeUpdate("ALTER TABLE ranks ORDER BY score DESC");
            String sql = "select * from ranks order by score DESC";
            rs = st.executeQuery(sql);
            Integer i = 1;
            while (rs.next()) {
                String username = rs.getString("username");
                Float score = rs.getFloat("score");
                Map dict = new HashMap();
                dict.put("User", username);
                dict.put("Rank", i);
                dict.put("Score", score);
                userList.add(dict);
                i++;
//                map.put(username, score);
//                System.out.println(username + " " + score);
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


        return userList;
    }

    public String addUser(String username){
//        Connection conn = null;
//        Statement st = null;
//        ResultSet rs = null;
//
//        try {
//            conn = JdbcUtils.getConnection();
//            st = conn.createStatement();
//
//
//
//
//            st.execute("use test");
//
//            boolean check = true;
//
//
//
//            String sql = "select * from ranks where username ='" + username + "'";
//            rs = st.executeQuery(sql);
//            while (rs.next()){
//                check = false;
//            }
//
//            if (check){
//                sql = "insert into ranks (username, score) values('" + username  +  "'" +", " + 0 + ") on duplicate key update score=values(score)";
//                st.executeUpdate(sql);
//
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                JdbcUtils.release(conn, st, rs);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return username;
//    }


        //---------------------------------------
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();

            st = conn.prepareStatement("use test");

            st.execute();

            boolean check = true;

            String sql = "select * from ranks where username = ?";

            st = conn.prepareStatement(sql);

            st.setString(1,username);

            rs = st.executeQuery();

            while (rs.next()){
                check = false;
            }

            if(check) {

                sql = "insert into ranks (username, score) values(?, 0) on duplicate key update score=values(score)";

                st = conn.prepareStatement(sql);

                st.setString(1, username);

                st.executeUpdate();
            }


        } catch (Exception e) {
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
