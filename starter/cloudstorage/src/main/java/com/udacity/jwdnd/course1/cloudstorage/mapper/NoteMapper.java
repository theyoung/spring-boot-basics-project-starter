package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("select * from NOTES")
    List<Note> getNotes();

    @Insert("insert into NOTES(notetitle, notedescription, userid) values (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);

    @Update("update NOTES set notetitle = #{noteTitle}, notedescription = #{noteDescription},  userid = #{userId} where noteId = #{noteId}")
    int updateNote(Note note);

    @Delete("delete from NOTES where noteId = #{noteId}")
    int deleteNote(Note note);

    @Delete("delete from NOTES where noteId = #{noteId}")
    int deleteNoteWithString(String noteId);
}
