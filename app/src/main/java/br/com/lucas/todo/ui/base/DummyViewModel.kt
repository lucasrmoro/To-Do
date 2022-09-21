package br.com.lucas.todo.ui.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class DummyViewModel @Inject constructor() : ViewModel()