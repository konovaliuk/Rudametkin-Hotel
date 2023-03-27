package com.rudametkin.hotelsystem.controller;

import com.rudametkin.hotelsystem.commands.*;

import java.util.HashMap;
import java.util.Map;

public class ControllerHelper {
    private static ControllerHelper instance = null;
    private final Map<String, ICommand> commands;
    private ControllerHelper() {
        commands = new HashMap<>();

        commands.put(null, new RedirectPage("/pages/main.jsp"));
        commands.put("redirect-login-form", new RedirectPage("/pages/login.jsp"));
        commands.put("redirect-home-page", commands.get(null));
        commands.put("redirect-error", new RedirectPage("/pages/error.jsp"));
        commands.put("redirect-cabinet-page", new Cabinet());
        commands.put("login", new Login());
        commands.put("logout", new Logout());
    }

    public static ControllerHelper getInstance() {
        if(instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }

    public ICommand getCommand(String commandName) {
        return commands.get(commandName);
    }
}
