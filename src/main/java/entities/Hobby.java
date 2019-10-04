/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Hobby.deleteAllRows", query = "DELETE FROM Hobby"),
@NamedQuery(name = "Hobby.getAll", query = "SELECT h FROM Hobby h"),
@NamedQuery(name = "Hobby.getHobbyByID", query = "SELECT h FROM Hobby h WHERE h.id = :id"),
})
public class Hobby implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name, description;
    
    @ManyToMany(mappedBy = "hobbys", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private List<Person> persons = new ArrayList();

    public Hobby()
    {}
    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<Person> getPersons()
    {
        return persons;
    }

    public void setPersons(List<Person> persons)
    {
        this.persons = persons;
    }
    
    public void addPerson(Person person)
    {
        this.persons.add(person);
    }
    
}
