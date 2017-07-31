package com.example.labs.azimo.automationtestsupervisorexample.instructions;

import android.os.Bundle;
import android.view.View;

import com.azimolabs.conditionwatcher.ConditionWatcher;

/**
 * Created by F1sherKK on 31/07/2017.
 */

public class InstructionUtils {

    public static void waitForEnteredActivity(Class activity) throws Exception {
        EnteredActivityInstruction instruction = new EnteredActivityInstruction();
        Bundle bundleWithActivityClass = new Bundle();
        bundleWithActivityClass.putSerializable(InstructionKeys.KEY_SERIALIZABLE, activity);
        instruction.setData(bundleWithActivityClass);
        ConditionWatcher.waitForCondition(instruction);
    }

    public static void waitForItemWithTextAppearAtPosOfNotesBoard(int pos, String text) throws Exception {
        NotesBoardItemWaitInstruction instruction = new NotesBoardItemWaitInstruction();
        Bundle bundleWithItemInfo = new Bundle();
        bundleWithItemInfo.putInt(InstructionKeys.KEY_INTEGER, pos);
        bundleWithItemInfo.putString(InstructionKeys.KEY_STRING, text);
        instruction.setData(bundleWithItemInfo);
        ConditionWatcher.waitForCondition(instruction);
    }

    public static void waitForNotesBoardRefreshingStatusIs(int visibility) throws Exception {
        NotesBoardLoadingInstruction instruction = new NotesBoardLoadingInstruction();
        Bundle bundleWithRefreshingStatus = new Bundle();
        bundleWithRefreshingStatus.putBoolean(InstructionKeys.KEY_BOOLEAN, visibility == View.VISIBLE);
        instruction.setData(bundleWithRefreshingStatus);
        ConditionWatcher.waitForCondition(instruction);
    }
}
