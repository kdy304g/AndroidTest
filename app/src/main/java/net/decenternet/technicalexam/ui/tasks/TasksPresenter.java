package net.decenternet.technicalexam.ui.tasks;

import net.decenternet.technicalexam.data.TaskLocalService;
import net.decenternet.technicalexam.domain.Task;

public class TasksPresenter implements TasksContract.Presenter {

    private final TaskLocalService taskLocalService;

    public TasksPresenter(TaskLocalService taskLocalService) {
        this.taskLocalService = taskLocalService;
    }

    @Override
    public void onAddTaskClicked() {
        TasksActivity.getInstance().popupTaskAddingDialog();
    }

    @Override
    public void onSaveTaskClicked(Task task) {
        taskLocalService.save(task);
    }

    @Override
    public void onEditTaskClicked(Task task) {
        taskLocalService.update(task);
    }

    @Override
    public void onTaskChecked(int taskId) {

    }

    @Override
    public void onTaskUnchecked(int taskId) {

    }

    @Override
    public void onDeleteTaskClicked(int taskId) {
        taskLocalService.delete(taskId);
    }
}
