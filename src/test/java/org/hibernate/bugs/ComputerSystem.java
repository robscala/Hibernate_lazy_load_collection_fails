package org.hibernate.bugs;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="computer_system_3")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="CompanyType", discriminatorType= DiscriminatorType.INTEGER)
public class ComputerSystem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", foreignKey = @ForeignKey())
    protected Company owner = null;

    public void setOwner(Company owner) {
        this.owner = owner;
    }
}
