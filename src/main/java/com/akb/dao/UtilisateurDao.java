package com.akb.dao;

import java.util.List;

import com.akb.beans.Utilisateur;

public interface UtilisateurDao {
	
	void ajouter(Utilisateur utilisateur) throws DaoException;
	
	List<Utilisateur> lister() throws DaoException;
}
