package sn.dev.jee.dao;

import org.hibernate.SessionFactory;

import sn.dev.jee.dao.impl.ProduitImpl;
import sn.dev.jee.entity.Produit;


public class Test {
	public static void main(String[] args) {
		// Initialisation de la session de connexion Hibernate
        SessionFactory sessionFactory = SingletonConnection.getSessionFactory();

        // Vérifier la création de la table en ouvrant et fermant une session
        sessionFactory.openSession().close();
        
        ProduitImpl impl = new ProduitImpl(sessionFactory);
        
        // TEST DE LA LISTE DES PRODUITS
        impl.liste("Ram4");
        
        // TEST DE MODIIFICATION PRODUIT
//        Produit produit = impl.gestProduitById(1);
//        System.out.println("PRODUIT: "+produit.getDesignation());
//        produit.setDesignation("mmmm");
//        produit.setPrix(1111);
//        produit.setQuantite(1);
//        impl.modifier(produit);
        
        // TEST DE LA LISTE DES PRODUITS
        impl.supprimer(1);
//        
//        Produit produit = new Produit();
//        
//        produit.setDesignation("Souris");
//        produit.setPrix(2500);
//        produit.setQuantite(1);
//        
//        impl.ajouter(produit);

        // Fermer la sessionFactory
        sessionFactory.close();
	}
}
