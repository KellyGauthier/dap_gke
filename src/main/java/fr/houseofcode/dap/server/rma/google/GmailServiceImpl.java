package fr.houseofcode.dap.server.rma.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

//TODO GKE by Djer |Audit Code| Prends en comtpe les remarques de CheckStyle ! 

/**
 * class GmailService.
 * @author lavio
 */

@Service
public final class GmailServiceImpl implements GmailService {

    private static final Logger LOG = LogManager.getLogger();

    /**
     * @return the internal APPLICATION_NAME.
     */
    private static final String APPLICATION_NAME = "Gmail API Java Quickstart";

    // ========================METHODE D'ACCES A GMAIL=========================

    //TODO GKE by Djer |POO| On accede en gÈnÈrale au constantes privÈ directement sans getter/setter. Cette constante n'est pas utilisÈ dans une autre classe il n'est donc pas utile de l'exposer avec ce getter.
    /**
     * @return access to constant APPLICATION_NAME.
     */
    public static String getApplicationName() {
        return APPLICATION_NAME;
    }

    /**
     * secure connection to GmailService.
     * @return a instance of Gmail.
     * @throws GeneralSecurityException exception
     * @throws IOException exception
     */
    private Gmail getGmailService(String userKey) throws GeneralSecurityException, IOException {
        final NetHttpTransport hTTPTRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(hTTPTRANSPORT, Utils.getJsonFactory(),
                Utils.getCredentials(hTTPTRANSPORT, userKey)).setApplicationName(GmailServiceImpl.getApplicationName())
                        .build();
        return service;
    }

    // ========================METHODE LABEL=========================
    /**
     * @param userId type String
     * @return String containing list of labels
     * @throws IOException exception
     * @throws GeneralSecurityException exception
     */

    @Override
    public String getLabels(String ukValue) throws IOException, GeneralSecurityException {
        //TODO GKE by Djer |IDE| (encdage) Attention l'encodage de tes fichiers Java etait (est?) en ISO8859-2 au lieu d'UTF-8 (dans Eclipse Help->Perform setup Task...)
        //TODO GKE by Djer |Log4J| Contextualise tes messages de log " ... for userKey : " + ukValue".
        LOG.debug(
                "recuperation des labels avec d√©clenchement possible d'exceptions (IOException ou GeneralSecurityException)");

        String str = "";
        ListLabelsResponse listResponse = getGmailService(ukValue).users().labels().list("me").execute();
        List<Label> labels = listResponse.getLabels();

        if (labels.isEmpty()) {
            str = "No Labels";

        } else {

            for (Label label : labels) {
                //TODO GKE by Djer |Rest API| Dans une API (serveur) Evite de formater les messages, renvoie plutot une Liste (de String). Laisse le client (ou Thymeleaf) effectuer la prÈsentation.
                str += label.getName() + "\n";

            }
        }
        //TODO GKE by Djer |log4J| Contextualise tes messages ("... for user : " + ukValue). 
        LOG.info("nombre de labels gmail : " + labels.size());
        return str;
    }

    /**
     * Renvoie le nombre d'email non lus dan sla boite principale 
     * @param userId String return the user
     * @param query String
     * @return number of unread email
     * @throws IOException exception
     * @throws GeneralSecurityException exception
     */

    @Override
    public int getNbUnreadEmail(String ukValue) throws IOException, GeneralSecurityException {
        LOG.debug(
                "recuperation des labels avec d√©clenchement possible d'exceptions (IOException ou GeneralSecurityException)");

        LOG.info("R√©cup√©ration du nombre d'email pour l'utilisateur : " + ukValue);

        ListMessagesResponse response = getGmailService(ukValue).users().messages().list("me")
                .setQ("is:unread in:inbox").execute();

        List<Message> messages = new ArrayList<Message>();
        while (response.getMessages() != null) {

            messages.addAll(response.getMessages());

            if (response.getNextPageToken() != null) {

                String pageToken = response.getNextPageToken();
                response = getGmailService(ukValue).users().messages().list("me").setQ("is:unread")
                        .setPageToken(pageToken).execute();

            } else {
                //TODO GKE by Djer |POO| Pas trËs utile, tu "break le while" s'il n'y a plus de "page suivante", hors "avoir une page suivante" est justement la condition pour entrer dans le while...
                break;
            }
        }

        int number = messages.size();
        //TODO GKE by Djer |log4J| Contextualise tes messages ("... for user : " + ukValue).
        LOG.info("Nombre d'email non lus : " + number);
        return number;

    }

}
