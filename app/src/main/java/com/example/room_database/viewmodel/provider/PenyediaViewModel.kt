package com.example.room_database.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.room_database.viewmodel.HomeViewModel
import com.example.room_database.repositori.AplikasiSiswa
import com.example.room_database.viewmodel.DetailViewModel
import com.example.room_database.viewmodel.EditViewModel
import com.example.room_database.viewmodel.EntryViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            EntryViewModel(aplikasiSiswa().container.repositoriSiswa)
        }
        initializer {
            DetailViewModel(savedStateHandle = this.createSavedStateHandle(),
                repositoriSiswa = aplikasiSiswa().container.repositoriSiswa)
        }
        initializer {
            EditViewModel(this.createSavedStateHandle(),aplikasiSiswa().container.repositoriSiswa)
        }
    }
}

fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
            AplikasiSiswa)