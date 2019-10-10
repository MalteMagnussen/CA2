package entities;

import dto.AddressDTO_IN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author
 */
@NamedQueries({
    @NamedQuery(name = "Address.deleteAllRows", query = "DELETE FROM Address"),})

@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String additionalInfo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cityinfo_id")
    private CityInfo cityinfo;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "address",
            cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private List<Person> persons = new ArrayList();

    public Address() {
    }

    public Address(String street, String additionalInfo, CityInfo cityinfo, List<Person> persons) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityinfo = cityinfo;
        this.persons = persons;
    }

    public Address(String street, String additionalInfo, CityInfo cityinfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityinfo = cityinfo;
        this.persons = new ArrayList();
    }

    public Address(String street, String additionalInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityinfo = new CityInfo();
        this.persons = new ArrayList();
    }

    public Address(AddressDTO_IN address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public CityInfo getCityinfo() {
        return cityinfo;
    }

    public void setCityinfo(CityInfo cityinfo) {
        this.cityinfo = cityinfo;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.street);
        hash = 53 * hash + Objects.hashCode(this.additionalInfo);
        hash = 53 * hash + Objects.hashCode(this.cityinfo);
        hash = 53 * hash + Objects.hashCode(this.persons);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.additionalInfo, other.additionalInfo)) {
            return false;
        }
        if (!Objects.equals(this.cityinfo, other.cityinfo)) {
            return false;
        }
        if (!Objects.equals(this.persons, other.persons)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", street=" + street + ", additionalInfo=" + additionalInfo + ", cityinfo=" + cityinfo + ", persons=" + persons + '}';
    }

}
