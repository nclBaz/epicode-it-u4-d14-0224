package riccardogulin.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "owners")
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	protected String name;

	@OneToMany(mappedBy = "owner")
	protected List<Animal> animals;

	@Override
	public String toString() {
		return "Owner{" +
				"name='" + name + '\'' +
				", id=" + id +
				'}';
	}
}
