/**
 * 
 */
package fr.houseofcode.dap.server.rma;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lavio
 *
 */
public class EmailControllerTest {

    @Test
    public void testDisplayNbUnreadEmail() throws IOException, GeneralSecurityException {
        //Init data
        EmailController ec = new EmailController();
        ec.setService(new GmailServiceMock());

        //Appel de la méthode
        Integer result = ec.displayNbUnreadEmail("KellyG");

        //COntrôle de l'exécution de la méthode
        Integer expectedNbEmail = 12;
        Assert.assertNotNull("Nombre d'email non présent", result);
        Assert.assertEquals(expectedNbEmail, result);
    }

}
