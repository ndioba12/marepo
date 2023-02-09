/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :23/12/2021
 */

package sn.gainde2000.fichedotation.services.implementations.shared;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sn.gainde2000.fichedotation.commons.utils.MailService;
import sn.gainde2000.fichedotation.security.jwt.JwtProvider;
import sn.gainde2000.fichedotation.services.interfaces.shared.INotificationService;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.LoginFormDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.mails.MailConnexionInfosDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.mails.MailInfosDTO;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private static final String RESET_PASSWORD_LINK = "reset-password";
    private static final String TOKEN_NAME_IN_MAIL_NOTIFICATION = "token";
    private final JwtProvider jwtProvider;
    private final MailService mailService;

    @Value("${app.url.front}")
    private String appUrlFront;

    @Value("${url.logo.starterkit}")
    private String urlLogoStarterKit;

    @Override
    public void createNewUserNotification(LoginFormDTO loginFormDTO, String action, Boolean isRegister) {
        String lien = getLinkWithToken(loginFormDTO, action);
        String textMessage = "";
        if (Boolean.TRUE.equals(isRegister))
            textMessage = "Bienvenue sur la plateforme de dematerialisation des fiches de dotation des materiels de GAINDE2000. Merci de cliquer " + "<a href=\"" + lien + "\" style=\"color: #3498DB;\">ici</a> pour activer votre compte.";
        else
            textMessage = "Votre compte utilisateur a été créé. Merci de cliquer " + "<a href=\"" + lien + "\" style=\"color: #3498DB;\">ici</a> pour activer votre compte.";

        MailInfosDTO mailInfosDTO = MailInfosDTO.builder()
                .subject(Boolean.TRUE.equals(isRegister) ? "Inscription" : "Création compte")
                .originalText(textMessage)
                .destinataire(loginFormDTO.getLogin())
                .build();
        sendMessage(mailInfosDTO);

    }

    @Override
    public void editUserNotification(LoginFormDTO loginFormDTO, String action) {
        String lien = getLinkWithToken(loginFormDTO, action);
        String textMessage = "Votre compte utilisateur a été modifié. Merci de cliquer " + "<a href=\"" + lien + "\" style=\"color: #3498DB;\">ici</a> pour l'activer à nouveau.";

        MailInfosDTO mailInfosDTO = MailInfosDTO.builder()
                .subject("Modification compte")
                .originalText(textMessage)
                .destinataire(loginFormDTO.getLogin())
                .build();
        sendMessage(mailInfosDTO);
    }

    @Override
    public void forgetPasswordNotification(LoginFormDTO loginFormDTO, String action) {
        String lien = getLinkWithToken(loginFormDTO, action);
        String textMessage = "Votre mot de passe a été réinitialisé avec succès. Merci de cliquer " + "<a href=\"" + lien + "\" style=\"color: #3498DB;\">ici</a> pour choisir un nouveau mot de passe.";

        MailInfosDTO mailInfosDTO = MailInfosDTO.builder()
                .subject("Réinitialisation mot de passe")
                .originalText(textMessage)
                .destinataire(loginFormDTO.getLogin())
                .build();
        sendMessage(mailInfosDTO);
    }
    @Override
    public void activationOrDeactivationOfAUser(String email, boolean action) {
        String objet;
        String textMessage;

        if (action) {
            objet = "Activation compte";
            textMessage = "Votre compte a été activé avec succès. Merci de cliquer " + "<a href=\"" + appUrlFront + "\" style=\"color: #3498DB;\">ici</a> pour vous connecter à votre compte.";
        } else {
            objet = "Désactivation compte";
            textMessage = "Votre compte a été désactivé.\n Veuillez contacter l'administrateur du système pour plus d'informations.";
        }

        MailInfosDTO mailInfosDTO = MailInfosDTO.builder()
                .subject(objet)
                .originalText(textMessage)
                .destinataire(email)
                .build();
        sendMessage(mailInfosDTO);
    }

    private String getLinkWithToken(LoginFormDTO loginFormDTO, String action) {
        MailConnexionInfosDTO infos = MailConnexionInfosDTO.builder().action(action).login(loginFormDTO.getLogin()).password(loginFormDTO.getPassword()).build();
        String mailToken = jwtProvider.generateJwtMailToken(infos);
        return appUrlFront + "/" + RESET_PASSWORD_LINK + "?" + TOKEN_NAME_IN_MAIL_NOTIFICATION + "=" + mailToken;
    }

    @Override
    public void sendMessage(MailInfosDTO mailInfosDTO) {
        mailInfosDTO.setText(getHtmlMessage(mailInfosDTO.getOriginalText()));
        try {
            mailService.sendASynchronousMail(mailInfosDTO);
        } catch (Exception e) {
            LOGGER.error("000000000000000000000000000000");
        }
    }

    private String getHtmlMessage(String text) {
        return "<!doctype html>\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "\n" +
                "<head>\n" +
                "  <title> Hello world </title>\n" +
                "  <!--[if !mso]><!-->\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <!--<![endif]-->\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <style type=\"text/css\">\n" +
                "    #outlook a {\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      -webkit-text-size-adjust: 100%;\n" +
                "      -ms-text-size-adjust: 100%;\n" +
                "    }\n" +
                "\n" +
                "    table,\n" +
                "    td {\n" +
                "      border-collapse: collapse;\n" +
                "      mso-table-lspace: 0pt;\n" +
                "      mso-table-rspace: 0pt;\n" +
                "    }\n" +
                "\n" +
                "    img {\n" +
                "      border: 0;\n" +
                "      height: auto;\n" +
                "      line-height: 100%;\n" +
                "      outline: none;\n" +
                "      text-decoration: none;\n" +
                "      -ms-interpolation-mode: bicubic;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "      display: block;\n" +
                "      margin: 13px 0;\n" +
                "    }\n" +
                "  </style>\n" +
                "  <!--[if mso]>\n" +
                "        <noscript>\n" +
                "        <xml>\n" +
                "        <o:OfficeDocumentSettings>\n" +
                "          <o:AllowPNG/>\n" +
                "          <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "        </o:OfficeDocumentSettings>\n" +
                "        </xml>\n" +
                "        </noscript>\n" +
                "        <![endif]-->\n" +
                "  <!--[if lte mso 11]>\n" +
                "        <style type=\"text/css\">\n" +
                "          .mj-outlook-group-fix { width:100% !important; }\n" +
                "        </style>\n" +
                "        <![endif]-->\n" +
                "  <!--[if !mso]><!-->\n" +
                "  <link href=\"https://fonts.googleapis.com/css?family=Roboto:300,500\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "  <style type=\"text/css\">\n" +
                "    @import url(https://fonts.googleapis.com/css?family=Roboto:300,500);\n" +
                "  </style>\n" +
                "  <!--<![endif]-->\n" +
                "  <style type=\"text/css\">\n" +
                "    @media only screen and (min-width:480px) {\n" +
                "      .mj-column-per-100 {\n" +
                "        width: 100% !important;\n" +
                "        max-width: 100%;\n" +
                "      }\n" +
                "\n" +
                "      .mj-column-per-45 {\n" +
                "        width: 45% !important;\n" +
                "        max-width: 45%;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "  <style media=\"screen and (min-width:480px)\">\n" +
                "    .moz-text-html .mj-column-per-100 {\n" +
                "      width: 100% !important;\n" +
                "      max-width: 100%;\n" +
                "    }\n" +
                "\n" +
                "    .moz-text-html .mj-column-per-45 {\n" +
                "      width: 45% !important;\n" +
                "      max-width: 45%;\n" +
                "    }\n" +
                "  </style>\n" +
                "  <style type=\"text/css\">\n" +
                "    @media only screen and (max-width:480px) {\n" +
                "      table.mj-full-width-mobile {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      td.mj-full-width-mobile {\n" +
                "        width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"word-spacing:normal;\">\n" +
                "  <div style=\"\">\n" +
                "    <!--[if mso | IE]></td></tr></table><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->\n" +
                "    <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">\n" +
                "              <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:600px;\" ><![endif]-->\n" +
                "              <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                  <tbody>\n" +
                "                    <tr>\n" +
                "                      <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                        <div style=\"font-family:Roboto, Helvetica, sans-serif;font-size:16px;font-weight:300;line-height:24px;text-align:left;\">\n" +
                "                          <p>Bonjour,</p>" +
                text +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody>\n" +
                "                </table>\n" +
                "              </div>\n" +
                "              <!--[if mso | IE]></td></tr></table><![endif]-->\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>\n" +
                "    <!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->\n" +
                "    <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">\n" +
                "              <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:600px;\" ><![endif]-->\n" +
                "              <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                  <tbody>\n" +
                "                    <tr>\n" +
                "                      <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td align=\"center\" style=\"width:550px;\">\n" +
                "<img alt height=\"auto\" width='auto' src=" + urlLogoStarterKit + " style=\"border:0;display:block;outline:none;text-decoration:none;font-size:13px;\" />" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody>\n" +
                "                </table>\n" +
                "              </div>\n" +
                "              <!--[if mso | IE]></td></tr></table><![endif]-->\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>\n" +
                "    <!--[if mso | IE]></td></tr></table><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->\n" +
                "    <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">\n" +
                "              <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr></tr></table><![endif]-->\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>\n" +
                "    <!--[if mso | IE]></td></tr></table><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->\n" +
                "    <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">\n" +
                "              <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:270px;\" ><![endif]-->\n" +
                "              <div class=\"mj-column-per-45 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                  <tbody>\n" +
                "                    <tr>\n" +
                "                      <td align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\">\n" +

                "                        <div style=\"font-family:Roboto, Helvetica, sans-serif;font-size:18px;font-weight:500;line-height:24px;text-align:center;color:blue;\">Fiche de dotation de materiel de GAINDE 2000</div>\n" +

                "                        <div style=\"font-family:Roboto, Helvetica, sans-serif;font-size:18px;font-weight:500;line-height:24px;text-align:center;color:blue;\">Gestion fiche de dotation et suivi des immobilisations</div>\n" +

                "                      </td>\n" +
                "                    </tr>\n" +
/*                "                    <tr>\n" +
                "                      <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                        <p style=\"border-top:solid 2px #616161;font-size:1px;margin:0px auto;width:100%;\">\n" +
                "                        </p>\n" +
                "                        <!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-top:solid 2px #616161;font-size:1px;margin:0px auto;width:220px;\" role=\"presentation\" width=\"220px\" ><tr><td style=\"height:0;line-height:0;\"> &nbsp;\n" +
                "</td></tr></table><![endif]-->\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                        <p style=\"border-top:solid 2px #616161;font-size:1px;margin:0px auto;width:45%;\">\n" +
                "                        </p>\n" +
                "                        <!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-top:solid 2px #616161;font-size:1px;margin:0px auto;width:99px;\" role=\"presentation\" width=\"99px\" ><tr><td style=\"height:0;line-height:0;\"> &nbsp;\n" +
                "</td></tr></table><![endif]-->\n" +
                "                      </td>\n" +
                "                    </tr>\n" +*/
                "                  </tbody>\n" +
                "                </table>\n" +
                "              </div>\n" +
                "              <!--[if mso | IE]></td></tr></table><![endif]-->\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>\n" +
                "    <!--[if mso | IE]></td></tr></table><![endif]-->\n" +
                "  </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
}

