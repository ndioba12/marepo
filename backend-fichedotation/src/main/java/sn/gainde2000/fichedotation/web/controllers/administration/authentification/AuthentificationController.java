package sn.gainde2000.fichedotation.web.controllers.administration.authentification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.fichedotation.services.interfaces.administration.authentification.IAuthentification;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.EditMonCompteDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.LoginFormDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.ResetOrForgetFormDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;

import javax.validation.Valid;
import javax.validation.constraints.Email;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Tag(name = "AuthentificationController", description = "Permet de gérer les opérations sur l'authentification")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthentificationController {
    public final PasswordEncoder encoder;
    private final IAuthentification iAuth;
/*
    @Operation(summary = "Endpoint pour ajouter un utilisateur")
    @PostMapping("/signup")
    public Response<Object> registerUtilisateur(@Valid @RequestBody UtilisateurDTO dto) {
        return iAuth.registerUtilisateur(dto);
    }
*/
    @Operation(summary = "Endpoint pour vérifier si un email est utilisé")
    @GetMapping("/checkIfEmailIsUsed/{email}")
    public Response<Object> checkIfEmailIsUsed(@PathVariable @Email String email) {
        return iAuth.checkIfEmailIsUsed(email);
    }

    @Operation(summary = "Endpoint pour s'authentifier")
    @PostMapping("/signin")
    public Response<Object> authenticateUser(@Valid @RequestBody LoginFormDTO loginRequest) {
        return iAuth.authenticateUser(loginRequest);
    }

    @Operation(summary = "Endpoint pour s'authentifier à partir d'un lien mail pour une première connexion")
    @PostMapping("/signinWithFirstUrlConnexion")
    public Response<Object> authenticateUserWithFirstUrlConnexion(@Valid @RequestBody ResetOrForgetFormDTO formRequest) {
        return iAuth.authenticateUserWithFirstUrlConnexion(formRequest);
    }

    @Operation(summary = "Endpoint pour s'authentifier à partir d'un lien mail apres mot de passe oublié")
    @PostMapping("/signinWithForgetPasswordUrlConnexion")
    public Response<Object> authenticateUserWithForgetPasswordUrlConnexion(@Valid @RequestBody ResetOrForgetFormDTO formRequest) {
        return iAuth.authenticateUserWithForgetPasswordUrlConnexion(formRequest);
    }

    @Operation(summary = "Endpoint pour s'authentifier à partir d'un lien mail pour une première connexion")
    @GetMapping("/forgotPassword")
    public Response<Object> forgotPassword(@RequestParam("login") String login) {
        return iAuth.initPassword(login);
    }

    @Operation(summary = "Endpoint pour modifier les informations d'un utilisateur")
    @PostMapping("/editUserInfos")
    public Response<Object> editUserInfos(@Valid @RequestBody EditMonCompteDTO req) {
        return iAuth.editUserInfos(req);
    }

    @Operation(summary = "Endpoint pour modifier le mot de passe d'un utilisateur")
    @PostMapping("/editUserPassword")
    public Response<Object> editUserPassword(@Valid @RequestBody ResetOrForgetFormDTO req) {
        return iAuth.updatePasswordFromInterface(req);
    }
}
