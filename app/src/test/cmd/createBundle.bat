@echo off
echo "USAGE: createBundle <version>"
For /F "tokens=1* delims==" %%A IN (local.settings) DO (
    IF "%%A"=="DEPLOYMENT_DIR" set DEPLOYMENT_DIR=%%B
    IF "%%A"=="BUNDLETOOL" set BUNDLETOOL=%%B
    IF "%%A"=="KEYSTORE" set KEYSTORE=%%B
)
set VERSION=%1

echo DEPLOYMENT_DIR=%DEPLOYMENT_DIR%
echo BUNDLETOOL=%BUNDLETOOL%
echo KEYSTORE=%KEYSTORE%
echo VERSION=%VERSION%

java -jar %BUNDLETOOL% build-apks --bundle=%DEPLOYMENT_DIR%\Version_%VERSION%\release\app-release.aab --output=%DEPLOYMENT_DIR%\Version_%VERSION%\release\app-release.apks --ks=%KEYSTORE% --ks-pass=file:keystore.pwd --ks-key-alias=androidashreleaskey --key-pass=file:key.pwd --local-testing --connected-device



