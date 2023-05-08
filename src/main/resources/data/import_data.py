import os
import json
import subprocess
import zipfile

with open('kaggle.json') as f:
    kaggle_cred = json.load(f)

os.environ['KAGGLE_USERNAME'] = kaggle_cred['username']
os.environ['KAGGLE_KEY'] = kaggle_cred['key']

dataset_name = 'whigmalwhim/steam-releases'

subprocess.run(['kaggle', 'datasets', 'download', '-d', dataset_name])

zip_file_name = 'steam-releases.zip'

with zipfile.ZipFile(zip_file_name, 'r') as zip_file:
    file_to_extract = 'games-release-ALL.csv'
    extract_path = '.'
    zip_file.extract(file_to_extract, extract_path)

os.remove(os.path.join('.', zip_file_name))
