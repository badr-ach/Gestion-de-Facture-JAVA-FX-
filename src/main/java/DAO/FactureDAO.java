package DAO;

import Utils.Facture;
import Utils.MysqlConnection;

import java.sql.*;

public class FactureDAO implements IDAO<Facture>{

    private final Connection con;

    public FactureDAO(){
        con = MysqlConnection.getConnection();
    }

    @Override
    public ResultSet find(String id) {
        String sql = "Select * from factures where numFact = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getAll() {
        String sql = "Select * from factures";
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
    public boolean add(Facture factureDAO) {
        return false;
    }

    @Override
    public boolean update(Facture factureDAO) {
        String sql = "UPDATE factures set recevant = ? and date = ? and type = ? where numFact = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(4, factureDAO.getNumfact());
            System.out.println("Recevant " + factureDAO.getRecevant() + " type " + factureDAO.getType());
            st.setString(1,factureDAO.getRecevant());
            st.setString(2,factureDAO.getDate().toString());
            st.setString(3,factureDAO.getType());
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Facture factureDAO) {
        String sql = "DELETE from factures where numFact = ?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(factureDAO.getNumfact()));
            boolean rs = st.execute();
            return rs;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
