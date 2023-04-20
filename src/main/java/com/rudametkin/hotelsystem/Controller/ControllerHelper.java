package com.rudametkin.hotelsystem.Controller;

import com.rudametkin.hotelsystem.Controller.Commands.*;
import com.rudametkin.hotelsystem.Controller.Commands.PageCommands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerHelper {
    private static ControllerHelper instance = null;
    private Map<String, ICommand> commands;
    private ControllerHelper() {
        try {
            commands = new HashMap<>();

            commands.put("load-login", new LoginPage());
            commands.put("load-home", new HomePage());
            commands.put("load-cabinet", new CabinetPage());
            commands.put("load-error", new ErrorPage());

            commands.put("login", new Login());
            commands.put("signup", new Signup());
            commands.put("logout", new Logout());
            commands.put("load-search", new SearchPage());
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

    public ICommand getCommand(HttpServletRequest request) throws Exception {
        String command = request.getParameter("command");
        ICommand foundCommand;
        if(command != null) {
            foundCommand = commands.get(command);
            if(foundCommand != null)
                return foundCommand;
            else
                throw new Exception("There are no such command: " + command);
        } else {
            String requestUri = request.getRequestURI().substring(request.getContextPath().length() + 1) ;
            String loadCommandName = "load-" + requestUri;
            foundCommand = commands.get(loadCommandName);
            if(foundCommand != null)
                return commands.get(loadCommandName);
            else
                throw new Exception("There are no such uri page: " + requestUri);
        }
    }

}
