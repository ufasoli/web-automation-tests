java -jar C:\dev\bin\selenium-server\selenium-server-standalone-2.37.0.jar -role node  -hub http://localhost:4444/grid/register -Dwebdriver.chrome.driver=C:\dev\workspaces\idea\web-automation-tests\simple-webapp\ext\webdrivers\chrome\chromedriver_windows.exe -browser browserName=chrome,maxInstances=10