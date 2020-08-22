package net.decenternet.technicalexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import net.decenternet.technicalexam.domain.Task;
import net.decenternet.technicalexam.ui.tasks.TasksActivity;

import java.util.List;

public class TasksAdapter extends BaseAdapter implements ListAdapter {
    private List<Task> tasks;
    private Context context;

    public TasksAdapter(List<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int pos) {
        return tasks.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return tasks.get(pos).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_todo, null);
        }

        TextView listItemText = (TextView)view.findViewById(R.id.task_description);
        listItemText.setText(tasks.get(position).getDescription());
        Button deleteBtn = (Button)view.findViewById(R.id.task_delete);
        Button editBtn = (Button)view.findViewById(R.id.task_edit);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TasksActivity.getInstance().removeTaskFromList(tasks.get(position));
                notifyDataSetChanged();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TasksActivity.getInstance().popupTaskEditorDialog(tasks.get(position));
            }
        });

        return view;
    }
}