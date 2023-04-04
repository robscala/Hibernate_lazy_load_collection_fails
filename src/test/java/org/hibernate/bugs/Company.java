package org.hibernate.bugs;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="company")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="CompanyType", discriminatorType= DiscriminatorType.INTEGER)
public class Company {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected long id;

    @SuppressWarnings("FieldMayBeFinal")
    @OneToMany(mappedBy="company", orphanRemoval=true, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Person> people = new ArrayList<>();

    public List<Person> getPeople() {
        return people;
    }

    public void addPerson(Person person) {
        people.add(person);
        person.setCompany(this);
    }
}