package sn.dev.jee.web;

import java.io.IOException;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.dev.jee.dao.IProduit;
import sn.dev.jee.dao.SingletonConnection;
import sn.dev.jee.dao.impl.ProduitImpl;
import sn.dev.jee.entity.Produit;
/**
 * Servlet implementation class ProduitServlet
 */
public class ProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sf;
	private IProduit iProduit;
    /**
     * Default constructor. 
     */
    public ProduitServlet() {
        // Empty constructor
    }
    
    
    @Override
    public void init() throws ServletException {
        try {
        	sf = SingletonConnection.getSessionFactory();
        	this.iProduit = new ProduitImpl(sf);
        } catch (Exception e) {
            throw new ServletException("Error initializing SessionFactory", e);
        }
    }
    @Override
    public void destroy() {
        if (sf != null) {
        	sf.close();
        }
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher("views/liste.jsp").forward(request, response);
		String action = request.getServletPath();
		
		try {
			switch (action) {
			
			case "/nouveau-produit":
				nouveauForm(request, response);
				break;
			case "/ajouter-produit":
				ajouter(request, response);
				break;
			case "/modification-produit":
				modificationForm(request, response);
				break;
			case "/modifier-produit":
				modifier(request, response);
				break;
			case "/supprimer-produit":
				supprimer(request, response);
				break;
			default:
				lister(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	/**
	 * FORMULAIRE D'AJOUT DE NOUVEAU PRODUIT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void nouveauForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("views/creation.jsp");
	}
	
	/**
	 * AJOUT D'UN PRODUIT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ajouter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String designation = request.getParameter("designation");
		String prix = request.getParameter("prix");
		String quantite = request.getParameter("quantite");

		Produit produit = new Produit();
		produit.setDesignation(designation);
		produit.setPrix(Double.parseDouble(prix));
		produit.setQuantite(Integer.parseInt(quantite));
		
		iProduit.ajouter(produit);
		
		response.sendRedirect("produit");
	}
	
	/**
	 * FORMULAIRE DE MODIFICATION DE NOUVEAU PRODUIT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modificationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
        Produit existProduit = iProduit.gestProduitById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/modification.jsp");
        request.setAttribute("produit", existProduit);
        dispatcher.forward(request, response);
	}
	
	/**
	 * MODIFICATION D'UN PRODUIT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modifier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String designation = request.getParameter("designation");
		String prix = request.getParameter("prix");
		String quantite = request.getParameter("quantite");

		Produit produit = new Produit();
		produit.setId(Long.parseLong(id));
		produit.setDesignation(designation);
		produit.setPrix(Double.parseDouble(prix));
		produit.setQuantite(Integer.parseInt(quantite));
		
		iProduit.modifier(produit);
		
		response.sendRedirect("produit");
	}
	
	/**
	 * SUPPRESSION D'UN PRODUIT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		iProduit.supprimer(id);
		response.sendRedirect("produit");
	}
	
	/**
	 * LISTE DES PRODUITS
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void lister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String motCle = request.getParameter("search");
		List<Produit> produits = iProduit.liste(motCle);
		request.setAttribute("produits", produits);
		request.getRequestDispatcher("views/liste.jsp").forward(request, response);
		
	}
	
}
