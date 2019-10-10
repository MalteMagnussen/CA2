package facades;

import dto.CityInfoDTO_IN;
import dto.CityInfoDTO_OUT;
import dto.HobbyDTO_IN;
import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import dto.PhoneDTO_IN;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.ws.rs.WebApplicationException;

public class SearchFacade_Impl implements ISearchFacade {

    private static SearchFacade_Impl instance;
    private static EntityManagerFactory emf;

    private SearchFacade_Impl() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static SearchFacade_Impl getSearchFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SearchFacade_Impl();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public PersonDTO_OUT addPerson(PersonDTO_IN pDTO) {
        Person p = new Person(pDTO.getEmail(), pDTO.getFirstName(), pDTO.getLastName(), null);
        EntityManager em = getEntityManager();
        if (p.getEmail() == null || p.getFirstName() == null || p.getLastName() == null
                || p.getEmail().trim().equals("") || p.getFirstName().trim().equals("")
                || p.getLastName().trim().equals("")) {
            throw new WebApplicationException("Missing input", 400);
        }
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            PersonDTO_OUT pOUT = new PersonDTO_OUT(p);
            return pOUT;
        } catch (Exception ex) {
            System.out.println("Failed to persist object");
            //ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO_OUT> getAllPersonDTO_OUT() {
        EntityManager em = getEntityManager();
        try {
            List<Person> persons = em.createNamedQuery("Person.getAll").getResultList();
            if (persons.isEmpty()) {
                throw new WebApplicationException("No persons in database", 400);
            }
            List<PersonDTO_OUT> result = new ArrayList<>();
            persons.forEach((person) -> {
                result.add(new PersonDTO_OUT(person));
            });
            return result;
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO_OUT> getPersonDTO_OUT_ByHobby(String hobbyName) {
        EntityManager em = getEntityManager();
        try {
            List<Person> persons = em.createNamedQuery("Person.getPersonsByHobby").setParameter("name", hobbyName).getResultList();
            if (persons.isEmpty()) {
                throw new WebApplicationException("No persons with hobby in database", 400);
            }
            List<PersonDTO_OUT> result = new ArrayList<>();
            persons.forEach((person) -> {
                result.add(new PersonDTO_OUT(person));
            });
            return result;
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally {
            em.close();
        }
    }

    @Override
    public long getCountPersonByHobby(String hobbyName) {
        EntityManager em = getEntityManager();
        try {
            Long result = (long) em.createNamedQuery("Person.countPersonsByHobby").setParameter("name", hobbyName).getSingleResult();
            if (result == 0) {
                throw new WebApplicationException("No persons with hobby in database", 400);
            }
            return result;
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO_OUT editPerson(PersonDTO_IN personDTO) {
        if (personDTO.getId() == 0) {
            throw new WebApplicationException("Person ID was not set.", 400);
        }

        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            // Get person.
            Person person_database = em.find(Person.class, personDTO.getId());

            if (person_database == null) {
                throw new WebApplicationException("Could not find Person. Provided ID does not exist.", 400);
            }

            // Change all fields from the given DTO.
            // Address
            Address address;
            if (personDTO.getAddress() != null) {
                address = em.find(Address.class, personDTO.getAddress().getId());
                if (address == null) {
                    address = new Address(personDTO.getAddress());
                    em.persist(address);
                    person_database.setAddress(address);
                } else {
                    address = em.merge(address);
                    person_database.setAddress(address);
                }
            }

            // First Name
            if (personDTO.getFirstName() != null && !personDTO.getFirstName().isEmpty()) {
                person_database.setFirstName(personDTO.getFirstName());
            }

            // Last Name
            if (personDTO.getLastName() != null && !personDTO.getLastName().isEmpty()) {
                person_database.setLastName(personDTO.getLastName());
            }

            // Email
            if (personDTO.getEmail() != null && !personDTO.getEmail().isEmpty()) {
                person_database.setEmail(personDTO.getEmail());
            }

            // Hobbies
            List<HobbyDTO_IN> hobbies = personDTO.getHobbies();
            if (hobbies != null) {
                hobbies.forEach((hobbyDTO) -> {
                    Hobby hobby;
                    hobby = em.find(Hobby.class, hobbyDTO.getId());
                    if (hobby != null) {
                        hobby.setDescription(hobbyDTO.getDescription());
                        hobby.setName(hobbyDTO.getName());
                        em.merge(hobby);
                    } else {
                        hobby = new Hobby(hobbyDTO);
                        em.persist(hobby);
                    }
                    if (!person_database.getHobbies().contains(hobby)) {
                        person_database.addHobby(hobby);
                    }
                });
            }

            // Phone
            List<PhoneDTO_IN> phones = personDTO.getPhones();
            if (phones != null) {
                phones.forEach((phoneDTO) -> {
                    Phone phone;
                    phone = em.find(Phone.class, phoneDTO.getId());
                    if (phone != null) {
                        phone.setDescription(phoneDTO.getDescription());
                        phone.setNumber(phoneDTO.getNumber());
                        em.merge(phone);
                    } else {
                        phone = new Phone(phoneDTO);
                        em.persist(phone);
                    }
                    if (!person_database.getPhones().contains(phone)) {
                        person_database.addPhone(phone);
                    }
                });
            }

            Person mergedPerson = em.merge(person_database);
            em.getTransaction().commit();
            return new PersonDTO_OUT(mergedPerson);

        } catch (RollbackException ex) {
            throw new WebApplicationException("Something went wrong while editing person.", 500);
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO_OUT deletePerson(Integer id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            // Find Person from ID
            Person person = em.find(Person.class, id);
            if (person == null) {
                throw new WebApplicationException("Could not delete Person. Provided ID does not exist.", 400);
            }
            // Remove all the phones
            person.getPhones().forEach((phone) -> {
                em.remove(phone);
            });
            em.remove(person);
            em.getTransaction().commit();
            return new PersonDTO_OUT(person);
        } catch (RollbackException ex) {
            em.getTransaction().rollback();
            throw new WebApplicationException("Error when removing person.", 500);
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO_OUT addPersonWithEverything(PersonDTO_IN personDTO) {
        // Guard for PersonDTO being null / empty
        if (personDTO == null || personDTO.getEmail() == null || personDTO.getEmail().isEmpty()
                || personDTO.getFirstName() == null || personDTO.getFirstName().isEmpty()
                || personDTO.getLastName() == null || personDTO.getLastName().isEmpty()
                || personDTO.getAddress() == null || personDTO.getHobbies() == null || personDTO.getHobbies().isEmpty()
                || personDTO.getPhones() == null || personDTO.getPhones().isEmpty()) {
            throw new WebApplicationException("Missing Input", 400);
        }

        // Create Person
        Person person = new Person(personDTO.getEmail(), personDTO.getFirstName(), personDTO.getLastName());

        // Add Hobbies
        List<Hobby> hobbies = new ArrayList();
        personDTO.getHobbies().forEach((h) -> {
            hobbies.add(new Hobby(h));
        });

        // Add Phones
        List<Phone> phoneNumbers = new ArrayList();
        personDTO.getPhones().forEach((p) -> {
            phoneNumbers.add(new Phone(p));
        });

        // Add Address
        Address address = new Address(personDTO.getAddress());
        
        // Add City
        CityInfo city = new CityInfo(personDTO.getAddress().getCityInfo());

        EntityManager em = getEntityManager();

        try {
            // Begin Transaction
            em.getTransaction().begin();

            // Merge Hobbies
            hobbies.forEach((hobby) -> {
                em.merge(hobby);
                person.addHobby(hobby);
            });

            // Persist Phone Numbers
            phoneNumbers.forEach((phoneNumber) -> {
                em.persist(phoneNumber);
                person.addPhone(phoneNumber);
            });

            // Merge Address
            address = em.merge(address);
            
            // Merge City
            city = em.merge(city);
            address.setCityinfo(city);
            person.setAddress(address);

            // Persist person
            em.persist(person);

            // Commit. 
            em.getTransaction().commit();

            // Make return DTO and return it. 
            return new PersonDTO_OUT(person);

        } catch (RollbackException ex) {
            em.getTransaction().rollback();
            throw new WebApplicationException("Persisting person failed.", 500);
        } finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO_OUT> getPersonByFullName(String name) {
        EntityManager em = getEntityManager();
        String[] names = name.split(" ");
        try {
            Query query = em.createNamedQuery("Person.getPersonsByFullName");
            query.setParameter("firstName", names[0]);
            query.setParameter("lastName", names[1]);
            List<Person> results = query.getResultList();
            if (results.isEmpty()) {
                throw new WebApplicationException("No persons with that name in database", 400);
            }
            List<PersonDTO_OUT> personDTOs = new ArrayList<>();
            for (Person person : results) {
                personDTOs.add(new PersonDTO_OUT(person));
            }
            return personDTOs;
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally {
            em.close();
        }
    }

    /**
     * Get a List of All ZipCodes.
     *
     * @return List of Integer
     */
    @Override
    public List<Integer> getZipcodes() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("CityInfo.getZipCode");
            List<Integer> results = query.getResultList();
            if (results.isEmpty()) {
                throw new WebApplicationException("No cities in the database.", 400);
            } else {
                return results;
            }
        } finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO_OUT> getPersonsInCity(CityInfoDTO_IN city) {
        // Check if input is OK
        if (city == null || city.getCity() == null || city.getCity().isEmpty() || city.getZipCode() == null || city.getZipCode().isEmpty()) {
            throw new WebApplicationException("Missing Input", 400);
        }

        EntityManager em = getEntityManager();

        try {
            // Get a list of all persons living in given city.
            List<Person> persons = em.createNamedQuery("CityInfo.getCitizens")
                    .setParameter("city", city.getCity())
                    .setParameter("zip", city.getZipCode())
                    .getResultList();

            // Check if any people live in the city. 
            if (persons == null || persons.isEmpty()) {
                throw new WebApplicationException("No Persons lives in that city.", 400);
            }

            // Convert Persons to DTO.
            List<PersonDTO_OUT> returnList = new ArrayList<>();
            persons.forEach(person -> returnList.add(new PersonDTO_OUT(person)));

            // Return DTO list.
            return returnList;

        } // Remember to close resources. 
        // EntityManager sadly doesn't work with try-with resources. 
        finally {
            em.close();
        }
    }

    @Override
    public List<CityInfoDTO_OUT> getCities() {
        EntityManager em = getEntityManager();
        try {
            // Get a list of all cities.
            List<CityInfo> cities = em.createNamedQuery("CityInfo.getAll").getResultList();

            // If there are cities in the database. 
            if (cities != null && !cities.isEmpty()) {
                // Make empty DTO List
                List<CityInfoDTO_OUT> citiesDTO = new ArrayList<>();
                // Convert cities to DTO. 
                cities.forEach(city -> citiesDTO.add(new CityInfoDTO_OUT(city)));
                return citiesDTO;
            } else {
                throw new WebApplicationException("No cities in the database.", 400);
            }
        } finally {
            em.close();
        }
    }

    @Override
    public CityInfoDTO_OUT getCityByName(String name) {
        // Input Guard
        if (name == null || name.isEmpty()) {
            throw new WebApplicationException("Wrong Input.", 400);
        }

        EntityManager em = getEntityManager();
        try {
            // Get all cities by name from database.
            CityInfo city = em.createNamedQuery("CityInfo.getCityByName", CityInfo.class).setParameter("city", name).getSingleResult();
            // Check if there exists any city with that name.
            if (city == null || city.getCity() == null || city.getCity().isEmpty() || city.getZipCode() == null || city.getZipCode().isEmpty()) {
                throw new WebApplicationException("No cities exists with that name.", 400);
            }
            // Convert Entity to DTO. 
            return new CityInfoDTO_OUT(city);

        } catch (NoResultException ex) { // Thrown by .getSingleResult()
            throw new WebApplicationException("No cities exists with that name.", 400);
        } catch (NonUniqueResultException ex) { // Thrown by .getSingleResult()
            throw new WebApplicationException("More than one city exists with that name", 400);
        } finally {
            em.close();
        }
    }

    @Override
    public CityInfoDTO_OUT getCityByZipCode(String zip) {
        // Input Guard
        if (zip == null || zip.isEmpty()) {
            throw new WebApplicationException("Wrong Input", 400);
        }

        EntityManager em = getEntityManager();

        try {
            // Get City from database.
            CityInfo city = em.createNamedQuery("CityInfo.getCityByZip", CityInfo.class).setParameter("zip", zip).getSingleResult();

            // Check if city exists.
            if (city == null || city.getCity() == null || city.getCity().isEmpty() || city.getZipCode() == null || city.getZipCode().isEmpty()) {
                throw new WebApplicationException("No cities exists with that zipCode.", 400);
            }

            return new CityInfoDTO_OUT(city);
        } catch (NoResultException ex) { // Thrown by .getSingleResult()
            throw new WebApplicationException("No cities exists with that zipCode.", 400);
        } catch (NonUniqueResultException ex) { // Thrown by .getSingleResult()
            throw new WebApplicationException("More than one city exists with that zipCode", 500);
        } finally {
            em.close();
        }
    }

    @Override
    public CityInfoDTO_OUT createCity(String name, String zipCode, List<Address> addresses) {
        // Input Guard
        if (name == null || name.isEmpty() || zipCode == null || zipCode.isEmpty()) {
            throw new WebApplicationException("Wrong Input", 400);
        }

        EntityManager em = getEntityManager();

        try {
            CityInfo city = new CityInfo(zipCode, name, addresses);
            em.getTransaction().begin();
            em.persist(city);
            em.getTransaction().commit();
            return new CityInfoDTO_OUT(city);
        } catch (RollbackException ex) { // Thrown by em.commit()
            em.getTransaction().rollback();
            throw new WebApplicationException("Database error when persisting city.", 500);
        } finally {
            em.close();
        }
    }

    @Override
    public CityInfoDTO_OUT editCity(Integer ID, String name, String zipCode, List<Address> addresses) {
        // Input guard
        if (ID == null || ID == 0 || name == null || name.isEmpty() || zipCode == null || zipCode.isEmpty() || addresses == null) {
            throw new WebApplicationException("Wrong Input", 400);
        }

        EntityManager em = getEntityManager();

        try {
            CityInfo city = new CityInfo(zipCode, name, addresses);
            city.setId(ID);
            em.getTransaction().begin();
            em.merge(city);
            em.getTransaction().commit();
            return new CityInfoDTO_OUT(city);
        } catch (RollbackException ex) { // Thrown by em.commit()
            em.getTransaction().rollback();
            throw new WebApplicationException("Database error when editing city.", 500);
        } finally {
            em.close();
        }
    }

    @Override
    public CityInfoDTO_OUT deleteCity(int ID) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            CityInfo city = em.find(CityInfo.class, ID);

            em.remove(city);
            em.getTransaction().commit();
            return new CityInfoDTO_OUT(city);
        } catch (RollbackException ex) { // Thrown by em.commit()
            em.getTransaction().rollback();
            throw new WebApplicationException("Database error when deleting city.", 500);
        } catch (IllegalArgumentException ex) { // Thrown by em.find()
            throw new WebApplicationException("Wrong input");
        } finally {
            em.close();
        }
    }

    public CityInfo getCity(String name) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            CityInfo city = em.createNamedQuery("CityInfo.getCityByName", CityInfo.class).setParameter("city", name).getSingleResult();
            if (city != null) {
                return city;
            } else {
                throw new WebApplicationException("No cities exists with that name.", 400);
            }
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        } finally {
            em.close();
        }
    }

}
