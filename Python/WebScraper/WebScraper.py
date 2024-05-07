import requests
import sys
from bs4 import BeautifulSoup

def fetch(url: str, file: str):
    response = requests.get(url)
    
    if response.status_code == 200:
        soup = BeautifulSoup(response.content, 'html.parser')
        with open(file, 'w', encoding='UTF-8') as f:
            f.write(soup.prettify())
        print(f'Successfully written to {file}')
    else:
        print(f'Failed to parse {url}')
        sys.exit()
    
if __name__ == '__main__':
    if len(sys.argv) != 3:
        print('Usage: python WebScraper <URL> <output file>')
        sys.exit()
    
    url = sys.argv[1]
    file = sys.argv[2]

    fetch(url, file)