package fr.dauphine.ProjetMSMA.Transactions.Model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    private Long idTransaction;

    @Column(name="devise_src")
    private String source;

    @Column(name="devise_dest")
    private String dest;

    private int montant;

    @Column(name="date_cotation")
    private Date dateCotation;

    private BigDecimal taux;

    public Transaction() {

    }

    public Transaction(Long id, String source, String dest, int montant, Date dateCotation, BigDecimal taux) {
        super();
        this.idTransaction = id;
        this.source = source;
        this.dest = dest;
        this.montant = montant;
        this.dateCotation = dateCotation;
        this.taux = taux;
    }

    public Long getId() {
        return idTransaction;
    }

    public String getSource() {
        return source;
    }

    public String getDest() {
        return dest;
    }

    public Date getDateCotation(){
        return dateCotation;
    }

    public int getMontant(){
        return montant;
    }

    public BigDecimal getTaux() {
        return taux;
    }
}