/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.CityInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 *
 * @author
 */
public class CityInfoDTO_IN
{
    @Schema(name = "ID", required = true, example = "1")
    private Integer id;
    @Schema(required = true, example = "2800")
    private String zipCode;
    @Schema(required = true, example = "Lyngby")
    private String city;

    public CityInfoDTO_IN(Integer id, String zipCode, String city)
    {
        this.id = id;
        this.zipCode = zipCode;
        this.city = city;
    }

    public CityInfoDTO_IN(CityInfo cityinfo)
    {
        this.id = cityinfo.getId();
        this.zipCode = cityinfo.getZipCode();
        this.city = cityinfo.getCity();
    }
    
    public CityInfoDTO_IN(CityInfoDTO_OUT cityinfo)
    {
        this.id = cityinfo.getId();
        this.zipCode = cityinfo.getZipCode();
        this.city = cityinfo.getCity();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.zipCode);
        hash = 79 * hash + Objects.hashCode(this.city);
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
        final CityInfoDTO_IN other = (CityInfoDTO_IN) obj;
        if (!Objects.equals(this.zipCode, other.zipCode))
        {
            return false;
        }
        if (!Objects.equals(this.city, other.city))
        {
            return false;
        }
        return true;
    }
    
    
}
