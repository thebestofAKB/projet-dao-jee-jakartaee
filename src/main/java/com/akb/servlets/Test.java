package com.akb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.akb.beans.BeanException;
import com.akb.beans.Utilisateur;
import com.akb.dao.DaoException;
import com.akb.dao.DaoFactory;
import com.akb.dao.UtilisateurDao;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtilisateurDao utilisateurDao;
	
	public void init() {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "I Love Java";
		HttpSession session = request.getSession();
		session.setAttribute("title", title);
		
		try {
			request.setAttribute("utilisateurs", utilisateurDao.lister());
		} catch (DaoException e) {
			request.setAttribute("erreur", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Utilisateur utilisateur = new Utilisateur();
			
			utilisateur.setNom(request.getParameter("nom"));
			utilisateur.setPrenom(request.getParameter("prenom"));
			utilisateurDao.ajouter(utilisateur);
			
		} catch (BeanException | DaoException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
