package fr.dauphine.ProjetMSMA.Transactions.Model;


import java.math.BigDecimal;
import java.util.Date;
//import javax.persistence.GeneratedValue;


public class TauxChange {

    private Long id;

    private String source;

    private String dest;

    private Date dateCotation;

    private BigDecimal taux;

    public TauxChange() {

    }

    public TauxChange(Long id, String source, String dest, Date dateCotation, BigDecimal taux) {
        super();
        this.id = id;
        this.source = source;
        this.dest = dest;
        this.dateCotation = dateCotation;
        this.taux = taux;
    }

    public Long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getDest() {
        return dest;
    }

    public Date getDateCotation() { return dateCotation; }

    public BigDecimal getTaux() {
        return taux;
    }
}
