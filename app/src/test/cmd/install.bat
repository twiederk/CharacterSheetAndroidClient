@echo off
echo "USAGE: install <version>"
For /F "tokens=1* delims==" %%A IN (local.settings) DO (
    IF "%%A"=="DEPLOYMENT_DIR" set DEPLOYMENT_DIR=%%B
    IF "%%A"=="BUNDLETOOL" set BUNDLETOOL=%%B
)
set VERSION=%1

echo DEPLOYMENT_DIR=%DEPLOYMENT_DIR%
echo BUNDLETOOL=%BUNDLETOOL%
echo VERSION=%VERSION%

java -jar %BUNDLETOOL% install-apks --apks=%DEPLOYMENT_DIR%\Version_%VERSION%\release\app-release.apks
