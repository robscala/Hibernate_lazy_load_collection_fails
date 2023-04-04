package org.hibernate.bugs;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String firstName;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", foreignKey = @ForeignKey())
    private Company company;

    public Person() {
    }

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
