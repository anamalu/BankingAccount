cd C:\run\projects\bank\enhanced

rem ////////////////////////////////////
rem          RMI-IIOP Client Support
rem ////////////////////////////////////
C:\java\jdk1.3\bin\rmic -classpath . -iiop -idl com.hywy.samples.remote.TellerRemoteImpl
copy C:\run\projects\bank\enhanced\com\hywy\samples\remote\_TellerRemote_Stub.class C:\run\projects\bank\enhanced\com\hywy\samples\remote\client\
copy C:\run\projects\bank\enhanced\com\hywy\samples\remote\_TellerRemoteImpl_Tie.class C:\run\projects\bank\enhanced\com\hywy\samples\remote\client\

rem ////////////////////////////////////
rem          CORBA Client Support
rem ////////////////////////////////////
rem C:\java\jdk1.3\bin\idlj -v -i . -i C:\java\jdk1.3\lib -fclient -td . C:\run\projects\bank\enhanced\com\hywy\samples\remote\TellerRemote.idl
ant

