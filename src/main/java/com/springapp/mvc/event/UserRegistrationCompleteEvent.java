package com.springapp.mvc.event;

import com.springapp.mvc.model.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by VAHE on 13-Dec-16.
 */
public class UserRegistrationCompleteEvent extends ApplicationEvent {
    private final String appUrl;
    private final Locale locale;
    private final User user;

    public UserRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public String getAppUrl() {
        return appUrl;
    }
}