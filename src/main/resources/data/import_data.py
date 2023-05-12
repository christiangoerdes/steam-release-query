import argparse
import csv
import json
import os
import subprocess
import zipfile


def csv_to_json(csv_file, json_file):
    data = []

    with open(csv_file, mode='r') as f:
        csv_reader = csv.DictReader(f)
        for row in csv_reader:
            game_data = {
                'title': row['game'],
                'link': row['link'],
                'release': row['release'],
                'peakPlayers': int(row['peak_players'].replace(',', '')),
                'positiveReviews': int(row['positive_reviews'].replace(',', '')),
                'negativeReviews': int(row['negative_reviews'].replace(',', '')),
                'totalReviews': int(row['total_reviews'].replace(',', '')),
                'rating': row['rating'],
            }
            data.append(game_data)

    with open(json_file, mode='w') as f:
        json.dump(data, f, indent=4)


def main():

    # Download dataset
    with open('kaggle.json') as f:
        kaggle_cred = json.load(f)

    os.environ['KAGGLE_USERNAME'] = kaggle_cred['username']
    os.environ['KAGGLE_KEY'] = kaggle_cred['key']

    dataset_name = 'whigmalwhim/steam-releases'

    subprocess.run(['kaggle', 'datasets', 'download', '-d', dataset_name])

    zip_file_name = 'steam-releases.zip'

    # Extract data from zip file
    with zipfile.ZipFile(zip_file_name, 'r') as zip_file:
        file_to_extract = 'games-release-ALL.csv'
        extract_path = '.'
        zip_file.extract(file_to_extract, extract_path)

    # Delete zip file
    os.remove(os.path.join('.', zip_file_name))

    # Convert CSV to JSON
    csv_to_json('games-release-ALL.csv', 'steam-releases.json')

    # Remove existing file
    os.remove(os.path.join('.', "games-release-ALL.csv"))


if __name__ == '__main__':
    main()
