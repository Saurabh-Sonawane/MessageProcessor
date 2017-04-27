# MessageProcessor

Min JDK Requirement : 1.8

Min Gradle Requirement : 3.0

Run application : java -jar message.processor-1.0-SNAPSHOT.jar

Following are the class and domain details about the source code
1. Class Message :  This is used to define interface for different type of messages. We can add create all 3 kind of message mentioned in document. This message object will be passed from third party who want to make use of our API.

2. Class MessageProcessor : This class will be used as controller to perform business logic as given and to generate and print report.

3. Class ReportHelper : This class is responsible to print the report on console.

4. Sale : Class to simulate sale entity

5. ProductWiseSale : Class to store the product type wise sale and adjustment details. This will be used to perform calculation and to generate the report. This class has list of adjustment as member to record the adjustments for that product type sale.

6. MessageProcessorTest : Unit test class for MessageProcessor
