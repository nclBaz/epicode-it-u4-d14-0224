package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import riccardogulin.entities.Animal;
import riccardogulin.entities.Cat;
import riccardogulin.entities.Dog;
import riccardogulin.exceptions.NotFoundException;

import java.util.List;

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

	public List<Animal> findAll() {
		TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
		// Nel caso della single table equivale a fare SELECT * FROM animals
		// Nel caso della joined dovrebbe fare anche i join con le altre tabelle
		// Nel caso della table per class dovrebbe fare SELECT * FROM dogs, SELECT * FROM cats e fare un'unione di tutti i risultati
		return query.getResultList();
	}

	public List<Dog> findAllDogs() {
		TypedQuery<Dog> query = em.createQuery("SELECT d FROM Dog d", Dog.class); // SELECT * FROM animals WHERE tipo_animale = 'Cane' (Per la single table)
		return query.getResultList();
	}

	public List<Cat> findAllCats() {
		TypedQuery<Cat> query = em.createQuery("SELECT c FROM Cat c", Cat.class); // SELECT * FROM animals WHERE tipo_animale = 'Gatto' (Per la single table)
		return query.getResultList();
	}
}
