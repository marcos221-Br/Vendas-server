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
@Table(name = "users")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduser")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    @Basic(optional = false)
    @Column(name = "role")
    private String role;

    public User(Integer id, String name, String password, String role){
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(String name, String password, String role){
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(String name, String password){
        this.name = name;
        this.password = password;
        this.role = "user";
    }

    public User(String role){
        this.role = role;
    }

    public User(){
        this.role = "user";
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

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }

    @Override
    public String toString(){
        return "User\nId: " + this.id + "\nName: " + this.name + "\nPassword: " + this.password + "\nRole: " + this.role;
    }
}
