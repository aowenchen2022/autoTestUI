@ECHO OFF

set DEBUGINFO=
set BROWSER=
set BUYERBROWSER=
set DOMAIN=

for %%A in (%*) do (

if "%%A" == "debug" set DEBUGINFO=-Dmaven.surefire.debug="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8001 -Xnoagent -Djava.compiler=NONE"
if "%%A" == "ie" set BROWSER=-Dselenium.browser=IE
if "%%A" == "ff" set BROWSER=-Dselenium.browser=FireFox
if "%%A" == "chrome" set BROWSER=-Dselenium.browser=Chrome
if "%%A" == "buyerie" set BUYERBROWSER=-Dselenium.browser.buyer=IE
if "%%A" == "buyerff" set BUYERBROWSER=-Dselenium.browser.buyer=FireFox
if "%%A" == "buyerchrome" set BUYERBROWSER=-Dselenium.browser.buyer=Chrome
if "%%A" == "sqa" set DOMAIN=-Dselenium.domain=sqa
if "%%A" == "spd" set DOMAIN=-Dselenium.domain=spd
if "%%A" == "sdm" set DOMAIN=-Dselenium.domain=sdm
if "%%A" == "qa2" set DOMAIN=-Dselenium.domain=qa2
)

mvn clean integration-test %BROWSER% %DEBUGINFO% %BUYERBROWSER% %DOMAIN% -DtestngFile=testngOnboardingSP.xml