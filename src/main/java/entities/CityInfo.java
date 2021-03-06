package entities;

import dto.CityInfoDTO_IN;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "CityInfo.getAll", query = "SELECT c FROM CityInfo c"),
    @NamedQuery(name = "CityInfo.deleteAllRows", query = "DELETE FROM CityInfo"),
    @NamedQuery(name = "CityInfo.getCityByName", query = "SELECT c FROM CityInfo c WHERE c.city = :city"),
    @NamedQuery(name = "CityInfo.getCityByZip", query = "SELECT c FROM CityInfo c WHERE c.zipCode = :zip"),
    @NamedQuery(name = "CityInfo.getCity", query = "SELECT c FROM CityInfo c WHERE c.zipCode = :zip AND c.city = :city"),
    @NamedQuery(name = "CityInfo.getZipCode", query = "SELECT c.zipCode FROM CityInfo c"),
    @NamedQuery(name = "CityInfo.getCitizens", query = "SELECT p FROM Person p WHERE p.address = (SELECT a FROM Address a WHERE a.cityinfo = (SELECT c FROM CityInfo c WHERE c.city = :city AND c.zipCode = :zip))")
})
public class CityInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String zipCode;
    private String city;
    
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "cityinfo",
            cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private List<Address> addresses = new ArrayList();
    
    public CityInfo() {
    }

    public CityInfo(String zipCode, String city, List<Address> addresses) {
        this.zipCode = zipCode;
        this.city = city;
        this.addresses = addresses;
    }
    
    public CityInfo(String zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
        this.addresses = new ArrayList();
    }
    
    public CityInfo(CityInfoDTO_IN cityInfo) {
        this.id = cityInfo.getId();
        this.zipCode = cityInfo.getZipCode();
        this.city = cityInfo.getCity();
    }
    
    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
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
        final CityInfo other = (CityInfo) obj;
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.addresses, other.addresses)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CityInfo{" + "id=" + id + ", zipCode=" + zipCode + ", city=" + city + ", addresses=" + addresses + '}';
    }
    
}
