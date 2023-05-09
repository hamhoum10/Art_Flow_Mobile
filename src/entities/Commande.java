package entities;



public class Commande {
        //Player p =new Player();

        private int id;

        //private int customerId;
        private int id_panier; //baed twali panier
        private int id_client;

        private Panier panier;

        private String prénomClientCommande;

        private String nomClientCommande;

        private int numeroPhoneclient;
        private String status;
        private double totalAmount;
        private String createdAt;
        private int codepostal;
        private String adresse;
        private String email;




    // Constructeur pour établir une connexion à la base de données
        public Commande() {

        }

    public Commande(int id_client, String prénomClientCommande, String nomClientCommande, int numeroPhoneclient, int codepostal, String adresse,String email) {
        this.id_client = id_client;
        this.prénomClientCommande = prénomClientCommande;
        this.nomClientCommande = nomClientCommande;
        this.numeroPhoneclient = numeroPhoneclient;
        this.codepostal = codepostal;
        this.adresse = adresse;
    }
        


    public Commande(int id_panier, String prénomClientCommande, String nomClientCommande, int numeroPhoneclient, String status, double totalAmount, int codepostal, String adresse) {
        this.id_panier = id_panier;
        this.prénomClientCommande = prénomClientCommande;
        this.nomClientCommande = nomClientCommande;
        this.numeroPhoneclient = numeroPhoneclient;
        this.status = status;
        this.totalAmount = totalAmount;
        this.codepostal = codepostal;
        this.adresse = adresse;
    }

    public Commande(Panier panier, String prénomClientCommande, String nomClientCommande, int numeroPhoneclient, String status, double totalAmount, String createdAt, int codepostal, String adresse) {
        this.panier = panier;
        this.prénomClientCommande = prénomClientCommande;
        this.nomClientCommande = nomClientCommande;
        this.numeroPhoneclient = numeroPhoneclient;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.codepostal = codepostal;
        this.adresse = adresse;
    }

    // Getters pour les variables d'instance
        public int getId() {
            return id;
        }



        public int getId_panier() {
        return id_panier;
        }
        
        
        public void setId_panier(int id_panier) {
            this.id_panier = id_panier;
        }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
        

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
        

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public String getStatus() {
            return status;
        }

        public double getTotalAmount() {
            
            return totalAmount;
        }

        public String getCreatedAt() {
            return createdAt;
        }

    public int getCodepostal() {
        return codepostal;
    }


    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }



    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }*/

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPrénomClientCommande() {
        return prénomClientCommande;
    }

    public void setPrénomClientCommande(String prénomClientCommande) {
        this.prénomClientCommande = prénomClientCommande;
    }

    public String getNomClientCommande() {
        return nomClientCommande;
    }

    public void setNomClientCommande(String nomClientCommande) {
        this.nomClientCommande = nomClientCommande;
    }

    public int getNumeroPhoneclient() {
        return numeroPhoneclient;
    }

    public void setNumeroPhoneclient(int numeroPhoneclient) {
        this.numeroPhoneclient = numeroPhoneclient;
    }

    @Override
    public String toString() {
        return  //"id=" + id +
                //"id_panier=" + id_panier +System.lineSeparator()+
                "prénom : " + prénomClientCommande +
                "nom : " + nomClientCommande +
                "numero : " + numeroPhoneclient +
                "status : " + status +
                "totalAmount : " + totalAmount + 
                "createdAt : " + createdAt +
                "codepostal : " + codepostal +
                "adresse : " + adresse ;

    }
}


