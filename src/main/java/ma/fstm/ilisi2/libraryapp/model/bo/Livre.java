package ma.fstm.ilisi2.libraryapp.model.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Livres")
public class Livre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private String titre;
    private String auteur;

    @OneToMany(mappedBy = "livre", fetch = FetchType.EAGER)
    private Set<Exemplaire> exemplaires;

    @Column(insertable = false, updatable = false)
    public int getNbrExemplaires() {
        return exemplaires.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Livre livre = (Livre) o;

        if (!Objects.equals(isbn, livre.isbn)) return false;
        if (!Objects.equals(titre, livre.titre)) return false;
        return Objects.equals(auteur, livre.auteur);
    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (titre != null ? titre.hashCode() : 0);
        result = 31 * result + (auteur != null ? auteur.hashCode() : 0);
        return result;
    }
}
