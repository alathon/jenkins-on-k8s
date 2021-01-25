import jenkins.model.JenkinsLocationConfiguration

def jlc = JenkinsLocationConfiguration.get()
jlc.setUrl("http://poseidon.staging.pleo.io")
jlc.save()
