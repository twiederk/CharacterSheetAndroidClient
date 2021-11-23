package com.android.ash.charactersheet.gui.util;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Superclass for Fragments which log their events.
 */
public class LogFragment extends Fragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        Logger.info(getClass().getSimpleName() + ".onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        Logger.info(getClass().getSimpleName() + ".onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        Logger.info(getClass().getSimpleName() + ".onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Logger.info(getClass().getSimpleName() + ".onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Logger.info(getClass().getSimpleName() + ".onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Logger.info(getClass().getSimpleName() + ".onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Logger.info(getClass().getSimpleName() + ".onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Logger.info(getClass().getSimpleName() + ".onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Logger.info(getClass().getSimpleName() + ".onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Logger.info(getClass().getSimpleName() + ".onDetach");
        super.onDetach();
    }
}
