package ma.fstm.ilisi2.libraryapp.model.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Adherents")
public class Adherent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String prenomAdherent;
    private String nomAdherent;

    @OneToMany(mappedBy = "adherent", fetch = FetchType.LAZY)
    private Set<Emprunt> empruntsByid;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adherent adherent = (Adherent) o;

        if (!Objects.equals(id, adherent.id)) return false;
        if (!Objects.equals(prenomAdherent, adherent.prenomAdherent))
            return false;
        return Objects.equals(nomAdherent, adherent.nomAdherent);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (prenomAdherent != null ? prenomAdherent.hashCode() : 0);
        result = 31 * result + (nomAdherent != null ? nomAdherent.hashCode() : 0);
        return result;
    }
}
