package bg.finalexam.crazydesignsco.service;

import java.util.Locale;

public interface EmailService {

    void sendRegistrationEmail(String userEmail, String userName, Locale preferredLocale);

}
