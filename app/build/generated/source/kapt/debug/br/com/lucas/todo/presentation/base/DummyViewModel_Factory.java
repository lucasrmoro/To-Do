package br.com.lucas.todo.presentation.base;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DummyViewModel_Factory implements Factory<DummyViewModel> {
  @Override
  public DummyViewModel get() {
    return newInstance();
  }

  public static DummyViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DummyViewModel newInstance() {
    return new DummyViewModel();
  }

  private static final class InstanceHolder {
    private static final DummyViewModel_Factory INSTANCE = new DummyViewModel_Factory();
  }
}
