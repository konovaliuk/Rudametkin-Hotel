package com.rudametkin.hotelsystem.controller;

import com.rudametkin.hotelsystem.commands.*;
import com.rudametkin.hotelsystem.commands.Error;
import com.rudametkin.hotelsystem.configs.Config;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerHelper {
    private static ControllerHelper instance = null;
    private Map<String, ICommand> commands;
    private ControllerHelper() {
        try {
            commands = new HashMap<>();

            commands.put(null, new HomePage());
            commands.put("redirect-login-form", new LoginForm());
            commands.put("redirect-home-page", commands.get(null));
            commands.put("handle-error", new Error());
            commands.put("redirect-cabinet-page", new Cabinet());
            commands.put("login", new Login());
            commands.put("logout", new Logout());
            commands.put("start-search", new StartSearching());
            commands.put("prev-search-page", new PrevSearchPage());
            commands.put("next-search-page", new NextSearchPage());

        } catch (Exception e) {
            commands = null;
        }
    }

    public static ControllerHelper getInstance() {
        if(instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }

    public ICommand getCommand(String command) {
        return commands.get(command);
    }
}
