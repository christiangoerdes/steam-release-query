# steam-release-query GraphQL API
This is a GraphQL API called steam-release-query which provides information about Steam game release dates, names, ratings, and more. The API can be queried using GraphiQL, a user-friendly in-browser IDE for exploring and testing GraphQL APIs.

## Using the API with IntelliJ IDEA

To run and test the Spring Boot GraphQL API, follow these steps:

- Prerequisites: Make sure you have Java 17 and IntelliJ IDEA installed on your machine. If not, please refer to the official documentation for instructions on how to install these dependencies.
- Import the API project: Open IntelliJ IDEA and import the API project by selecting "Open" from the welcome screen or navigating to File → Open in the menu. Choose the root directory of the API project and click "Open" to import it.
- Configure the JDK: Ensure that IntelliJ IDEA is configured to use Java 17 as the JDK for the API project. You can check and modify the JDK settings by navigating to File → Project Structure and selecting the appropriate JDK version.
- Run the API: Locate the main class of the API (usually annotated with @SpringBootApplication). Right-click on the main class and select "Run \<main class name>" from the context menu. Alternatively, you can click on the green triangle icon next to the main class to run it.
- API Access: Once the API is running, you can access it using HTTP requests. The API exposes a GraphQL endpoint that accepts GraphQL queries and mutations.
- GraphQL Playground: Open a web browser and navigate to http://localhost:8080/graphiql. You can use the Playground to send queries and mutations and see the results. An example query would be:

        {
          releases {
            name
            link
            release
            peakPlayers
            positiveReviews
            negativeReviews
            totalReviews
            rating
          } 
        }


## Update the dataset

To update the dataset, follow these steps:

- Ensure that you have the latest version of Python installed on your computer.
- Copy your kaggle.json API credentials file into the resources/data directory. It is important to have this file in order to download the latest data from Kaggle. If you don't have one, you can create it by going to https://www.kaggle.com/settings and clicking "Create New Token".
- Run the following command to execute the script: 
    
      python3 import_data.py
    
  This will authenticate your Kaggle account using the kaggle.json file and download the dataset.
- Once the dataset is downloaded, the script will convert the CSV file to a JSON file.
- The updated dataset will be available in the resources/data directory as steam-releases.json.

Please note that the script will delete the previous version of the dataset before downloading the new one.



