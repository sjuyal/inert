Inert - Instant Multi Browser Testing!

Project Description:
----------------------------------------------------------------

Target User:
----------------------------------------------------------------
The product is targeted towards the QA Tester and the QA Team of a 
company based on a web application like flipkart.com, facebook.com, etc.

QA tester will upload the JAVA file which is the selenium based java 
script in which he has written the code to test the web application.

Steps to follow:
----------------------------------------------------------------
1. Upload:
	Upload the java file on to the server.
2. Select:
	Now the QA tester needs to select the browser on which he needs to 
	test the file.
3. Execute:
	Now user clicks on the Execute button which will execute the selenium 
	file on the server.

Project Flow (Technical):
----------------------------------------------------------------

User will be provided with a template jar inertdriver.jar. Jar file will contain InertDriver 
which will extend the WebDriver Class of Selenium. User will run a loop getting
all the instances of the driver i.e either chrome, firefox or internet explorer
driver instance.

User's only task is to upload the sample selenium java file and select the browsers
in which he want to execute the script. For the given selected browsers, an XML file 
will be generated in the server. That XML file will be used in executing the script
in the server.

Depending upon the script file uploaded and the browsers he has selected, testing will
be done on the server for those browsers. After uploading the file, result will be exposed
to the user through REST API. User on client end will keep on listening to the exposed REST 
function


Technologies Used:
----------------------------------------------------------------

1. Apache Spark Server  - for exposing REST API
2. Selenium Driver      - for testing user script on server
3. IntelliJ IDEA	- for development


