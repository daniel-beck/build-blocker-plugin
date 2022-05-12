package io.jenkins.plugins.buildblocker;

import hudson.Extension;
import hudson.ExtensionList;
import hudson.model.Action;
import hudson.model.Queue;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Extension
public class BuildBlockerHandler extends Queue.QueueDecisionHandler {

    public static final Logger LOGGER = Logger.getLogger(BuildBlockerHandler.class.getName());

    @Override
    public boolean shouldSchedule(Queue.Task p, List<Action> actions) {
        final boolean shouldSchedule = internalShouldSchedule();
        if (!shouldSchedule) {
            LOGGER.log(Level.INFO, "Rejecting scheduling of " + p);
        }
        return shouldSchedule;
    }

    private boolean internalShouldSchedule() {
        try {
            return !ExtensionList.lookupSingleton(BuildBlockerConfiguration.class).isBlockScheduling();
        } catch (RuntimeException ex) {
            LOGGER.log(Level.WARNING, "Failed to look up job blocker configuration", ex);
            return true;
        }
    }
}
