@echo off
echo "USAGE: install-apk <version>"
For /F "tokens=1* delims==" %%A IN (local.settings) DO (
    IF "%%A"=="DEPLOYMENT_DIR" set DEPLOYMENT_DIR=%%B
)
set VERSION=%1

echo DEPLOYMENT_DIR=%DEPLOYMENT_DIR%
echo VERSION=%VERSION%

adb install %DEPLOYMENT_DIR%\Version_%1\release\app-release.apk