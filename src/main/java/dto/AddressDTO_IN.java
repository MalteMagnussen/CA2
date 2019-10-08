/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 *
 * @author
 */
public class AddressDTO_IN
{
    @Schema(name="ID", example = "1")
    private Integer id;
    @Schema(required = true, example = "Mågevej")
    private String street;
    @Schema(required = true, example = "Nr. 4")
    private String additionalInfo;
    @Schema(required = true, example = "None available")
    private CityInfoDTO_IN cityInfo;

    public AddressDTO_IN(Integer id, String street, String additionalInfo)
    {
        this.id = id;
        this.street = street;
        this.additionalInfo = additionalInfo;
    }

    AddressDTO_IN(Address address)
    {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.cityInfo = new CityInfoDTO_IN(address.getCityinfo());
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }

    public CityInfoDTO_IN getCityInfo()
    {
        return cityInfo;
    }

    public void setCityInfo(CityInfoDTO_IN cityInfo)
    {
        this.cityInfo = cityInfo;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.street);
        hash = 53 * hash + Objects.hashCode(this.additionalInfo);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final AddressDTO_IN other = (AddressDTO_IN) obj;
        if (!Objects.equals(this.street, other.street))
        {
            return false;
        }
        if (!Objects.equals(this.additionalInfo, other.additionalInfo))
        {
            return false;
        }
        return true;
    }
    
    
}
