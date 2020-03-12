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
     * Define the gmailService
     * @param gmailService
     */
    public void setService(GmailService gmailService) {
        this.service = gmailService;
    }

    @RequestMapping("/email/labels")
    public String displayLabels(@RequestParam final String userKey) throws IOException, GeneralSecurityException {
        return service.getLabels(userKey);
    }

}
