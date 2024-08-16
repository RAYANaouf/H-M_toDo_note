package com.jetapptech.halfwarenote.di

import androidx.room.Room
import com.jetapptech.halfwarenote.MainViewModel
import com.jetapptech.halfwarenote.data.local.room.dao.CheckBox_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.Media_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.Note_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.Note_Dao_Impl
import com.jetapptech.halfwarenote.data.local.room.dao.Paragraph_Dao
import com.jetapptech.halfwarenote.data.local.room.database.Database
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.addNoteViewModel.AddNoteViewModel
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.homeViewModel.HomeViewModel
import com.jetapptech.halfwarenote.presentation.view.screen.noteScreen.viewModel.NoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val koinModule = module{

    single {
        Room
            .databaseBuilder(
                context = androidContext(),
                klass   = Database::class.java,
                name    = "myDatabaseV18"
            )
//            .addTypeConverter(Converter_Room())
            .fallbackToDestructiveMigration()
            .build()
    }


    single {
        get<Database>().noteDao
    }


    single {
        get<Database>().mediaDao
    }



    single {
        get<Database>().checkBoxDao
    }

    single {
        get<Database>().paragraphDao
    }

    single {
        get<Database>().categoryDao
    }





    viewModel {
        AddNoteViewModel(
            noteDao      = get(),
            paragraphDao = get(),
            mediaDao     = get(),
            checkBoxDao  = get(),
            categoryDao  = get()
        )
    }

    viewModel {
        HomeViewModel(
            noteDao     = get(),
            categoryDao = get()
        )
    }

    viewModel {
        NoteViewModel(
            noteDao = get()
        )
    }

    viewModel {
        MainViewModel()
    }



}