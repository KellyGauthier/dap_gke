/**
 * 
 */
package fr.houseofcode.dap.server.rma.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

//TODO GKE by Djer |Audit Code| Prends en comtpe les remarques de CheckStyle !

/**
 * @author lavio
 *
 */
public interface GmailService {

    /**
     * Renvoie le nombre d'email non lus dan sla boite principale.
     * @param userId String return the user
     * @param query String
     * @return number of unread email
     * @throws IOException exception
     * @throws GeneralSecurityException exception
     */
    int getNbUnreadEmail(String ukValue) throws IOException, GeneralSecurityException;

    String getLabels(String ukValue) throws IOException, GeneralSecurityException;
}
