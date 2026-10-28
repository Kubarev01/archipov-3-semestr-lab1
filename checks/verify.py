"""Run against a freshly started application: python3 checks/verify.py."""
import json
from pathlib import Path
from urllib.request import urlopen
from urllib.error import HTTPError
cases = json.loads(Path(__file__).with_name('cases.json').read_text())
for path, expected_status, expected in cases:
    try:
        response = urlopen('http://localhost:8080' + path)
    except HTTPError as error:
        response = error
    body = response.read().decode()
    assert response.status == expected_status, (path, response.status)
    actual = json.loads(body) if isinstance(expected, (list, dict)) else body
    assert expected is None or actual == expected, (path, actual, expected)
    print(f'PASS {response.status} GET {path} => {body}')
print(f'{len(cases)} HTTP checks passed')
