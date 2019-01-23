package com.example.amin.beatbox;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amin.beatbox.model.Sound;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BeatBoxFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BeatBoxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeatBoxFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SoundAdapter mSoundAdapter;
    private BeatBox mBeatBox;

    private OnFragmentInteractionListener mListener;

    public BeatBoxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BeatBoxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BeatBoxFragment newInstance() {
        BeatBoxFragment fragment = new BeatBoxFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        mBeatBox = new BeatBox(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);

        mRecyclerView = view.findViewById(R.id.beat_box_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        mSoundAdapter = new SoundAdapter(mBeatBox.getSounds());
        mRecyclerView.setAdapter(mSoundAdapter);

        return view;
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

    @Override
    public void onDestroy() {
        super.onDestroy();

        mBeatBox.release();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class SoundHolder extends RecyclerView.ViewHolder {

        private Button mButton;
        private Sound mSound;

        public SoundHolder(@NonNull View itemView) {
            super(itemView);
            mButton = itemView.findViewById(R.id.list_item_sound_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBeatBox.play(mSound);
                }
            });
        }

        public void bindSound(Sound sound) {
            mSound = sound;
            mButton.setText(sound.getName());
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> mSounds;

        public void setSounds(List<Sound> sounds) {
            mSounds = sounds;
        }

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_sound, parent, false);
            return new SoundHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

























}
