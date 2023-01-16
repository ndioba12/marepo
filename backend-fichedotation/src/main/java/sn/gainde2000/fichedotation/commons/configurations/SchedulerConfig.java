/*
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/02/2022
 */

package sn.gainde2000.fichedotation.commons.configurations;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import sn.gainde2000.fichedotation.entities.other.FailedMail;
import sn.gainde2000.fichedotation.repositories.FailedMailRepository;
import sn.gainde2000.fichedotation.services.interfaces.shared.INotificationService;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.mails.MailInfosDTO;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.Executor;

@Configuration
//@ConditionalOnProperty(name="scheduler.enabled", matchIfMissing = true)
public class SchedulerConfig extends AsyncConfigurerSupport {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("send-mailer-");
        executor.initialize();
        return executor;
    }
}

@Service
@RequiredArgsConstructor
class RetryToSendFailedEmails {
    @Value("${smtp.server.host}")
    private String MAIL_HOST;

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryToSendFailedEmails.class);
    private final FailedMailRepository failedMailRepository;
    private final INotificationService notificationService;

    //methode pour tenter à nouveau d'envoyer les mails echoues stockes dans la bd
    //elle s execute tous les ${interval} defini dans le fichier de conf
    @Scheduled(fixedDelayString = "${interval}")
    @Async
    public void retryToSendFailedEmails() {
        try {
            if (Boolean.FALSE.equals(sendPingRequest())) {
                LOGGER.info("Désolé ! Nous n'arrivons pas à joindre l'hôte {}", MAIL_HOST);
            } else {
                LOGGER.info("L'hôte {} est joignable !", MAIL_HOST);
                List<FailedMail> failedMails = failedMailRepository.findAll();

                if (!failedMails.isEmpty()) {
                    LOGGER.info("Nouvelle tentative d'envoi des mails précédemment échoués !");
                    failedMails.forEach(failedMail -> {
                        try {
                            MailInfosDTO mailInfosDTO = MailInfosDTO.builder()
                                    .id(failedMail.getId())
                                    .subject(failedMail.getSubject())
                                    .originalText(failedMail.getText())
                                    .destinataire(failedMail.getEmail())
                                    .build();
                            notificationService.sendMessage(mailInfosDTO);
                        } catch (Exception e) {
                            LOGGER.error("Erreur in scheduler config: {}", e.getMessage());
                        }
                    });
                }
            }

        } catch (Exception e) {
            LOGGER.error("Exception lors de l'envoi des mails => {}", e.getMessage());
        }
    }

    //method pour pinger le serveur de mail (retourne true s'il est joignable)
    private Boolean sendPingRequest() throws IOException {
        InetAddress inetAddress = InetAddress.getByName(MAIL_HOST);
        LOGGER.info("Envoi d'une requête Ping à {}", MAIL_HOST);
        return inetAddress.isReachable(5000);
    }
}
