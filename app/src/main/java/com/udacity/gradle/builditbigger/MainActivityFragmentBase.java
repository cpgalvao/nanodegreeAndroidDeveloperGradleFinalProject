package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import br.com.uol.jokedisplay.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public abstract class MainActivityFragmentBase extends Fragment {
    private ProgressBar progress;

    public void requestJoke() {
        progress.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(new JokeCallback() {
            @Override public void jokeResult(String joke) {
                progress.setVisibility(View.GONE);

                Intent intent = new Intent(getActivity(), JokeActivity.class);
                intent.putExtra("JOKE", joke);
                startActivity(intent);
            }
        }).execute("Cinthia");
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button jokeButton = root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                openJoke();
            }
        });

        progress = root.findViewById(R.id.progress);

        return root;
    }

    protected abstract void openJoke();
}
