/**
 * 
 */
package fr.houseofcode.dap.server.rma;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.rma.google.GmailService;

//TODO GKE by Djer |Audit Code| Prends en comtpe les remarques de CheckStyle !

/**
 * @author lavio
 *
 */
@RestController
public class EmailController {
    @Autowired
    private GmailService service;

    @RequestMapping("/email/nbunread")
    public Integer displayNbUnreadEmail(@RequestParam final String userKey)
            throws IOException, GeneralSecurityException {
        return service.getNbUnreadEmail(userKey);
    }

    /**
     * Define the gmailService.
     * @param gmailService
     */
    //TODO GKE by Djer |POO| Attention � l'ordre recommand�, les attributs devraient �tre vers le d�but de la classe. Ordre recommand� : Constantes, Attributs, initialisateurs statics, constructeurs, m�thdoes m�tiers, m�thodes utilitaires (toString,...) getters/setters.
    public void setService(GmailService gmailService) {
        this.service = gmailService;
    }

    @RequestMapping("/email/labels")
    public String displayLabels(@RequestParam final String userKey) throws IOException, GeneralSecurityException {
        return service.getLabels(userKey);
    }

}
