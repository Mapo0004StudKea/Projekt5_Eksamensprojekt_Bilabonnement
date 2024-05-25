package dk.kea.projekt5_eksamensprojekt_bilabonnement.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is a custom error controller class
 *
 * @author Martin Poulsen, mapo0004@stud.kea.dk
 *
 * Is meant to handle most if not all errors that can come up, but it only shows the same message for all errors.
 * Therefor it can't specify what the error is unless you view the console.
 */

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("message", "Der opstod en fejl. prøv igen senere.");
        return "error";
    }
}
