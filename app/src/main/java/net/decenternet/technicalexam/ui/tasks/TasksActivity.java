package net.decenternet.technicalexam.ui.tasks;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.decenternet.technicalexam.domain.Task;

import java.util.List;

public class TasksActivity extends AppCompatActivity implements TasksContract.View {

    private TasksContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Finish this simple task recording app.
         * The following are the remaining todos for this project:
         * 1. Make sure all defects are fixed.
         * 2. Showing a dialog to add/edit the task.
         * 3. Allowing the user to toggle it as completed.
         * 4. Allowing the user to delete a task.
         *
         */

    }

    @Override
    public void displayTasks(List<Task> tasks) {

    }

    @Override
    public void addTaskToList(Task task) {

    }

    @Override
    public void removeTaskFromList(Task task) {

    }

    @Override
    public void updateTaskInList(Task task) {

    }

    @Override
    public void popupTaskAddingDialog() {

    }

    @Override
    public void popupTaskEditorDialog(Task task) {

    }
}
