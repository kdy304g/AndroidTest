package net.decenternet.technicalexam.ui.tasks;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.decenternet.technicalexam.R;
import net.decenternet.technicalexam.TasksAdapter;
import net.decenternet.technicalexam.data.TaskLocalServiceProvider;
import net.decenternet.technicalexam.domain.Task;

import java.util.List;

public class TasksActivity extends AppCompatActivity implements TasksContract.View {

    private static final String TAG = "TasksActivity";
    private TasksAdapter mAdapter;
    private ListView mTaskListView;
    private List<Task> taskList;
    private SharedPreferences sharedPreferences;
    private TaskLocalServiceProvider service;
    private TasksPresenter presenter;
    private static TasksActivity instance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        instance = this;
        mTaskListView = (ListView) findViewById(R.id.list_todo);
        sharedPreferences = getSharedPreferences("TasksApplicatoin",MODE_PRIVATE);
        service = new TaskLocalServiceProvider(sharedPreferences);
        presenter = new TasksPresenter(service);
        taskList = service.findAll();
        displayTasks(taskList);
    }

    public static TasksActivity getInstance() {
         return instance;
    }

    @Override
    public void displayTasks(List<Task> tasks) {
        mAdapter = new TasksAdapter(tasks, this);
        mTaskListView.setAdapter(mAdapter);
    }


    @Override
    public void addTaskToList(Task task) {
        presenter.onSaveTaskClicked(task);
        taskList = service.findAll();
        displayTasks(taskList);
    }

    @Override
    public void removeTaskFromList(Task task) {
        presenter.onDeleteTaskClicked(task.getId());
        taskList = service.findAll();
        displayTasks(taskList);
    }

    @Override
    public void updateTaskInList(Task task) {
        presenter.onEditTaskClicked(task);
        taskList = service.findAll();
        displayTasks(taskList);
    }

    @Override
    public void popupTaskAddingDialog() {
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new task")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String description = String.valueOf(taskEditText.getText());
                        Task task = new Task();
                        task.setDescription(description);
                        addTaskToList(task);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    @Override
    public void popupTaskEditorDialog(final Task task) {
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Edit task")
                .setMessage("Please enter new description")
                .setView(taskEditText)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String description = String.valueOf(taskEditText.getText());
                        task.setDescription(description);
                        updateTaskInList(task);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tasks, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                presenter.onAddTaskClicked();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
