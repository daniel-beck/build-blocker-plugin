package io.jenkins.plugins.buildblocker;

import hudson.Extension;
import jenkins.model.GlobalConfiguration;
import org.kohsuke.stapler.DataBoundSetter;

@Extension
public class BuildBlockerConfiguration extends GlobalConfiguration {

    private boolean blockScheduling;

    public BuildBlockerConfiguration() {
        load();
    }

    public boolean isBlockScheduling() {
        return blockScheduling;
    }

    @DataBoundSetter
    public void setBlockScheduling(boolean blockScheduling) {
        this.blockScheduling = blockScheduling;
    }
}
