package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Animal;
import riccardogulin.entities.Cat;
import riccardogulin.entities.Dog;
import riccardogulin.exceptions.NotFoundException;

public class AnimalsDAO {
	private EntityManager em;

	public AnimalsDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Animal animal) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(animal);
		transaction.commit();
		System.out.println("Animale " + animal.getName() + " salvato correttamente");
	}

	public Animal findById(long animalId) {
		Animal animal = em.find(Animal.class, animalId);
		if (animal == null) throw new NotFoundException(animalId);
		return animal;
	}

	public Cat findCatById(long animalId) {
		Cat animal = em.find(Cat.class, animalId);
		if (animal == null) throw new NotFoundException(animalId);
		return animal;
	}

	public Dog findDogById(long animalId) {
		Dog animal = em.find(Dog.class, animalId);
		if (animal == null) throw new NotFoundException(animalId);
		return animal;
	}
}
