package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    public final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int addFile(File file){
        return fileMapper.insertFile(file);
    }

    public List<File> getFiles(){
        return fileMapper.getFiles();
    }

    public int deleteFile(String fileId) {
        return fileMapper.deleteFileWithString(fileId);
    }

    public File findFileById(String fileId){
        return fileMapper.findFileById(fileId);
    }
}
