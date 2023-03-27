package ma.fstm.ilisi2.libraryapp.model.bo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "Emprunts")
public class Emprunt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherent")
    @ToString.Exclude
    private Adherent adherent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exemplaire")
    @ToString.Exclude
    private Exemplaire exemplaire;

    @Column(name = "date_emprunt", nullable = false, updatable = false)
    private Date dateEmprunt;

    @Column(name = "date_retour", nullable = false)
    private Date dateRetour;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprunt emprunt = (Emprunt) o;
        return Objects.equals(id, emprunt.id) && Objects.equals(adherent, emprunt.adherent) && Objects.equals(dateEmprunt, emprunt.dateEmprunt) && Objects.equals(exemplaire, emprunt.exemplaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adherent, dateEmprunt, exemplaire);
    }
}
