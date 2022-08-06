package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("select * from CREDENTIALS")
    List<Credential> getCredentials();

    @Insert("insert into CREDENTIALS(username, key, password, userid, url) values (#{userName}, #{key}, #{password}, #{userId}, #{url})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credential credential);

    @Update("update CREDENTIALS set username = #{userName}, key = #{key},  password = #{password},  userid = #{userId}, url = #{url} where credentialid = #{credentialId}")
    int updateCredential(Credential credential);

    @Delete("delete from CREDENTIALS where credentialid = #{credentialId}")
    int deleteCredential(Credential credential);

    @Delete("delete from CREDENTIALS where credentialid = #{credentialId}")
    int deleteCredentialWithString(String credentialId);
}
