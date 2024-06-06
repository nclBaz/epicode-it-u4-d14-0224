package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.AnimalsDAO;
import riccardogulin.entities.Cat;
import riccardogulin.entities.Dog;

public class Application {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d14");

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		AnimalsDAO ad = new AnimalsDAO(em);

		Cat tom = new Cat("Robert", 2, 20);
		Dog fido = new Dog("Rintintin", 5, 30);
/*		ad.save(tom);
		ad.save(fido);*/

		// ad.findAllAnimalsNames().forEach(System.out::println);


		// ad.findAnimalsByNameAndDelete("Tom");

		ad.findByOwnerName("Giovanni").forEach(System.out::println);
	}
}
