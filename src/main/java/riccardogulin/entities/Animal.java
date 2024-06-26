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

JOINED è la strategia che genera tabelle per la classe padre e per le figlie, nel nostro caso quindi un totale di 3 tabelle, una per gli attributi
comuni, due per gli attributi specifici di Cat e Dog. Questo rende la struttura del db più 'pulita' e controllata (nel senso che posso inserire dei vincoli
di non-nullness sulle colonne), di contro però le operazioni di lettura dei dati potrebbero richiedere dei JOIN (i quali hanno un costo). Da preferire
rispetto a prima quando le classi figlie hanno tanti attributi diversi tra di loro e pochi in comune.

TABLE PER CLASS, detta anche table per CONCRETE class, è la strategia che crea una tabella per ogni classe concreta. Se la classe padre è astratta non avrà
una tabella di riferimento e viceversa se dovesse essere concreta anch'essa avrà una tabella. Questa strategia può portare ad avere una buona pulizia della
struttura delle tabelle però ha degli svantaggi nelle performance delle queries polimorfiche

* */
@NamedQuery(name = "findByName", query = "SELECT a FROM Animal a WHERE a.name = :name")
// Nelle query JPQL posso segnare dei dati parametrici con i due punti :
// Le named query sono query associate ad un'entità alle quali associo un nome che poi potrò richiamare quante volte voglio
public abstract class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	protected String name;
	protected int age;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	protected Owner owner;

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
