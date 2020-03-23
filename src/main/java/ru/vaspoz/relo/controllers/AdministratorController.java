package ru.vaspoz.relo.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vaspoz.relo.exceptions.CountryNotFoundException;
import ru.vaspoz.relo.model.AliyahLog;
import ru.vaspoz.relo.model.CountryRateResponseGET;
import ru.vaspoz.relo.repository.UsersRepository;
import ru.vaspoz.relo.services.AliyahResponseService;
import ru.vaspoz.relo.services.LoggingService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdministratorController {

    private static Logger log = Logger.getLogger(AdministratorController.class);

    @Autowired
    private AliyahResponseService service;

    @Autowired
    private LoggingService loggingService;

    @Autowired
    private UsersRepository usersRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "Hello, World!";
    }

    @RequestMapping(value = "/users-count", method = RequestMethod.GET)
    public long getUsersCount() {
        return usersRepository.count();
    }

    @RequestMapping(value = "/get-logs/{numLines}", method = RequestMethod.GET)
    public List<AliyahLog> getLogs(@PathVariable Integer numLines) {
        return loggingService.getLogsLast(numLines);
    }

}
