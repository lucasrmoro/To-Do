package br.com.lucas.todo.presentation.editTask;

import br.com.lucas.todo.data.TaskDao;
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
  private final Provider<TaskDao> taskDaoProvider;

  public EditTaskViewModel_Factory(Provider<TaskDao> taskDaoProvider) {
    this.taskDaoProvider = taskDaoProvider;
  }

  @Override
  public EditTaskViewModel get() {
    return newInstance(taskDaoProvider.get());
  }

  public static EditTaskViewModel_Factory create(Provider<TaskDao> taskDaoProvider) {
    return new EditTaskViewModel_Factory(taskDaoProvider);
  }

  public static EditTaskViewModel newInstance(TaskDao taskDao) {
    return new EditTaskViewModel(taskDao);
  }
}
