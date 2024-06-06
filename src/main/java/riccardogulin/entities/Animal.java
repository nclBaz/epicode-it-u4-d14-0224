package riccardogulin.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "animals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_animale") // <-- serve per rinominare la colonna discriminante (DTYPE)
/*
SINGLE TABLE è la strategia che ci genera un'unica tabella contenente tutti gli animali (Cat e Dog). La comodità è quella di avere una singola tabella,
il che è più gestibile e più performante, di contro però ci ritroveremo ad avere probabilmente parecchi null sparsi per la tabella (quindi ciò significa
che non potrò neanche mettere dei vincoli per verificare che una data colonna non contenga valori null)
* */
public abstract class Animal {
	@Id
	@GeneratedValue
	protected long id;
	protected String name;
	protected int age;

	public Animal() {
	}

	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
