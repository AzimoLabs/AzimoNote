package com.example.labs.azimo.note.instructions;

import com.azimolabs.conditionwatcher.Instruction;
import com.example.labs.azimo.note.config.AzimoTestApplication;

/**
 * Created by F1sherKK on 31/07/2017.
 */

public class EnteredActivityInstruction extends Instruction {
    @Override
    public String getDescription() {
        return "Should entered activity: " + getDataContainer().getSerializable(InstructionKeys.KEY_SERIALIZABLE);
    }

    @Override
    public boolean checkCondition() {
        Class activity = (Class) getDataContainer().getSerializable(InstructionKeys.KEY_SERIALIZABLE);
        return activity != null && AzimoTestApplication.getInstance().getCurrentActivity() != null
                && AzimoTestApplication.getInstance().getCurrentActivity().getClass().isAssignableFrom(activity);
    }
}