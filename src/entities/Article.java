/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MediaStudio
 */
public class Article {
    int id_article;
   double price;
   String type;
   String image , description,Nom_article;
   Categorie categorie;
   int artiste;
   int quantity;

    public Article() {
    }

    public Article(double price, String type, String image, String description, String Nom_article, Categorie categorie, int quantity) {
        this.price = price;
        this.type = type;
        this.image = image;
        this.description = description;
        this.Nom_article = Nom_article;
        this.categorie = categorie;
        this.quantity = quantity;
    }
        
    public Article(int id_article, double price, String type, String image, String description, String Nom_article, Categorie categorie, int quantity) {
        this.id_article = id_article;
        this.price = price;
        this.type = type;
        this.image = image;
        this.description = description;
        this.Nom_article = Nom_article;
        this.categorie = categorie;
        this.artiste = artiste;
        this.quantity = quantity;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom_article() {
        return Nom_article;
    }

    public void setNom_article(String Nom_article) {
        this.Nom_article = Nom_article;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getArtiste() {
        return artiste;
    }

    public void setArtiste(int artiste) {
        this.artiste = artiste;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Article{" + "id_article=" + id_article + ", price=" + price + ", type=" + type + ", image=" + image + ", description=" + description + ", Nom_article=" + Nom_article + ", categorie=" + categorie + ", artiste=" + artiste + ", quantity=" + quantity + '}';
    }

  
   
   
}
