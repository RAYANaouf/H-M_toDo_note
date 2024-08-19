package com.jetapptech.halfwarenote.data.local.room.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.jetapptech.halfwarenote.data.local.room.entities.Note_Room
import com.jetapptech.halfwarenote.data.local.room.relations.NoteAndComponents
import kotlinx.coroutines.flow.Flow
import java.io.File

@Dao
interface Note_Dao{

    @Upsert
    suspend fun insert(note  : Note_Room) : Long

    @Delete
    suspend fun delete(note  : Note_Room)


    @Query("Select img from media where note_id = :noteId")
    suspend fun getMediaFilePaths(noteId: Int):List<String>

    @Transaction
    suspend fun deleteWithAllImages(noteId  : Int){
        val mediaFilePaths = getMediaFilePaths(noteId)

        mediaFilePaths.forEach { path->
            val file = File(path)
            if (file.exists()){
                file.delete()
            }
        }

        delete(Note_Room(id = noteId-))
    }



    @Transaction
    @Query("select * from Note where id=:noteId")
    suspend fun getNoteById(noteId : Int): NoteAndComponents

    @Transaction
    @Query("select * from Note where category_id=:categoryId")
    fun getNoteByCategory(categoryId : Int): Flow<List<NoteAndComponents>>

    @Transaction
    @Query("select * from Note where title like '%' || :noteTitle || '%' ")
    fun getNotesByTitle(noteTitle : String): Flow<List<NoteAndComponents>>


    @Transaction
    @Query("select * from Note")
    fun getNoteAndComponents(): Flow<List<NoteAndComponents>>

//    @Query("select * from Note ")
//    fun getNotes(): Flow<List<Note_Room>>
}