package sistema.de.vendas.models;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclient")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "cellphone")
    private String cellphone;

    public Client(Integer id, String name, String cellphone){
        this.id = id;
        this.name = name;
        this.cellphone = cellphone;
    }

    public Client(String name, String cellphone){
        this.name = name;
        this.cellphone = cellphone;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return this.cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public String toString() {
        return "Client\nId: " + this.id + "\nName: " + this.name + "\nCellphone: " + this.cellphone;
    }
}
