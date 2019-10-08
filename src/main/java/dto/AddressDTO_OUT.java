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
public class AddressDTO_OUT
{
    @Schema(name="ID", example = "1")
    private Integer id;
    @Schema(example = "Mågevej")
    private String street;
    @Schema(example = "Nr. 4")
    private String additionalInfo;
    @Schema(example = "None available")
    private CityInfoDTO_OUT cityInfo;

    public AddressDTO_OUT(Integer id, String street, String additionalInfo)
    {
        this.id = id;
        this.street = street;
        this.additionalInfo = additionalInfo;
    }

    AddressDTO_OUT(Address address)
    {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.cityInfo = new CityInfoDTO_OUT(address.getCityinfo());
    }
    
    AddressDTO_OUT(AddressDTO_IN address)
    {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.cityInfo = new CityInfoDTO_OUT(address.getCityInfo());
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

    public CityInfoDTO_OUT getCityInfo()
    {
        return cityInfo;
    }

    public void setCityInfo(CityInfoDTO_OUT cityInfo)
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
        final AddressDTO_OUT other = (AddressDTO_OUT) obj;
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
