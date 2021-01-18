# E2EProject
RestAPI Validation


Pre Requisite information:


  1) The Testcase for validating the acceptance criteria is E2EProject\src\test\java\Academy\E2EProject\RestTestAPI.java.
  
       Acceptance Criteria:

        a) Name = "Carbon credits"

        b) CanRelist = true
        
        c) The Promotions element with Name = "Gallery" has a Description that contains the text "2x larger image"



  2) The dependent files are :

      a) E2EProject\src\main\java\resources\log4j2.xml
      b) E2EProject\src\test\java\Academy\E2EProject\ExtentReporterNG.java
      c) E2EProject\src\test\java\Academy\E2EProject\Listeners.java
      d) E2EProject\src\test\java\Academy\E2EProject\XlUtilities.java
      e) E2EProject\src\test\java\Academy\E2EProject\Rest.xlsx


Steps to Reproduce:

1) Run the the E2EProject\testng.xml file
2) The logs will be generated in the folder E2EProject\logs
3) Extent reports are present in the folder E2EProject\reports


Result:
The Testcase failed since the third acceptance ciretia failed as the Descritpion does not contain the text "2x larger image".

    
    
    
    
    
