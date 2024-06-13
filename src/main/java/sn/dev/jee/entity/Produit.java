package sn.dev.jee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p"),
    @NamedQuery(name = "Produit.findByDesignation", query = "SELECT p FROM Produit p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produit.findById", query = "SELECT p FROM Produit p WHERE p.id = :id"),
    @NamedQuery(name = "Produit.update", query = "UPDATE Produit p set p.designation = :designation, p.prix = :prix, p.quantite = :quantite where id = :id"),
    @NamedQuery(name = "Produit.delete", query = "DELETE FROM Produit p WHERE p.id = :id"),

})

public class Produit {
	
	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Désignation",nullable = false)
	private String designation;
	
	public Long getId() {
		return id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Column(name = "Prix",nullable = false)
	private double prix;
	
	@Column(name = "Quantité")
	private int quantite;
	
	
}
