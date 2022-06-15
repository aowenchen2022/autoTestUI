@ECHO OFF

set DEBUGINFO=
set BROWSER=
set BUYERBROWSER=
set DOMAIN=
set LOCALE=

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
if "%%A" == "scd" set DOMAIN=-Dselenium.domain=scd
if "%%A" == "US-English" set LOCALE=-Dselenium.locale=en_US
if "%%A" == "Euro-English" set LOCALE=-Dselenium.locale=en_EU
if "%%A" == "Germany-German" set LOCALE=-Dselenium.locale=de_DE
if "%%A" == "Spanish" set LOCALE=-Dselenium.locale=es_LA
)

mvn clean integration-test -Dselenium.browser=Chrome %DEBUGINFO% -Dselenium.browser.buyer=Chrome -Dselenium.domain=sqa %LOCALE% -DtestngFile=testngKmmsShipments.xml