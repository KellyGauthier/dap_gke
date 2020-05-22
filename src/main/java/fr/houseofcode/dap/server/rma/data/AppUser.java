/**
 * 
 */
package fr.houseofcode.dap.server.rma.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//TODO GKE by Djer |Audit Code| Prends en comtpe les remarques de CheckStyle !

/**
 * @author lavio
 *
 */
@Entity
public class AppUser {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    //TODO GKE by Djer |POO| Attention à l'ordre recommandé, les getters/setters sont vers la fin de la classe. Ordre recommandé : Constantes, Attributs, initialisateurs statics, constructeurs, méthdoes métiers, méthodes utilitaires (toString,...) getters/setters.
    private String name;

}
