# tic-tac-toe

This is a simple desktop multithread <a href="https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html">Tic-tac-toe</a> 
game made with programming language Java. The objective of it was to attend a required project
of <a href="https://en.wikipedia.org/wiki/Distributed_computing">Distributed Computing</a>
class in college.
The main requirement was to used only sockets to make communication with client and server.

## Features

<dl>
  <dt>Networking</dt>
  <dd>
    This project uses <a href="https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html">Socket</a>(TCP API) from <a href="https://docs.oracle.com/javase/8/docs/api/java/net/package-summary.html">java.net</a> library.
  </dd>
  <dt>Threads</dt>
  <dd>
    Uses <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html">Thread</a> for continuos 
    execution of GUI and communication between server and client.
  </dd>
</dl>

## Quick start

0. You must have JDK installed(In this project I used openjdk 19.0.1). I recommend to use <a href="https://sdkman.io/">sdkman</a> for installing your JDK version. 
1. The easy way to run this project is using <a href="https://netbeans.apache.org/">Netbeans</a> IDE because contains all standard libraries used in project.
2. Open a terminal and choose a place to clone the repository. Use command `git clone https://github.com/matheusfcorocher/tic-tac-toe.git`
3. Open the project with Netbeans and run two client view and server view.
4. Start first the server and then connect clients to it.
5. Have fun!
