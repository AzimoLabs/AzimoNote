package com.example.labs.azimo.automationtestsupervisorexample.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.labs.azimo.automationtestsupervisorexample.R;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.Note;
import com.example.labs.azimo.automationtestsupervisorexample.ui.adapter.model.NoteAdapterViewModel;
import com.example.labs.azimo.automationtestsupervisorexample.ui.adapter.viewholder.BaseViewHolder;
import com.example.labs.azimo.automationtestsupervisorexample.ui.adapter.viewholder.NoteViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by F1sherKK on 27/07/2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<BaseViewHolder<NoteAdapterViewModel>> {

    public static final int ITEM_NOTE_PREVIEW = 0;
    public static final int ITEM_NOTE_EDIT = 1;

    private final List<NoteAdapterViewModel> currentItems = new ArrayList<>();

    private final LayoutInflater inflater;

    public NotesAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public BaseViewHolder<NoteAdapterViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_NOTE_PREVIEW) {
            return new NoteViewHolder(
                    inflater.inflate(R.layout.list_item_note_preview, parent, false)
            );
        }
//        } else if (viewType == ITEM_NOTE) {
//            return new NoteAdapterViewModel(
//                    inflater.inflate(R.layout.list_item_loader, parent, false)
//            );
//        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        NoteAdapterViewModel viewModel = getItem(position);
        if (viewModel.getType() == NoteAdapterViewModel.TYPE_NOTE) {
            return ITEM_NOTE_PREVIEW;
        } else {
            return -1;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<NoteAdapterViewModel> holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return currentItems.size();
    }

    public void addItems(@NonNull List<Note> notes) {
        List<NoteAdapterViewModel> newItems = new ArrayList<>();
        for (Note note : notes) {
            boolean isUniqueIdCurrentlyInAdapter = false;
            for (NoteAdapterViewModel viewModel  : currentItems) {
              if (viewModel.getNote().getUniqueId().equals(note.getUniqueId())) {
                  isUniqueIdCurrentlyInAdapter = true;
              }
            }

            if (!isUniqueIdCurrentlyInAdapter) {
                NoteAdapterViewModel newViewModel = new NoteAdapterViewModel(note, ITEM_NOTE_PREVIEW);
                newItems.add(newViewModel);
            }
        }
        currentItems.addAll(newItems);
    }

    public NoteAdapterViewModel getItem(int pos) {
        return currentItems.get(pos);
    }
}