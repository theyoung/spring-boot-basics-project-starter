package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("select * from FILES")
    List<File> getFiles();

    @Insert("insert into FILES(filename, contenttype, filesize,userid,filedata) values (#{filename}, #{contenttype}, #{filesize},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Delete("delete from FILES where fileId = #{fileId}")
    int deleteFile(File file);

    @Delete("delete from FILES where fileId = #{fileId}")
    int deleteFileWithString(String fileId);
}

//    CREATE TABLE IF NOT EXISTS FILES (
//        fileId INT PRIMARY KEY auto_increment,
//        filename VARCHAR,
//        contenttype VARCHAR,
//        filesize VARCHAR,
//        userid INT,
//        filedata BLOB,
//        foreign key (userid) references USERS(userid)
//        );