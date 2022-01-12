package Utils;


import java.time.LocalDate;

/**
 *
 * @author Badr
 */
abstract public
        class Facture{
    private String numfact;
    private LocalDate date;

    public String getRecevant() {
        return recevant;
    }

    public void setRecevant(String recevant) {
        this.recevant = recevant;
    }

    public Facture(String numfact) {
        this();
        this.numfact = numfact;
    }

    public Facture(LocalDate date){
        this.date = date;
    }

    public Facture(){
        date = LocalDate.now();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumfact(){
        return numfact;
    }

    public void setNumfact(String numfact){
        this.numfact = numfact;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public String toString(){
        return "Num de la facture : " + this.getNumfact() + ", date : " + this.getDate();
    }


    public Facture(String numfact, LocalDate date,String recevant,String type) {
        this.numfact = numfact;
        this.date = date;
        this.recevant = recevant;
        this.type = type;
    }

    private String type;
    private String recevant;
}
