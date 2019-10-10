package dto;

import entities.CityInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 *
 * @author
 */
public class CityInfoDTO_OUT {

    @Schema(name = "ID", example = "1")
    private Integer id;
    @Schema(example = "2800")
    private String zipCode;
    @Schema(example = "Lyngby")
    private String city;

    public CityInfoDTO_OUT(Integer id, String zipCode, String city) {
        this.id = id;
        this.zipCode = zipCode;
        this.city = city;
    }

    public CityInfoDTO_OUT(CityInfo cityinfo) {
        this.id = cityinfo.getId();
        this.zipCode = cityinfo.getZipCode();
        this.city = cityinfo.getCity();
    }

    public CityInfoDTO_OUT(CityInfoDTO_IN cityinfo) {
        this.id = cityinfo.getId();
        this.zipCode = cityinfo.getZipCode();
        this.city = cityinfo.getCity();
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.zipCode);
        hash = 79 * hash + Objects.hashCode(this.city);
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
        final CityInfoDTO_OUT other = (CityInfoDTO_OUT) obj;
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CityInfoDTO_OUT{" + "id=" + id + ", zipCode=" + zipCode + ", city=" + city + '}';
    }

}
