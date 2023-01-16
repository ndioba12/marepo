/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/02/2022
 */

package sn.gainde2000.fichedotation.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;

import javax.naming.AuthenticationException;
import javax.naming.SizeLimitExceededException;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /* pour le traitement des fichiers */
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public Response<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        return Response.unauthorized().setMessage("Vous avez dépassé la taille maximale autorisée !").setErrors(ex.getMessage());
    }

    @ExceptionHandler(value = SizeLimitExceededException.class)
    public Response<Object> handleSizeLimitExceededException(SizeLimitExceededException ex) {
        return Response.unauthorized().setMessage("Vous avez dépassé la taille maximale autorisée par requête !").setErrors(ex.getMessage());
    }

    /* fin pour le traitement des fichiers */

    //pour les accès non autorisés
    @ExceptionHandler(value = InterruptedException.class)
    public void handleInterruptedException(InterruptedException ex) {
        System.out.println("Le mail n'a pas été envoyé !");
    }

    //pour les accès à des ressources (points terminaux des controleurs) non autorisés
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Response<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return Response.accessDenied().setMessage("Vous n'avez pas la permission d'accéder à cette ressource !").setErrors(ex.getMessage());
    }

    //pour les accès non autorisés
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Response<Object> handleAuthenticationException(AuthenticationException ex) {
        return Response.accessDenied().setMessage("Vous n'avez pas la permission d'accéder à cette ressource !").setErrors(ex.getMessage());
    }

    //pour les accès non autorisés
    @ExceptionHandler(value = BadCredentialsException.class)
    public Response<Object> handleBadCredentialsException(BadCredentialsException ex) {
        return Response.wrongCredentials().setMessage("Login ou mot de passe incorrect !").setErrors(ex.getMessage());
    }

    //pour les contraintes de validations
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return Response.badRequest().setMessage("Violation des contraintes de validations !").setErrors(errors);
    }

    //pour le blockage du nombre de tentatives
    @ExceptionHandler(value = InternalAuthenticationServiceException.class)
    public Response<Object> handleAuthenticationServiceException(InternalAuthenticationServiceException ex) {
        return Response.badRequest().setMessage(ex.getMessage()).setErrors(ex.getMessage());
    }

    //exception par défaut
    @ExceptionHandler(Exception.class)
    public Response<Object> exceptionHandler(Exception exception) {
        exception.printStackTrace();
        return Response.badRequest().setMessage("Une erreur inconnue est survenue lors du traitement !").setErrors(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Response<Object> handleViolationException(Exception ex) {
        return Response.exception().setMessage("Violation des contraintes de validations !").setErrors(ex.getMessage());
    }

    @ExceptionHandler(GenericApiException.class)
    public Response<Object> handleGenericApiException(GenericApiException exception) {
        return Response.exception().setMessage(exception.getMessage()).setErrors(exception.getMessage());
    }

    @ExceptionHandler(ParseException.class)
    public Response<Object> handleParseExceptionHandler(ParseException exception) {
        return Response.exception().setMessage("Le format de date fournit n'est pas pris en compte !").setMessage(exception.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public Response handleIOExceptionHandler(IOException exception) {
        return Response.directoryNotFound().setMessage("Échec!, impossible de stocker le (s) fichier (s). Veuillez réessayer plus tard.").setMessage(exception.getMessage());
    }
}
