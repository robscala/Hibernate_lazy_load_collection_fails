package org.hibernate.bugs;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="manufacturer_computer_system_3")
@DiscriminatorValue("2")
public class ManufacturerComputerSystem extends ComputerSystem
{
    public ManufacturerComputerSystem()
    {
    }
}
