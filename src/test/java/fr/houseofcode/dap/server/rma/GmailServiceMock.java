/**
 * 
 */
package fr.houseofcode.dap.server.rma;

import java.io.IOException;
import java.security.GeneralSecurityException;

import fr.houseofcode.dap.server.rma.google.GmailService;

/**
 * @author lavio
 *
 */
public class GmailServiceMock implements GmailService {

    /**
     * MOCK
     * @param userId String return the user
     * @param query String
     * @return number of unread email
     * @throws IOException exception
     * @throws GeneralSecurityException exception
     */

    @Override
    public int getNbUnreadEmail(String ukValue) throws IOException, GeneralSecurityException {

        return 12;

    }

    @Override
    public String getLabels(String ukValue) throws IOException, GeneralSecurityException {

        return "Bouchon, Liste, Labels";
    }
}
