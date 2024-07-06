package sistema.de.vendas.models;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
    @Column(name = "quantity")
    private Integer quantity;

    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    @Basic(optional = false)
    @Column(name = "value")
    private Float value;

    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    private Client client;

    public Order(Integer id, Integer quantity, String description, Float value, Client client){
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.value = value;
        this.client = client;
    }

    public Order(Integer quantity, String description, Float value, Client client){
        this.quantity = quantity;
        this.description = description;
        this.value = value;
        this.client = client;
    }

    public Order(){
        
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getQuantity(){
        return this.quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Float getValue(){
        return this.value;
    }

    public void setValue(Float value){
        this.value = value;
    }

    public Client getClient(){
        return this.client;
    }

    public void setClient(Client client){
        this.client = client;
    }

    @Override
    public String toString(){
        return "Order\nId: " + this.id + "\nQuantity: " + this.quantity + "\nDescription: " + this.description + "\nValue: " + this.value + "\nClient: " + this.client;
    }
}
