package fr.houseofcode.dap.server.rma.google;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;

//TODO GKE by Djer |Audit Code| Prends en comtpe les remarques de CheckStyle ! 

/**
 * @author adminHOC.
 *
 */
public class Utils {
    //TODO GKE by Djer |JavaDoc| Cette javadoc ne document plus rien !
    /**constant PORT. */

    /** the default JSON_FACTORY.*/
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    //TODO GKE by Djer |JavaDoc| Pas utile de repete le no mde la constante. "Folder to store user credential" serait mieux.
    /**
     * constant TOKENS DIRECTORY PATH.
     */
    private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + File.separator + "dap"
            + File.separator + "tokens";

    /**
     * method getJsonFactory().
     * @return constant JSON_FACTORY
     */
    public static JsonFactory getJsonFactory() {
        return JSON_FACTORY;
    }

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = new ArrayList<String>();

    //TODO GKE by Djer |JavaDoc| Pas utile de repete le no mde la constante. "File path contening Google app Credentials" serait mieux.
    /**
     * String CREDENTIALS_FILE_PATH.
     */
    private static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + File.separator + "dap"
            + File.separator + "credentials.json";

    /**
     * load client secret.
     * @param hTTPtRANSPORT transport.
     * @return client secret
     * @throws IOException exception
     */
    static Credential getCredentials(final NetHttpTransport hTTPtRANSPORT, String userKey) throws IOException {

        //LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(PORT).build();
        //return new AuthorizationCodeInstalledApp(getFlow(hTTPtRANSPORT), receiver).authorize("user");

        GoogleAuthorizationCodeFlow flow = getFlow(hTTPtRANSPORT);
        return flow.loadCredential(userKey);
    }

    public static GoogleAuthorizationCodeFlow getFlow(final NetHttpTransport hTTPtRANSPORT) throws IOException {
        //TODO GKE by Djer |POO| Les données dans liste seront en doublons, a chaque passage dnas la méthode. Cré plutot un constructeur pour ajouter les "scopes" à demander à l'utilisateur, comme cela la liste des scope ne conteindra pas de doublons.
        SCOPES.add(CalendarScopes.CALENDAR_READONLY);
        SCOPES.add(GmailScopes.GMAIL_READONLY);

        //        InputStream in = GmailService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        //        if (in == null) {
        //            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        //        }
        File fic = new java.io.File(CREDENTIALS_FILE_PATH);

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(new FileInputStream(fic), Charset.forName("UTF-8")));

        // Build flow and trigger user authorization request.

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(hTTPtRANSPORT, JSON_FACTORY,
                clientSecrets, SCOPES)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                        .setAccessType("offline").build();
        return flow;
    }

}
