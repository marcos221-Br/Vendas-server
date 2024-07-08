package sistema.de.vendas.models;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorder")
    private Integer id;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    private Client client;

    public Order(Integer id, Client client, Date date){
        this.id = id;
        this.client = client;
        this.date = date;
    }

    public Order(Client client, Date date){
        this.client = client;
        this.date = date;
    }

    public Order(){
        
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Client getClient(){
        return this.client;
    }

    public void setClient(Client client){
        this.client = client;
    }

    @Override
    public String toString(){
        return "Order\nId: " + this.id + "\nClient: " + this.client;
    }
}
