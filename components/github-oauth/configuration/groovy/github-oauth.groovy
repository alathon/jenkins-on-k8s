#!groovy

// The below is a modified/stripped version of the GitHub auth strategy from
// https://github.com/cloudposse/jenkins/blob/master/init.groovy

import org.jenkinsci.plugins.GithubAuthorizationStrategy
import org.jenkinsci.plugins.GithubSecurityRealm

def isValidString = { value ->
    if (value != null && value instanceof String && value.trim() != "") {
        return true
    }
    return false
}

def env = System.getenv()
def jenkins = Jenkins.getInstance()

// Configure Github Authentication Security Realm and GitHub Committer Authorization Strategy
def configureGitHubAuthorizationStrategy = { clientId, clientSecret, admins, orgNames, oauthScopes ->
    if (!isValidString(clientId)) {
        throw new Throwable("'JENKINS_GITHUB_CLIENT_ID' is required for GitHub Authorization Strategy")
    }
    if (!isValidString(clientSecret)) {
        throw new Throwable("'JENKINS_GITHUB_CLIENT_SECRET' is required for GitHub Authorization Strategy")
    }
    if (!isValidString(admins)) {
        throw new Throwable("'JENKINS_GITHUB_ADMINS' is required for GitHub Authorization Strategy")
    }
    if (!isValidString(orgNames)) {
        throw new Throwable("'JENKINS_GITHUB_ORG_NAMES' is required for GitHub Authorization Strategy")
    }
    if (!isValidString(oauthScopes)) {
        throw new Throwable("'JENKINS_GITHUB_OAUTH_SCOPES' is required for GitHub Authorization Strategy")
    }

    // https://github.com/mocleiri/github-oauth-plugin/blob/master/src/main/java/org/jenkinsci/plugins/GithubSecurityRealm.java
    def githubSecurityRealm = new GithubSecurityRealm(
            "https://github.com",
            "https://api.github.com",
            clientId,
            clientSecret,
            oauthScopes
    )

    // https://github.com/jenkinsci/github-oauth-plugin/blob/master/src/main/java/org/jenkinsci/plugins/GithubAuthorizationStrategy.java
    def githubAuthorizationStrategy = new GithubAuthorizationStrategy(
            admins,    /*adminUserNames*/
            false,     /*authenticatedUserReadPermission*/
            true,      /*useRepositoryPermissions*/
            false,     /*authenticatedUserCreateJobPermission*/
            orgNames,  /*organizationNames*/
            true,      /*allowGithubWebHookPermission*/
            false,     /*allowCcTrayPermission*/
            false,     /*allowAnonymousReadPermission*/
            false      /*allowAnonymousJobStatusPermission*/
    )

    jenkins.setSecurityRealm(githubSecurityRealm)
    jenkins.setAuthorizationStrategy(githubAuthorizationStrategy)
    jenkins.save()
}

configureGitHubAuthorizationStrategy(
        env.JENKINS_GITHUB_CLIENT_ID,
        env.JENKINS_GITHUB_CLIENT_SECRET,
        env.JENKINS_GITHUB_ADMINS,
        env.JENKINS_GITHUB_ORG_NAMES,
        env.JENKINS_GITHUB_OAUTH_SCOPES
)

jenkins.save()
