package healthnutrition.healthnutrition.web.AdminController;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView.addObject("405");
    }

}
