/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :23/12/2021
 */

package sn.gainde2000.fichedotation.commons.utils;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.fichedotation.entities.other.FailedMail;
import sn.gainde2000.fichedotation.repositories.FailedMailRepository;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.mails.MailInfosDTO;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${smtp.server.host}")
    private String MAIL_HOST;

    @Value("${smtp.server.from}")
    private String FROM;

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
    private final FailedMailRepository failedMailRepository;

    /**
     * this statement create a thread pool of twenty threads
     * here we are assigning send mail task using ScheduledExecutorService.submit();
     */

    @Transactional
    @Async
    public void sendASynchronousMail(MailInfosDTO mailInfosDTO) {
        LOGGER.debug("Envoi mail en cours d'initialisation !");

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", MAIL_HOST);

            Session session = Session.getInstance(props);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailInfosDTO.getDestinataire()));
            msg.setSubject(mailInfosDTO.getSubject());
            msg.setText(mailInfosDTO.getText(), "utf-8", "html");

            Transport.send(msg);
            manageFailedMailInDatabase(mailInfosDTO, "DELETE");
        } catch (Exception e) {
            LOGGER.info("Erreur lors de l'envoi du mail à {} !", mailInfosDTO.getDestinataire());
            manageFailedMailInDatabase(mailInfosDTO, "CREATE_OR_EDIT");
        }
    }

    private void manageFailedMailInDatabase(MailInfosDTO mailInfosDTO, String action) {
        LOGGER.info("Mise à jour de la table FailedEmail en cours !");
        try {
            if (action.equalsIgnoreCase("CREATE_OR_EDIT")) {
                FailedMail failedMail = FailedMail.builder()
                        .email(mailInfosDTO.getDestinataire())
                        .subject(mailInfosDTO.getSubject())
                        .text(mailInfosDTO.getOriginalText())
                        .createdDate(new Date())
                        .isSent(false)
                        .build();
                if (Objects.nonNull(mailInfosDTO.getId()))
                    failedMail = failedMailRepository.findById(mailInfosDTO.getId()).orElse(failedMail);

                failedMailRepository.save(failedMail);
            } else if (action.equalsIgnoreCase("DELETE") && Objects.nonNull(mailInfosDTO.getId()))
                failedMailRepository.deleteById(mailInfosDTO.getId());

        } catch (Exception exception) {
            LOGGER.error("Echec insertion dans la base de données: ");
        }
        LOGGER.info("La mise à jour a été faite avec succès ! => {}", mailInfosDTO.getDestinataire());
    }
}
