package DAO;

import Models.User;
import Utils.MysqlConnection;

import java.sql.*;

public class UserDAO implements IDAO<User>{
    private Connection con;

    public UserDAO(){
        con = MysqlConnection.getConnection();
    }


    @Override
    public ResultSet find(String reg){
        String sql = "Select * from users where username like '"+reg+"%'";
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public boolean Login(User user){
        String sql = "Select * from users where username = ? and password = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2,user.getPassword());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                user = new User(rs.getString("username"),rs.getString("password"));
                return true;
            }
            return false;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ResultSet getAll(){
        String sql = "Select * from users";
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(User user){
        String sql = "INSERT INTO users ( username , password ) values ( ? , ? )";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2,user.getPassword());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User user){
        String sql = "UPDATE users set password = ? where username = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(2, user.getUsername());
            st.setString(1,user.getPassword());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(User user){
        String sql = "DELETE from users where username = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getUsername());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
