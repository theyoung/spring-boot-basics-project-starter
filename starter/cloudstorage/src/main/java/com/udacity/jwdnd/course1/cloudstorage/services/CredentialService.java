package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    public final CredentialMapper credentialMapper;
    public final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int addCredential(Credential credential){
        return credentialMapper.insertCredential(credential);
    }

    public int updateCredential(Credential credential){
        return credentialMapper.updateCredential(credential);
    }

    public List<Credential> getCredentials(int userId){
        List<Credential> list = credentialMapper.getCredentials(userId);
        list.forEach(
            credential -> {
                credential.setDecryPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
            }
        );

        return list;
    }

    public Credential getCredential(Credential credential) {
        return credentialMapper.getCredentialByCredential(credential);
    }

    public int deleteCredential(Credential credential) {
        return credentialMapper.deleteCredential(credential);
    }

    public int deleteCredential(String credential) {
        return credentialMapper.deleteCredentialWithString(credential);
    }
}
