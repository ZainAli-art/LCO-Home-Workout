package com.example.android.lcohomeworkout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutListFragment extends Fragment implements View.OnClickListener {
    private NavController navController;

    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        WorkoutViewModel viewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setAdapter(new WorkoutListAdapter(viewModel.getWorkoutSession()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button workoutButton = view.findViewById(R.id.startButton);
        workoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startButton:
                navController.navigate(R.id.action_workoutListFragment_to_workoutFragment);
                break;
            default:
                Toast.makeText(getContext(), "No Functionality added yet", Toast.LENGTH_SHORT);
        }
    }

    public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.WorkoutViewHolder> {
        private List<Workout> workoutSession;

        public WorkoutListAdapter(List<Workout> workoutSession) {
            this.workoutSession = workoutSession;
        }

        @NonNull
        @Override
        public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.workout_list_item, parent, false);

            return new WorkoutViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
            if (workoutSession != null) {
                Workout curWorkout = workoutSession.get(position);
                holder.image.setImageResource(curWorkout.getImageResId());
                holder.title.setText(curWorkout.getName());
                holder.trailing.setText("****");
            }
        }

        @Override
        public int getItemCount() {
            if (workoutSession != null)
                return workoutSession.size();
            return 0;
        }

        private class WorkoutViewHolder extends RecyclerView.ViewHolder {
            private ImageView image;
            private TextView title;
            private TextView trailing;

            public WorkoutViewHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
                title = itemView.findViewById(R.id.itemTitle);
                trailing = itemView.findViewById(R.id.trailing);
            }
        }
    }
}
