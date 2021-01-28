import org.jenkinsci.plugins.github.GitHubPlugin
import org.jenkinsci.plugins.github.config.GitHubServerConfig
import jenkins.model.Jenkins

def jenkins = Jenkins.getInstance()

def server = new GitHubServerConfig('github-plugin-token')
server.name = 'GitHub'
server.setManageHooks(true)

GitHubPlugin.configuration().getConfigs().add(server)

jenkins.save()
