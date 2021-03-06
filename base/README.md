# Base configuration
This folder provides the bare minimum configuration required to set up Jenkins within a Kubernetes cluster, and use that cluster as a cloud provider to run builds with.

## Usage
This configuration can be used to deploy Jenkins directly if you want, although the configured Jenkins instance is not especially useful without further plugins. By default this Jenkins instance is open, and requires no login credentials to access.

### Examples of usage
An example of setting up basic authorization and an admin account can be found [here](../examples/admin-password). This example also shows you how Kubernetes Secrets can be [read into Jenkins](../examples/admin-password/secrets.yaml) for use in builds.

### Installed plugins
This configuration installs the following plugins:
 - `kubernetes - latest version`

### Secrets
This configuration does not assume any secrets are available in the namespace it is deployed in.

### Environment variables
This configuration does not require any environment variables to operate.

This configuration does accept a number of optional environment variables, to configure functionality.

| Name | Description | Example | Default |
|------|-------------|---------|---------|
| DONT_INSTALL_LOCAL_CLOUD | Skip configuration of a local Cloud. Defining this will prevent builds from running in the local cluster. | YES | |
| LOCAL_CLOUD_NAME | Set the name of the local Cloud. This is useful if you require the local Cloud to be particularly named for further configuration | myCloud | kubernetes |

## Permissions
The base configuration sets up a number of permissions for the Jenkins instance, to allow it to perform builds in the local Kubernetes cluster, and read Secrets and ConfigMaps from within the same namespace that Jenkins runs in. Due to these permissions being set up, it is recommended to run Jenkins within it's own namespace, and not the `default` namespace.
