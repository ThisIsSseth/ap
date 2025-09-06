package ap.exercises.librarysystem2;

import ap.exercises.librarysystem2.utils.AuthenticationService;

public class Controller {
    AuthenticationService authService = new AuthenticationService();


    public void startMenuDirector(int option) {
        switch (option) {
            case 1 -> authService.studentSignUp();
        }
    }
}
