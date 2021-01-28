1. Nuking the Jenkins master pod nukes all build history. Not awful,
but ideally there should be a somewhat permanent build history somewhere.

2. Probably a good idea to run builds in namespaces divided by teams
or repositories, instead of in the general jenkins namespace; otherwise
all team-specific secrets need to live in the jenkins namespace, which
isn't a great idea.

3. The Organization webhook (which isn't really used for much) isn't registering,
as it says the Jenkins URL is not set. I.e.: 

2021-01-28 14:43:55.308+0000 [id=27]	SEVERE	c.c.jenkins.GitHubWebHook$1#apply: Skip registration of GHHook (The Jenkins URL is empty. Explicitly set the Jenkins URL in the global configuration or in the GitHub plugin configuration to manage webhooks.)
