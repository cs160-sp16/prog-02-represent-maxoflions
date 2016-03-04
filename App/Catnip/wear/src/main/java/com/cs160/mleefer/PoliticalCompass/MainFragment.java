package com.cs160.mleefer.PoliticalCompass;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PARTY = "PARTY";
    private static final String POSITION = "POSITION";
    private static final String NAME = "NAME";
    private static final String COLOR = "COLOR";

    // TODO: Rename and change types of parameters
    private String party;
    private String position;
    private String name;
    private int color;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String party, String position, String name, int color) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(PARTY, party);
        args.putString(POSITION, position);
        args.putString(NAME, name);
        args.putInt(COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            party = getArguments().getString(PARTY);
            position = getArguments().getString(POSITION);
            name = getArguments().getString(NAME);
            color = getArguments().getInt(COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_main, container, false);
        RelativeLayout screen = (RelativeLayout) v.findViewById(R.id.bg);
        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG", "Screen ONCLICK");
                Intent sendIntent = new Intent(getActivity(), WatchToPhoneService.class);
                sendIntent.putExtra("TYPE", "SELECT");
                sendIntent.putExtra("SELECTED_CANDIDATE", name);
                getActivity().startService(sendIntent);
            }
        });

        screen.setBackgroundColor(color);
        TextView text = (TextView) v.findViewById(R.id.name);
        text.setText(name);
        text = (TextView) v.findViewById(R.id.position);
        text.setText(position);
        text = (TextView) v.findViewById(R.id.party);
        text.setText(party);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
