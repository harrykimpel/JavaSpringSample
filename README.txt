
BUILD INSTRUCTIONS:
===================
This is an Eclipse project by default.

You can build the project through Ant by running build.xml.  Please take note that you need to supply the 
tomcat home directory (tomcat.home.dir) in build.properties file.

This application is also built using Maven.  The pom.xml file is located under the top root directory. 

To build the project into a war file using Maven: from the terminal or command prompt, navigate to the directory path of the project, 
then enter the command "mvn package" (no quotes) or "mvn package -DskipTests=true" (no quotes) to skip JUnit tests.

	1.	Update "application.properties" & "spring-dbconfig.xml" file to point to correct database.	
	2.	Execute "Audit.sql" in application database for storing audit entries. File is available at "/<Project>/etc/".	
	3.	Execute “SecuritySetup.sql” on a database from where your authentication and authorization to be done for this application.	
	4.	Update "spring-dbconfig.xml" file to point to database on which you have executed above (SecuritySetup.sql).	
	5.	In case your application uses different tables for authentication & authorization, then follow below steps. Current application uses Spring Security based authentication & authorization.
		a.	Update "spring-security.xml" to change the  “users-by-username-query” query  under element <authentication-provider>  in this xml to specify from where the USERNAME and PASSWORD should be fetched by the application to be provided to Spring for authentication.	
		b.	Update "spring-security.xml" to change the  “authorities-by-username-query” query  under element <authentication-provider>  in this xml to specify from where the USERNAME and ROLES should be fetched by the application to be provided to Spring for authorization. 	
		c.	Update & add new “<intercept-url pattern/>” to specify the authorization for each role.
			<intercept-url pattern="/getLogin" access="permitAll" />
			<intercept-url pattern="/momentum/users" access="permitAll" />
			<intercept-url pattern="/momentum/user/*" access="hasRole('ROLE_ADMIN')" />
			<intercept-url pattern="/momentum/updateUser/*" access="hasRole('ROLE_ADMIN')" />
			<intercept-url pattern="/momentum/deleteUser/*" access="hasRole('ROLE_ADMIN')" />
			<intercept-url pattern="/momentum/getUserByName/*" access="hasRole('ROLE_ADMIN')" />		
			
	6. By default application has only English language support. To add any new language support into the application, you need to add new JS files as per below instructions:
		a. Go to path '<Project>\src\main\webapp\i18n' and update 'resources-locale_list.js' file to add the value of new language support as below. Add below statements as per the language:
			 {
       			 "label":"Spanish",
       			 "value":"es-es",
        		"description":"Spanish"
    		}
    		Note: Include ',' (comma) with start of above statement.
    		
    	b. Add a new file with naming convention as 'resources-locale_<LANGUAGE CODE>.js value in above file.
    	c. Copy all the content from file 'resources-locale_en-US.js' and paste it the new file 'resources-locale_<LANGUAGE CODE>.js.
    	d. Build and deploy the application and you will have a support for new language available.
    	
	7. Instructions for updating the Screens to be Responsive UI.
		a. Go to <Project-Name>/home.html and update the "home.html" file.
		b. Replace the <div class="container"> with <div class="container-fluid">. 
		c. Do not do Replace ALL operation as it will affect the "container" used for with other <div> class attribute.
		d. Do not replace the container where you find like "main-container-props".




