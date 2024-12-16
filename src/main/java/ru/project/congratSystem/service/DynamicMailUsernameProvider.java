package ru.project.congratSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.project.congratSystem.security.UsersDetails;


public class DynamicMailUsernameProvider implements EnvironmentAware {

    private Environment environment;
    private UsersDetails usersDetails;


    public DynamicMailUsernameProvider(Environment environment, UsersDetails usersDetails) {
        this.environment = environment;
        this.usersDetails = usersDetails;
    }

    public String getDynamicMailUsername() {
        // Здесь вы можете получить email из сущности User, например, из базы данных
        // Предположим, у вас есть сервис UserService, который может получить email пользователя
        return usersDetails.getEmail();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
