package br.com.lucas.todo.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DummyViewModel @Inject constructor() : ViewModel()