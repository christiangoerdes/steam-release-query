# steam-release-query

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
