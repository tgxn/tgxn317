@echo off
title -GaMeR X-'s Client - Loading
color 0a

:start
if exist "%HOMEDRIVE%\Program Files (x86)"  goto x64
goto x86


:x64
echo You can now close this window.
"C:/Program Files (x86)/Java/jre6/bin/javaw.exe" -Xmx1000m client/Client
exit


:x86
start javaw -Xmx1000m client/Client
exit