package br.com.lucas.todo.presentation.listTask;

import br.com.lucas.todo.domain.useCases.DeleteTaskUseCase;
import br.com.lucas.todo.domain.useCases.GetAllTasksUseCase;
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
public final class ListTaskViewModel_Factory implements Factory<ListTaskViewModel> {
  private final Provider<DeleteTaskUseCase> deleteTaskUseCaseProvider;

  private final Provider<GetAllTasksUseCase> getAllTasksUseCaseProvider;

  public ListTaskViewModel_Factory(Provider<DeleteTaskUseCase> deleteTaskUseCaseProvider,
      Provider<GetAllTasksUseCase> getAllTasksUseCaseProvider) {
    this.deleteTaskUseCaseProvider = deleteTaskUseCaseProvider;
    this.getAllTasksUseCaseProvider = getAllTasksUseCaseProvider;
  }

  @Override
  public ListTaskViewModel get() {
    return newInstance(deleteTaskUseCaseProvider.get(), getAllTasksUseCaseProvider.get());
  }

  public static ListTaskViewModel_Factory create(
      Provider<DeleteTaskUseCase> deleteTaskUseCaseProvider,
      Provider<GetAllTasksUseCase> getAllTasksUseCaseProvider) {
    return new ListTaskViewModel_Factory(deleteTaskUseCaseProvider, getAllTasksUseCaseProvider);
  }

  public static ListTaskViewModel newInstance(DeleteTaskUseCase deleteTaskUseCase,
      GetAllTasksUseCase getAllTasksUseCase) {
    return new ListTaskViewModel(deleteTaskUseCase, getAllTasksUseCase);
  }
}
