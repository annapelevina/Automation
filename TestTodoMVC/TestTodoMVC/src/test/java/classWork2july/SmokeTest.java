package classWork2july;

import classWork2july.features.TodoMVCtestSuitMaven;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by anna.pelevina on 7/2/2015.
 */
@RunWith(Categories.class)//использовать категории для запуска
@Suite.SuiteClasses (TodoMVCtestSuitMaven.class)//в каких тест классах искать
@Categories.IncludeCategory(Smoke.class)//какие категории искать
public class SmokeTest {
}
