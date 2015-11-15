# Remote deployment and debugging for Java

## Purpose
To automate the starting and stopping of Java VMs when doing remote debugging.

## Needed tools
* IntelliJ (Eclipse probably can do this too)
* WinSCP command line https://winscp.net/
* Gnu-screen must be installed on target system

## Configuration in IntelliJ
* Configure a remote debugging session
    * Run > Edit Configuration...
    * Add a new "Remote" config
        * Give it a name, and select
            * Single Instance Only: Checked (important!)
            * Transport: Socket
            * Debugger Mode: Attach
        * Enter the IP and port to use (can leave defaults)
        * In the "Before Launch" section:
            * Add a "Build Artifacts" and specify the one to deploy.
            * Add a "Run External Tool" and configure as follows
                * Name: "Deploy via WinSCP"
                * Program: Path/To/WinSCP.com
                * Paramteters: `/script="$ProjectFileDir$\deploy.script" /parameter "$ProjectFileDir$\out\artifacts\Path\to\nameofartifact.jar" "$ProjectName$" "$ProjectName$.jar"`
                * Working directory: Any that fits
            * Make sure that the "Run External Tool" action is last in the list.
  
## Usage
Whenever you now do Run > Debug 'your name of config', the JAR will be deployed to the remote server and a screen session started with a JVM running your JAR.

## Notes
The JVM will continue to run when you disconnect the debugger. There doesn't seem to be a way to fix that. It will however be terminated when you start your next debugging session.

## Misc
Q: Why not just use '&' to fork a child process?


A: Because WinSCP can't handle such caracters in the script it generates when `call`-ing commands.

