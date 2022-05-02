package pt.visualnuts.lucasalbertoni.exercises.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class CountryLanguage implements Serializable {
	
	private static final long serialVersionUID = -6078022762737107690L;
	
	@EmbeddedId
	private CountryLanguageID id;

	public CountryLanguageID getId() {
		return id;
	}
	public void setId(CountryLanguageID id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryLanguage other = (CountryLanguage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
