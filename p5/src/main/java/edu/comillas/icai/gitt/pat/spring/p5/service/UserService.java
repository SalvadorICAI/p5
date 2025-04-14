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
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public Token login(String email, String password) {
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null) return null;

        if (!Hashing.matches(password, appUser.getPassword())) return null;

        Token existing = tokenRepository.findByAppUser(appUser);
        if (existing != null) return existing;

        Token token = new Token();
        token.setAppUser(appUser);
        return tokenRepository.save(token);
    }

    public AppUser authentication(String tokenId) {
        Optional<Token> token = tokenRepository.findById(tokenId);
        return token.map(Token::getAppUser).orElse(null);
    }

    public ProfileResponse profile(AppUser appUser) {
        return new ProfileResponse(appUser.getEmail(), appUser.getName(), appUser.getRole());
    }

    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {
        if (StringUtils.hasText(profile.name())) {
            appUser.setName(profile.name());
        }

        if (StringUtils.hasText(profile.password())) {
            appUser.setPassword(Hashing.hash(profile.password()));
        }

        appUser = appUserRepository.save(appUser);
        return profile(appUser);
    }

    public ProfileResponse profile(RegisterRequest register) {
        AppUser appUser = new AppUser();
        appUser.setEmail(register.email());
        appUser.setPassword(Hashing.hash(register.password()));
        appUser.setRole(register.role());
        appUser.setName(register.name());

        appUser = appUserRepository.save(appUser);
        return profile(appUser);
    }

    public void logout(String tokenId) {
        tokenRepository.deleteById(tokenId);
    }

    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);
    }


}
