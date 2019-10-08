/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 *
 * @author
 */
public class PhoneDTO_OUT
{
    @Schema(name = "ID", example = "1")
    private Integer id;
    @Schema(example = "80343434")
    private int number;
    @Schema(example = "Cell phone")
    private String description;

    public PhoneDTO_OUT(Integer id, int number, String description)
    {
        this.id = id;
        this.number = number;
        this.description = description;
    }

    public PhoneDTO_OUT(Phone phone)
    {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.description = phone.getDescription();
    }

    public PhoneDTO_OUT(PhoneDTO_IN phone)
    {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.description = phone.getDescription();
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
        final PhoneDTO_OUT other = (PhoneDTO_OUT) obj;
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
