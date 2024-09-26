package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.atteo.evo.inflector.English;
import org.springframework.data.domain.Persistable;
import org.springframework.util.StringUtils;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@MappedSuperclass
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="uri")
public abstract class UriEntity<ID extends Serializable> implements Persistable<ID> {

    /**
     * The uri string value.
     */
    private String uri;
    /**
     * The value of the entity version.
     */
    @Version
    private Long version;

    /**
     *  Method that builds the uri and returns it in string format.
     * @return Uri with uncapitalize letters in plural with name and id of the entity
     */

    public String getUri() {
        return "/" + English.plural(StringUtils.uncapitalize(this.getClass().getSimpleName())) + "/" + getId();
    }


    /**
     * Method that checks if the version is null or not and returns it in boolean format.
     * @return a boolean value if the version of the entity is null or not.
     */
    @Override
    @JsonIgnore
    public boolean isNew() {
        return version == null;
    }
}
