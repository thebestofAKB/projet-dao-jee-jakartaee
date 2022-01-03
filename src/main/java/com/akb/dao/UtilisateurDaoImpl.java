package com.akb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.akb.beans.BeanException;
import com.akb.beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
	private DaoFactory daoFactory;
	
	UtilisateurDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Utilisateur utilisateur) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?)");
			preparedStatement.setString(1, utilisateur.getNom());
			preparedStatement.setString(2, utilisateur.getPrenom());
			
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch(SQLException e) {
			try {
				
				if (connexion != null) {
					connexion.rollback();
				}
				
			} catch (SQLException e2) {}
			
			throw new DaoException("Impossible de communiquer avec la base de données");
		}
	}

	@Override
	public List<Utilisateur> lister() {
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		List<Utilisateur> users = new ArrayList<>();
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT nom, prenom FROM noms");
			
			while(resultat.next()) {
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				
				Utilisateur user = new Utilisateur();
				user.setNom(nom);
				user.setPrenom(prenom);
				
				users.add(user);
			}
				
			} catch (SQLException | BeanException e) {
				e.printStackTrace();
			} 
			finally {
				
				//Fermeture de la connexion
				try {
					if (resultat != null)
						resultat.close();
					if (statement != null)
						statement.close();
					if (connexion != null)
						connexion.close();
					
				} catch (SQLException ignore) {}
			}
		
		return users;
	}

}
