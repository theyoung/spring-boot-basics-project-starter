package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("select * from FILES")
    List<File> getFiles();

    @Insert("insert into FILES(filename, contenttype, filesize,userid, filedata) values (#{fileName}, #{contentType}, #{fileSize},#{userId},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Delete("delete from FILES where fileId = #{fileId}")
    int deleteFile(File file);

    @Delete("delete from FILES where fileId = #{fileId}")
    int deleteFileWithString(String fileId);

    @Select("select * from FILES where fileId = #{fileId}")
    File findFileById(String fileId);
}