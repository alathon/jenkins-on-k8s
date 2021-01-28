1. Nuking the Jenkins master pod nukes all build history. Not awful,
but ideally there should be a somewhat permanent build history somewhere.

2. Probably a good idea to run builds in namespaces divided by teams
or repositories, instead of in the general jenkins namespace; otherwise
all team-specific secrets need to live in the jenkins namespace, which
isn't a great idea.
