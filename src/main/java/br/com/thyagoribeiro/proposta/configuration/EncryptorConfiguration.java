package br.com.thyagoribeiro.proposta.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@Configuration
public class EncryptorConfiguration {

    @Bean
    public TextEncryptor EncryptorConfiguration(@Value("${encrypt.key}") String encryptKey, @Value("${salt.key}") String encryptSalt) {
        return Encryptors.text(encryptKey, encryptSalt);
    }

}
