package riccardogulin.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Cane") // <-- Cambio il valore all'interno della discriminator column. Da Dog (default) a Cane
public class Dog extends Animal {
	private double maxSpeed;

	public Dog() {
	}

	public Dog(String name, int age, double maxSpeed) {
		super(name, age);
		this.maxSpeed = maxSpeed;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	@Override
	public String toString() {
		return "Dog{" +
				"maxSpeed=" + maxSpeed +
				", id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", owner=" + owner +
				"} ";
	}
}
