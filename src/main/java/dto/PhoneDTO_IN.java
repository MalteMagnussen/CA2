/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 *
 * @author
 */
public class PhoneDTO_IN
{
    @Schema(name = "ID", example = "1")
    private Integer id;
    @Schema(required = true, example = "80343434")
    private int number;
    @Schema(required = true, example = "Cell phone")
    private String description;

    public PhoneDTO_IN(Integer id, int number, String description)
    {
        this.id = id;
        this.number = number;
        this.description = description;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 17 * hash + this.number;
        hash = 17 * hash + Objects.hashCode(this.description);
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
        final PhoneDTO_IN other = (PhoneDTO_IN) obj;
        if (this.number != other.number)
        {
            return false;
        }
        if (!Objects.equals(this.description, other.description))
        {
            return false;
        }
        return true;
    }
    
    
}
