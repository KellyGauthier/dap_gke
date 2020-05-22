package fr.houseofcode.dap.server.rma.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

//TODO GKE by Djer |Audit Code| Prends en comtpe les remarques de CheckStyle ! 

/**
 * final class of CalendarService.
 */

@Service
public final class CalendarService {

    private static final Logger LOG = LogManager.getLogger();

    /**the internal APPLICATION_NAME.*/
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";

    //TODO GKE by Djer |POO| Tu n'as plus besoin de Singleton, @Service de Spring le fait pour toi. Tu peux supprimer ce constructeur, celui par defaut sera automatiquement crÈÈ pour toi par le compilateur.
    /** private constructor who respect Singleton Pattern. */
    private CalendarService() {
    }

    // ========================METHODE D'ACCES A CALENDAR=========================

    //TODO GKE by Djer |JavaDoc| Il n'est pas utile de repÈter le nom de la constante. "name of the application when query a Google Service" serait mieu.
    /**
     * constant APPLICATION_NAME.
     * @return constant APPLICATION_NAME
     */
    public static String getApplicationName() {
        return APPLICATION_NAME;
    }

    //TODO GKE by Djer |JavaDoc| Description fause, ne renvoie pas une liste, mais un "CalendarService" qui permet d'intÈroger les donnÈes d'un calendrier Google d'un utilisateur.
    /**
     * @return a list of service.
     * @throws GeneralSecurityException exception
     * @throws IOException exception
     */
    private static Calendar getCalendarService(String userKey) throws GeneralSecurityException, IOException {
        final NetHttpTransport hTTPTRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(hTTPTRANSPORT, Utils.getJsonFactory(),
                Utils.getCredentials(hTTPTRANSPORT, userKey)).setApplicationName(CalendarService.getApplicationName())
                        .build();
        return service;

    }

    /**
     * @return a String who contains the nextEvent
     * @throws IOException exception
     * @throws GeneralSecurityException exception
     */
    public String getNextEvent(String ukValue) throws IOException, GeneralSecurityException {
        //TODO GKE by Djer |POO| Ce commentaire est devenu faux !
        // Build a new authorized API client service.

        //TODO GKE by Djer |IDE| (encdage) Attention l'encodage de tes ficheirs Java etait (est?) en ISO8859-2 au lieu d'UTF-8 (dans Eclipse Help->Perform setup Task...)
        LOG.debug(
                "recuperation du prochain avec d√©clenchement possible d'exceptions (IOException ou GeneralSecurityException)");

        LOG.info("r√©cup√©ration du prochain √©v√®nement pour l'utilisateur :" + ukValue);

        String str = "No upcoming events found.";
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = getCalendarService(ukValue).events().list("primary").setMaxResults(1).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();
        List<Event> items = events.getItems();
        if (!items.isEmpty()) {
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                DateTime end = event.getEnd().getDateTime();

                if (start == null) {
                    start = event.getStart().getDate();
                }
                if (end == null) {
                    end = event.getEnd().getDate();
                }
                //TODO GKE by Djer |Rest API| Dans une API (serveur) Evite de formater les messages, renvoie plutot une Liste (de String). Laisse le client (ou Thymeleaf) effectuer la prÈsentation.
                str = "Evenement √† venir =" + " " + event.getSummary() + " pour le : " + start + ", se terminant le : "
                        + end;
            }

        }

        //TODO GKE by Djer |Log4J| Contextualise tes messages de log " ... for userKey : " + ukValue".
        LOG.info("Prochain √©v√®nement : " + str);
        return str;
    }

}
