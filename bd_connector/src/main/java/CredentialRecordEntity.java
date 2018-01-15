import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entity-Classes need to add in the HibernateUtil using configuration.addAnnotatedClass()
 */

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "credentialrecord", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CredentialRecordEntity implements DataInterface {
    @Id
    @Column(name = "id", unique=true, nullable = false)
    private int id;

    @Column(name = "data")
    private String data;

    public CredentialRecordEntity(){
        this.id = 1000;
    }
}
