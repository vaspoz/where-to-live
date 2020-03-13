package ru.vaspoz.relo.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vaspoz.relo.auth.AuthContext;
import ru.vaspoz.relo.exceptions.CountryNotFoundException;
import ru.vaspoz.relo.model.*;
import ru.vaspoz.relo.repository.CitiesRepository;
import ru.vaspoz.relo.repository.CountriesRepository;
import ru.vaspoz.relo.repository.LogsRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class LoggingService {

    private static Logger log = Logger.getLogger(LoggingService.class);

    @Autowired
    private LogsRepository logsRepository;

    private void writeLog(
            String username,
            String action,
            String basecounty,
            String basecity,
            String comparecountry,
            String comments
    ) {

        AliyahLog newLogEntry = new AliyahLog();
        newLogEntry.setUsername(username);
        newLogEntry.setAction(action);
        newLogEntry.setBasecountry(basecounty);
        newLogEntry.setBasecity(basecity);
        newLogEntry.setComparecountry(comparecountry);
        newLogEntry.setComments(comments);
        newLogEntry.setCreatedatetime(new Date());

        logsRepository.save(newLogEntry);

    }

    public void writeUserLogin(String username) {
        writeLog(username, LogActions.LOGIN, null, null, null, "User logged in");
    }

    public void writeUserIncorrectLogin(String username) {
        writeLog(username, LogActions.LOGIN_FAILED, null, null, null, "Incorrect credentials");
    }

    public void newUserSugnedUp(String username) {
        writeLog(username, LogActions.SIGNUP, null, null, null, "New user registered");
    }

    public void compareCountries(String basecountry, String basecity, String comparecountry) {
        writeLog(AuthContext.currentUser(), LogActions.COMPARE, basecountry, basecity, comparecountry, "Comparison requested");
    }
}