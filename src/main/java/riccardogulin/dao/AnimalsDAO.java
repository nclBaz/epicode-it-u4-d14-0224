package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
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

	public List<String> findAllAnimalsNames() {
		TypedQuery<String> query = em.createQuery("SELECT a.name FROM Animal a", String.class);
		return query.getResultList();
	}

	public List<Animal> findByName(String name) {
		// 1. Richiamo la named query "findByName" definita nell'entità Animal
		TypedQuery<Animal> query = em.createNamedQuery("findByName", Animal.class);

		// 2. Passo un valore per il parametro name della query
		query.setParameter("name", name);

		// 3. Eseguo la query e ritorno il risultato
		return query.getResultList();
	}

	public void findAnimalsByNameAndDelete(String name) {
		// Quando si parla di operazioni che vanno a modificare il db come save,update,delete,ecc devo usare le transazioni
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Query deleteQuery = em.createQuery("DELETE FROM Animal a WHERE a.name = :name"); // DELETE FROM animals WHERE name = 'nomedacancellare'
		deleteQuery.setParameter("name", name);
		int numeroCancellati = deleteQuery.executeUpdate();

		transaction.commit();
		System.out.println(numeroCancellati + " animali di nome " + name + " cancellati correttamente");
	}

	public void findAnimalsByNameAndUpdateName(String oldName, String newName) {
		// Quando si parla di operazioni che vanno a modificare il db come save,update,delete,ecc devo usare le transazioni
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Query deleteQuery = em.createQuery("UPDATE FROM Animal a SET a.name = :newName WHERE a.name = :oldName"); // UPDATE FROM animals SET name = 'nomenuovo' WHERE name = 'nomevecchio'
		deleteQuery.setParameter("oldName", oldName);
		deleteQuery.setParameter("newName", newName);
		int numeroModificati = deleteQuery.executeUpdate();

		transaction.commit();
		System.out.println(numeroModificati + " animali di nome " + oldName + " rinominati correttamente in " + newName);
	}

	public List<Animal> findByNameStartsWith(String partialName) {
		TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE LOWER(a.name) LIKE LOWER(:partialName)", Animal.class);
		// Siccome ILIKE non è compatibile con tutti i db relazionali, meglio non usarlo e usare lo stratagemma di qua sopra
		query.setParameter("partialName", partialName + "%");
		return query.getResultList();
	}

	public List<Animal> findByOwnerName(String ownerName) {
		TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.owner.name = :ownerName", Animal.class);
		query.setParameter("ownerName", ownerName);
		return query.getResultList();
	}

	// JPQL Operators --> https://www.objectdb.com/java/jpa/query/jpql/structure


}
