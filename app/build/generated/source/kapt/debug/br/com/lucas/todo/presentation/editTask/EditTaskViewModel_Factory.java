package br.com.lucas.todo.presentation.editTask;

import br.com.lucas.todo.domain.useCases.InsertTaskUseCase;
import br.com.lucas.todo.domain.useCases.UpdateTaskUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class EditTaskViewModel_Factory implements Factory<EditTaskViewModel> {
  private final Provider<InsertTaskUseCase> insertTaskUseCaseProvider;

  private final Provider<UpdateTaskUseCase> updateTaskUseCaseProvider;

  public EditTaskViewModel_Factory(Provider<InsertTaskUseCase> insertTaskUseCaseProvider,
      Provider<UpdateTaskUseCase> updateTaskUseCaseProvider) {
    this.insertTaskUseCaseProvider = insertTaskUseCaseProvider;
    this.updateTaskUseCaseProvider = updateTaskUseCaseProvider;
  }

  @Override
  public EditTaskViewModel get() {
    return newInstance(insertTaskUseCaseProvider.get(), updateTaskUseCaseProvider.get());
  }

  public static EditTaskViewModel_Factory create(
      Provider<InsertTaskUseCase> insertTaskUseCaseProvider,
      Provider<UpdateTaskUseCase> updateTaskUseCaseProvider) {
    return new EditTaskViewModel_Factory(insertTaskUseCaseProvider, updateTaskUseCaseProvider);
  }

  public static EditTaskViewModel newInstance(InsertTaskUseCase insertTaskUseCase,
      UpdateTaskUseCase updateTaskUseCase) {
    return new EditTaskViewModel(insertTaskUseCase, updateTaskUseCase);
  }
}
