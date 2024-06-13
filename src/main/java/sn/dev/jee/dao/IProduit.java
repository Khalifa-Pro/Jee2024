package sn.dev.jee.dao;

import java.util.List;

import sn.dev.jee.entity.Produit;


public interface IProduit {
	public void ajouter(Produit produit);
	public List<Produit> liste(String mc);
	//public List<Produit> liste();
	public Produit gestProduitById(long id);
	public void modifier(Produit produit);
	public void supprimer(long id);
}
