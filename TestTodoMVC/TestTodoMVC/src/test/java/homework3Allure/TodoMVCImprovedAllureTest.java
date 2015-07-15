package homework3Allure;

import com.codeborne.selenide.ElementsCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Anna on 5/20/2015.
 */
public class TodoMVCImprovedAllureTest {

    @Test
    public void testAllFilter()
    {
        //add tasks
        addTask("all-task1");
        addTask("all-task2");
        addTask("all-task3");
        addTask("all-task4");
        assertTasksHaveExactTexts("all-task1", "all-task2", "all-task3", "all-task4");
        checkCounter(4);

        //delete task
        deleteTask("all-task2");
        assertTasksHaveExactTexts("all-task1", "all-task3", "all-task4");
        checkCounter(3);

        //mark task completed
        toggleTask("all-task3");
        checkCounter(2);

        //un mark task 3 completed
        toggleTask("all-task3");
        checkCounter(3);

        //this is additional check  based on found bugs in another projects(should be placed in separate test, please ignore it :) ): mark task 4 completed
        toggleTask("all-task4");
        checkCounter(2);

        //clear completed
        clearCompleted();
        assertTasksHaveExactTexts("all-task1", "all-task3");

        // Edit task
        editTask("all-task3", "all-task3 edited");
        assertTasksHaveExactTexts("all-task1", "all-task3 edited");

        //mark all as completed
        toggleAll();
        checkCounter(0);
        clearCompleted();
        assertVisibleTasksListEmpty();
    }

    @Test
    public void testActiveFilter() {
        //add tasks: one in All filter, switch to Active and add 3 tasks
        addTask("All-task1");
        selectActiveFilter();
        addTask("Active-task2");
        addTask("Active-task3");
        addTask("Active-task4");

        // mark tasks completed, switch to All, add task and check results in All filter
        toggleTask("All-task1");
        toggleTask("Active-task2");
        toggleTask("Active-task3");
        assertVisibleTasksHaveExactTexts("Active-task4");
        checkCounter(1);
        selectAllFilter();
        addTask("All-task5");
        assertTasksHaveExactTexts("All-task1", "Active-task2", "Active-task3", "Active-task4", "All-task5");
        checkCounter(2);

        //check tasks appearing in completed filter
        selectCompletedFilter();
        assertVisibleTasksHaveExactTexts("All-task1", "Active-task2", "Active-task3");

        //mark all completed & un-mark all as completed in Active filter
        selectActiveFilter();
        toggleAll();
        checkCounter(0);
        assertVisibleTasksListEmpty();
        toggleAll();
        assertVisibleTasksHaveExactTexts("All-task1", "Active-task2", "Active-task3", "Active-task4", "All-task5");
        checkCounter(5);

        //delete task in Active and verify in All filter
        deleteTask("Active-task2");
        assertVisibleTasksHaveExactTexts("All-task1", "Active-task3", "Active-task4","All-task5");
        checkCounter(4);
        selectAllFilter();
        assertTasksHaveExactTexts("All-task1", "Active-task3", "Active-task4", "All-task5");
        checkCounter(4);

        // Edit task in Active and verify in All filer
        selectActiveFilter();
        editTask("Active-task3", "Active-task3 edited");
        assertVisibleTasksHaveExactTexts("All-task1", "Active-task3 edited", "Active-task4", "All-task5");
        selectAllFilter();
        assertTasksHaveExactTexts("All-task1", "Active-task3 edited", "Active-task4", "All-task5");

    }

    @Test
    public void testCompletedFilter() {
        //add tasks
        addTask("Completed-task1");
        addTask("Completed-task2");
        addTask("Completed-task3");
        addTask("Completed-task4");

        //mark completed
        toggleTask("Completed-task1");
        toggleTask("Completed-task2");
        toggleTask("Completed-task4");
        selectCompletedFilter();
        assertVisibleTasksHaveExactTexts("Completed-task1", "Completed-task2", "Completed-task4");
        checkCounter(1);

        //edit task in Completed and verify in All filer
        editTask("Completed-task1", "Completed-task1 edited");
        assertVisibleTasksHaveExactTexts("Completed-task1 edited", "Completed-task2", "Completed-task4");
        selectAllFilter();
        assertTasksHaveExactTexts("Completed-task1 edited", "Completed-task2","Completed-task3", "Completed-task4");


        //un-mark completed
        selectCompletedFilter();
        toggleTask("Completed-task2");
        checkCounter(2);
        assertVisibleTasksHaveExactTexts("Completed-task1 edited", "Completed-task4");

        //check in active filter after un-marking completed
        selectActiveFilter();
        assertVisibleTasksHaveExactTexts("Completed-task2", "Completed-task3");

        //check in All  filter after un-marking completed
        selectAllFilter();
        assertTasksHaveExactTexts("Completed-task1 edited", "Completed-task2","Completed-task3", "Completed-task4");

        //delete task
        selectCompletedFilter();
        deleteTask("Completed-task4");
        assertVisibleTasksHaveExactTexts("Completed-task1 edited");
        checkCounter(2);

        //check in All filter after deletion in completed
        selectAllFilter();
        assertTasksHaveExactTexts("Completed-task1 edited", "Completed-task2","Completed-task3");
        checkCounter(2);

        //clear completed
        selectCompletedFilter();
        clearCompleted();
        assertVisibleTasksListEmpty();
        checkCounter(2);

        //toggle all in Completed filter and verify Active filter
        toggleAll();
        assertVisibleTasksHaveExactTexts("Completed-task2", "Completed-task3");
        checkCounter(0);
        selectActiveFilter();
        checkCounter(0);
        assertVisibleTasksListEmpty();

        //clear all completed
        selectCompletedFilter();
        clearCompleted();
        assertVisibleTasksListEmpty();

    }

    @Before
    public void openTodoMVC(){
        open("http://todomvc.com/examples/troopjs_require/#");
    }

    @After
    public void clearData () {
        executeJavaScript("localStorage.clear()");
        open("http://todomvc.com/");
    }

    ElementsCollection tasks = $$("#todo-list>li");

    @Step
    public void addTask (String taskName) {
        $("#new-todo").setValue(taskName).pressEnter();
    }

    @Step
    public void editTask(String oldTaskName, String newTaskName) {
        actions().doubleClick(tasks.find(exactText(oldTaskName)).find("label")).perform();
        tasks.find(cssClass("editing")).find(".edit").setValue(newTaskName).pressEnter();
    }

    @Step
    public void deleteTask(String taskName) {
        tasks.find(exactText(taskName)).hover();
        tasks.find(exactText(taskName)).find(".destroy").click();
    }

    @Step
    public void assertTasksHaveExactTexts(String... taskNames){
        tasks.shouldHave(exactTexts(taskNames));
    }

    @Step
    public void assertVisibleTasksHaveExactTexts(String... taskNames){
        tasks.filter(visible).shouldHave(exactTexts(taskNames));
    }

    @Step
    public void assertVisibleTasksListEmpty(){
        tasks.filter(visible).shouldHave(empty);
    }

    @Step
    public void clearCompleted () {
        $("#clear-completed").click();
    }

    @Step
    public void checkCounter(int itemsLeftCounter) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(itemsLeftCounter)));
    }

    @Step
    public void selectActiveFilter() {
        $("[href='#/active']").click();
    }

    @Step
    public void selectAllFilter() {
        $("[href='#/']").click();
    }

    @Step
    public void selectCompletedFilter() {
        $("[href='#/completed']").click();
    }

    @Step
    public void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    public void toggleTask(String taskName) {
        tasks.find(exactText(taskName)).find(".toggle").click();

    }

}
