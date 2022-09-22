package br.com.lucas.todo.presentation.listTask;

import br.com.lucas.todo.database.TaskDao;
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
  private final Provider<TaskDao> taskDaoProvider;

  public ListTaskViewModel_Factory(Provider<TaskDao> taskDaoProvider) {
    this.taskDaoProvider = taskDaoProvider;
  }

  @Override
  public ListTaskViewModel get() {
    return newInstance(taskDaoProvider.get());
  }

  public static ListTaskViewModel_Factory create(Provider<TaskDao> taskDaoProvider) {
    return new ListTaskViewModel_Factory(taskDaoProvider);
  }

  public static ListTaskViewModel newInstance(TaskDao taskDao) {
    return new ListTaskViewModel(taskDao);
  }
}
