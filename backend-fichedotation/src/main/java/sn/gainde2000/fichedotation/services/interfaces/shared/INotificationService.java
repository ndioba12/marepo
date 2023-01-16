package sn.gainde2000.fichedotation.services.interfaces.shared;

import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.LoginFormDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.mails.MailInfosDTO;


public interface INotificationService {
    void createNewUserNotification(LoginFormDTO loginFormDTO, String action, Boolean isRegister);
    void editUserNotification(LoginFormDTO loginFormDTO, String action);
    void forgetPasswordNotification(LoginFormDTO loginFormDTO, String action);
    void activationOrDeactivationOfAUser(String email, boolean action);
    void sendMessage(MailInfosDTO mailInfosDTO);
}
