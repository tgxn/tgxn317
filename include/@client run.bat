@echo off
title -GaMeR X-'s Client - Loading
color 0a

:start
echo.
echo ------------------------------------------------
echo     -GaMeR X-'s Client (x64), Starting up...
echo ------------------------------------------------
echo  Closing this cmd window WILL close the client.
echo   If you happen to find ANY bugs, please send 
echo    them to: domenic.horner@gmail.com, Thanks.
echo ------------------------------------------------
if exist "%HOMEDRIVE%\Program Files (x86)" goto x64
goto x86


:x64
title -GaMeR X-'s Client (x64) - Loader
echo         Auto-Detected 64-bit (x64) System
echo ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
echo    . C l i e n t   o u t p u t   b e l o w .
echo ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
echo.
java -Xmx1000m -cp .;lib/jl1.0.1.jar;lib/basicplayer3.0.jar client/Client
exit


:x86
title -GaMeR X-'s Client (x86) - Loader
echo         Auto-Detected 32-bit (x86) System
echo ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
echo    . C l i e n t   o u t p u t   b e l o w .
echo ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
echo.
"%HOMEDRIVE%\Program Files\Java\jre6\bin\java.exe" -Xmx1000m -cp .;lib/jl1.0.1.jar;lib/basicplayer3.0.jar client/Client
exit