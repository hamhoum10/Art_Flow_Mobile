package entities;


public class Ligne_panier {


    // Variables pour la gestion du panier
    private int id;
    private int id_article;
    private  Article article;

    private  Panier panier;
    private int id_panier;

    private Double prix_unitaire;
    private int quantity;

    private String nom_article;

    private String description;

    // Constructeur
    public Ligne_panier() {}

    /*public Ligne_panier(Article article, int id_panier , int quantity) {
        this.article = article;
        this.id_panier = id_panier;
        //this.prix = prix;
        this.quantity = quantity;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public Double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(Double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNom_article() {
        return nom_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ligne_panier{" + "id=" + id + ", id_article=" + id_article + ", article=" + article + ", panier=" + panier + ", id_panier=" + id_panier + ", prix_unitaire=" + prix_unitaire + ", quantity=" + quantity + ", nom_article=" + nom_article + ", description=" + description + '}';
    }

    
}
