package healthnutrition.healthnutrition.web.Error;
import healthnutrition.healthnutrition.Exception.DatabaseException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleObjectNotFound(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("500");
        modelAndView.addObject("objectId", exception.getMessage());
        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFound(ObjectNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("objectId", exception.getMessage());
        return modelAndView;
    }
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DatabaseException.class)
    public ModelAndView handlerDatabase(DatabaseException exception) {
        ModelAndView modelAndView = new ModelAndView("500");
        modelAndView.addObject("objectId", exception.getMessage());
        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView handlerDatabase(UsernameNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("objectId", exception.getMessage());
        return modelAndView;
    }


}
