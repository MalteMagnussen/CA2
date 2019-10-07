/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Malte
 */
@Entity
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int number;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    public Phone() {
    }

    public Phone(int number, String description, Person person) {
        this.number = number;
        this.description = description;
        this.person = person;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the value of number
     *
     * @return the value of number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set the value of number
     *
     * @param number new value of number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Phone{" + "number=" + number + ", description=" + description + ", person=" + person + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.number;
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.person);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Phone other = (Phone) obj;
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.person, other.person)) {
            return false;
        }
        return true;
    }
    
    

}
