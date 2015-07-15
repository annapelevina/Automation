package homework1Base;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by ��� on 5/20/2015.
 */
public class TodoMVCBaseTest {
    ElementsCollection tasks = $$("#todo-list>li");

    public void addTask (String task) {
        $("#new-todo").setValue(task).pressEnter();
    }

    public void clearCompleted () {
        $("#clear-completed").click();
    }

    @Test
    public void testMVCHomeWork1 () {
        open("http://todomvc.com/examples/troopjs_require/#");
        //add tasks and verify added tasks
        addTask("task1");
        addTask("task2");
        addTask("task3");
        addTask("task4");
        tasks.shouldHave(exactTexts("task1", "task2", "task3", "task4"));

        //delete task 2
        tasks.find(exactText("task2")).hover();
        tasks.find(exactText("task2")).find(".destroy").click();
        tasks.shouldHave(exactTexts("task1", "task3", "task4"));

        //mark task 4 completed
        tasks.find(exactText("task4")).find(".toggle").click();

        //clear completed
        clearCompleted();
        tasks.shouldHave(exactTexts("task1", "task3"));

        //mark all as completed
        $("#toggle-all").click();
        clearCompleted();
        tasks.shouldBe(empty);
    }
}
