package sn.dev.jee.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sn.dev.jee.dao.IProduit;
import sn.dev.jee.entity.Produit;


public class ProduitImpl implements IProduit{
	private SessionFactory sf;
	
	/**
	 * CONSTRUCTEUR AVEC INJECTION DE DEPENDANCE
	 * @param sf
	 */
	public ProduitImpl(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	/**
	 * AJOUT DE PRODUIT
	 */
	public void ajouter(Produit produit) {
		// TODO Auto-generated method stub
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		sn.save(produit);
		tx.commit();
		sn.close();
	}
	
	
	/**
	 * LISTE DES PRODUITS
	 */
	@Override
	public List<Produit> liste(String mc) {
		// TODO Auto-generated method stub
		List<Produit> liste = new ArrayList<Produit>();
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		if (mc != null) {
			Query<Produit> query = sn.createNamedQuery("Produit.findByDesignation", Produit.class);
	        query.setParameter("designation", mc);
			liste = query.getResultList();
			sn.close();
			return liste;
		} else {
			Query<Produit> query = sn.createNamedQuery("Produit.findAll", Produit.class);
			liste = query.getResultList();
			sn.close();
			return liste;
		}
        
	}

	@SuppressWarnings("deprecation")
	@Override
	/**
	 * MODIFICATION DE PRODUIT
	 */
	public void modifier(Produit produit) {
		// TODO Auto-generated method stub
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Produit> query = sn.createNamedQuery("Produit.update");
		query.setParameter("designation", produit.getDesignation());
        query.setParameter("prix", produit.getPrix());
        query.setParameter("quantite", produit.getQuantite());
        query.setParameter("id", produit.getId());
        query.executeUpdate();
		tx.commit();
		sn.close();
	}

	@SuppressWarnings("deprecation")
	@Override
	/**
	 * SUPPRESSION DE PRODUIT
	 */
	public void supprimer(long id) {
		// TODO Auto-generated method stub
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		
		@SuppressWarnings("rawtypes")
		Query query = sn.createNamedQuery("Produit.delete");
		query.setParameter("id",id);
		query.executeUpdate();
		tx.commit();
		sn.close();
	}

	
	@Override
	public Produit gestProduitById(long id) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		try {
			Query<Produit> queryById = sn.createNamedQuery("Produit.findById", Produit.class);
			queryById.setParameter("id", id);
			Produit produit = queryById.getSingleResult();
			tx.commit();
			return produit;
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			return null;
		}
		finally {
			sn.close();
		}
	}

//	@Override
//	public List<Produit> liste() {
//		// TODO Auto-generated method stub
//		List<Produit> liste = new ArrayList<Produit>();
//		Session sn = sf.openSession();
//		Transaction tx = sn.beginTransaction();
//		Query<Produit> query = sn.createNamedQuery("Produit.findAll", Produit.class);
//		liste = query.getResultList();
//		sn.close();
//		System.out.println("LISTE: "+liste);
//		return liste;
//	}

}
