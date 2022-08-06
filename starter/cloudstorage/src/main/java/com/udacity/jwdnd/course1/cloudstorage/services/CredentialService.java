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

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public int addCredential(Credential credential){
        return credentialMapper.insertCredential(credential);
    }

    public int updateCredential(Credential credential){
        return credentialMapper.updateCredential(credential);
    }

    public List<Credential> getCredentials(int userId){
        return credentialMapper.getCredentials(userId);
    }

    public int deleteCredential(Credential credential) {
        return credentialMapper.deleteCredential(credential);
    }

    public int deleteCredential(String credential) {
        return credentialMapper.deleteCredentialWithString(credential);
    }
}
