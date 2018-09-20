<h1>Backend Developer Test – ATM Simulation</h1>


This is a Spring Boot Web application and Command Line application that simulates the Backend logic of a cash dispensing Automatic Teller Machine (ATM). It supports only $20 and $50 notes. The system dispenses only legal combinations of notes. If a request cannot be satisfied due to failure to find a suitable combination of notes, it reports an error condition.

You clone the repository: 
$ git clone https://github.com/IoannisTsamandouras/atm-simulation.git



How to run 

You need to have Java 8 and Maven installed to run the application.

<b>Command Line</b>: <pre>mvn clean install -P ConsoleApplication</pre><br>

<b>Web</b>:          <pre>mvn clean install</pre><br>

<b>Run</b>: <pre>java -jar target/atm-0.0.1-SNAPSHOT.jar</pre><br>



Requirements

We need an application that simulates the Backend logic of a cash dispensing Automatic Teller Machine (ATM). 

Of course the application is not required to distribute money, but it should be able to simulate and report the out come of people requesting money. 

This simulation will not require any authentication or PIN to access the ATM. 

Rather it is to be focused on keeping track of the current cash of the ATM, and dispensing only the notes available.

It should be possible to tell it that it has so many of each type of note during initialisation. After initialisation, it is only possible to remove notes.

It must know how many of each type of bank note it has and it should be able to report back how much of each note it has.

It must support $20 and $50 notes.

It should be able to dispense only legal combinations of notes. For example, a request for $100 can be satisfied by either five $20 notes or two $50 notes. It is not required to present a list of options.

If a request cannot be satisfied due to failure to find a suitable combination of notes, it should report an error condition in some fashion. For example, in an ATM with only $20 and $50 notes, it is not possible to dispense $30. 

Dispensing money should reduce the amount of available cash in the machine. 

Failure to dispense money due to an error should not reduce the amount of available cash in the machine.

