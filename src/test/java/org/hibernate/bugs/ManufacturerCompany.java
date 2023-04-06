package org.hibernate.bugs;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("1")
public class ManufacturerCompany extends Company {
    @OneToOne(orphanRemoval=true, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "COMPUTERSYSTEM_ID", foreignKey = @ForeignKey())
    private ManufacturerComputerSystem computerSystem;

    public void setComputerSystem(ManufacturerComputerSystem computerSystem) {
        this.computerSystem = computerSystem;
        computerSystem.setOwner(this);
    }
}
