/**
 * 
 */
package fr.houseofcode.dap.server.rma;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Assert;
import org.junit.Test;

import fr.houseofcode.dap.server.rma.google.GmailServiceImpl;

/**
 * @author lavio
 *
 */
public class EmailControllerIT {

    @Test
    public void testDisplayNbUnreadEmail() throws IOException, GeneralSecurityException {
        //Init data
        EmailController ec = new EmailController();
        ec.setService(new GmailServiceImpl());

        //Appel de la méthode
        Integer result = ec.displayNbUnreadEmail("Kelly");

        //COntrôle de l'exécution de la méthode
        Assert.assertNotNull("Nombre d'email non présent", result);
    }

}
