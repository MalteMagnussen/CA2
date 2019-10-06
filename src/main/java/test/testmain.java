/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dto.PersonDTO_OUT;
import entities.Person;

/**
 *
 * @author
 */
public class testmain
{
    public static void main(String[] args)
    {
        Person p = new Person("hella@ass.com", "Hella", "Ass");
        System.out.println("Person p:\n" + p);
        PersonDTO_OUT pOUT = new PersonDTO_OUT(p);
        System.out.println("Person DTO_OUT:\n" + pOUT);
    }
}
