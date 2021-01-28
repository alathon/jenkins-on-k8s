#!/usr/bin/groovy

import jenkins.model.Jenkins
import hudson.plugins.ansicolor.AnsiColorBuildWrapper

def jenkins = Jenkins.getInstance()

jenkins.getDescriptorByType(AnsiColorBuildWrapper.DescriptorImpl.class).setGlobalColorMapName("xterm")

jenkins.save()
