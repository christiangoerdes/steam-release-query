import argparse
import csv
import json

def csv_to_json(csv_file, json_file):
    data = []

    with open(csv_file, mode='r') as f:
        csv_reader = csv.DictReader(f)
        for row in csv_reader:
            game_data = {
                'name': row['game'],
                'link': row['link'],
                'release': row['release'],
                'peak_players': int(row['peak_players'].replace(',', '')),
                'positive_reviews': int(row['positive_reviews'].replace(',', '')),
                'negative_reviews': int(row['negative_reviews'].replace(',', '')),
                'total_reviews': int(row['total_reviews'].replace(',', '')),
                'rating': row['rating'],
            }
            data.append(game_data)

    with open(json_file, mode='w') as f:
        json.dump(data, f, indent=4)

def main():
    parser = argparse.ArgumentParser(description='Convert a CSV file to a JSON document.')
    parser.add_argument('csv_file', help='The input CSV file.')
    parser.add_argument('json_file', help='The output JSON file.')

    args = parser.parse_args()
    csv_to_json(args.csv_file, args.json_file)

if __name__ == '__main__':
    main()
