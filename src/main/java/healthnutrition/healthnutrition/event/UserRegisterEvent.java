package healthnutrition.healthnutrition.event;

import org.springframework.context.ApplicationEvent;

public class UserRegisterEvent extends ApplicationEvent {
    private final String userEmail;
    private final String fullName;

    public UserRegisterEvent(Object source, String userEmail, String fullName) {
        super(source);
        this.userEmail = userEmail;
        this.fullName = fullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getFullName() {
        return fullName;
    }
}
