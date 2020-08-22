package net.decenternet.technicalexam.data;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.decenternet.technicalexam.domain.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskLocalServiceProvider implements TaskLocalService {

    private final SharedPreferences sharedPreferences;
    private Collection<Task> localTasks;

    public TaskLocalServiceProvider(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        localTasks = new Gson()
                .fromJson(
                        sharedPreferences.getString("tasks", "[]"),
                        new TypeToken<List<Task>>(){}.getType()
                );
    }

    @Override
    public void save(Task task) {
        ArrayList<Task> tasks = new ArrayList<>(localTasks);
        task.setId(getNextId());
        tasks.add(task);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tasks", new Gson().toJson(tasks));
        editor.apply();

        localTasks = tasks;
    }

    @Override
    public void update(Task task) {
        ArrayList<Task> tasks = new ArrayList<>(localTasks);
        for (int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId() == task.getId()){
                tasks.get(i).setDescription(task.getDescription());
            }
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tasks", new Gson().toJson(tasks));
        editor.apply();
        localTasks = tasks;
    }

    @Override
    public void delete(int id) {
        ArrayList<Task> tasks = new ArrayList<>(localTasks);
        for (int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId() == id){
                tasks.remove(tasks.get(i));
            }
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tasks", new Gson().toJson(tasks));
        editor.apply();
        localTasks = tasks;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(localTasks);
    }

    private Integer getNextId() {
        int max = 0;
        ArrayList<Task> tasks = new ArrayList<>(localTasks);
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId() >= max){
                max = tasks.get(i).getId();
            }
        }
        return max + 1;
    }
}
