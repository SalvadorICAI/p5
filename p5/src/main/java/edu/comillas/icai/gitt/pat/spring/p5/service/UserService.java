package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.p5.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */

@Service
public class UserService implements UserServiceInterface {

    public Token login(String email, String password) {
        AppUser appUser = null;
        if (appUser == null) return null;

        Token token = null;
        if (token != null) return token;

        token = new Token();
        return null;
    }

    public AppUser authentication(String tokenId) {
        return null;
    }

    public ProfileResponse profile(AppUser appUser) {
        return null;
    }
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {
        return null;
    }
    public ProfileResponse profile(RegisterRequest register) {
        return null;
    }

    public void logout(String tokenId) {

    }

    public void delete(AppUser appUser) {

    }

}
