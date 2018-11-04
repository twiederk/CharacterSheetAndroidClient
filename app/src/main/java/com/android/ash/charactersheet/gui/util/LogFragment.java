package com.android.ash.charactersheet.gui.util;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Superclass for Fragments wich log their events.
 */
public class LogFragment extends Fragment {

    @Override
    public void onAttach(final Activity activity) {
        Logger.info(getClass().getSimpleName() + ".onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        Logger.info(getClass().getSimpleName() + ".onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        Logger.info(getClass().getSimpleName() + ".onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

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
