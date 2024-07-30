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
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val koinModule = module{

    single {
        Room
            .databaseBuilder(
                context = androidContext(),
                klass   = Database::class.java,
                name    = "myDatabaseV12"
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





    viewModel {
        AddNoteViewModel(
            noteDao = get<Note_Dao>(),
            paragraphDao = get<Paragraph_Dao>(),
            mediaDao = get<Media_Dao>(),
            checkBoxDao = get<CheckBox_Dao>()
        )
    }

    viewModel {
        HomeViewModel(
            noteDao = get<Note_Dao>()
        )
    }

    viewModel {
        MainViewModel()
    }



}