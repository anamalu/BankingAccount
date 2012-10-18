start C:\java\jdk1.3\bin\tnameserv -ORBInitialPort 1000
start C:\java\jdk1.3\bin\java -classpath .;C:\PEJ\lib\jdo.jar;C:\PEJ\lib\j2ee.jar;C:\PEJ\lib\pej.jar; com.hywy.samples.remote.iiop.TellerServer
pause
C:\java\jdk1.3\bin\java -classpath .;C:\PEJ\lib\jdo.jar;C:\PEJ\lib\j2ee.jar;C:\PEJ\lib\pej.jar; com.hywy.samples.remote.client.CORBAClient