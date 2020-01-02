set projectLocation=D:\Testing\EclipseWorkplace\Accountpaybles_Chrome
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*;%projectLocation%\src\*
java org.testng.TestNG %projectLocation%\POTestTestNG.xml
pause