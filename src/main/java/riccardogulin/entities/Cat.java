package riccardogulin.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Gatto") // <-- Cambio il valore all'interno della discriminator column. Da Cat (default) a Gatto
public class Cat extends Animal {
	private double maxJumpHeight;

	public Cat() {
	}

	public Cat(String name, int age, double maxJumpHeight) {
		super(name, age);
		this.maxJumpHeight = maxJumpHeight;
	}

	public double getMaxJumpHeight() {
		return maxJumpHeight;
	}

	public void setMaxJumpHeight(double maxJumpHeight) {
		this.maxJumpHeight = maxJumpHeight;
	}

	@Override
	public String toString() {
		return "Cat{" +
				"maxJumpHeight=" + maxJumpHeight +
				", id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", owner=" + owner +
				"} ";
	}
}
