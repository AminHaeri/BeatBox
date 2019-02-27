package com.example.amin.beatbox.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amin.beatbox.model.BeatBox;
import com.example.amin.beatbox.R;
import com.example.amin.beatbox.databinding.FragmentBeatBoxBinding;
import com.example.amin.beatbox.databinding.ListItemSoundBinding;
import com.example.amin.beatbox.model.Sound;
import com.example.amin.beatbox.viewmodel.SoundViewModel;

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

    private FragmentBeatBoxBinding mFragmentBeatBoxBinding;
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
        mFragmentBeatBoxBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_beat_box, container, false);

        View view = mFragmentBeatBoxBinding.getRoot();

        mFragmentBeatBoxBinding.beatBoxRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        mSoundAdapter = new SoundAdapter(mBeatBox.getSounds());
        mFragmentBeatBoxBinding.beatBoxRecyclerView.setAdapter(mSoundAdapter);

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

        private ListItemSoundBinding mBinding;
        private Sound mSound;

        public SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
            mBinding.setSoundviewmodel(new SoundViewModel(mBeatBox));

            mBinding.listItemSoundButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBeatBox.play(mSound);
                }
            });

        }

        public void bindSound(Sound sound) {
            mSound = sound;
            mBinding.getSoundviewmodel().setSound(mSound);
            mBinding.executePendingBindings();
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
//            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_sound, parent, false);
            ListItemSoundBinding binding = DataBindingUtil
                    .inflate(LayoutInflater.from(getActivity()), R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
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
