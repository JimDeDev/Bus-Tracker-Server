# Bus Tracker Server

This is a java application that is used to connect to Auckland Transports real time API.

## Database

The tracker database is a MySql database housed in a docker container.

To start the database use the following command
``` bash
docker run -p 3306:3306 --name trackerDB trackerdb:0.x
```
## Secrets

The secrets file stores API keys and credentials.
Auckland Transport api keys can be found on the [Auckland Transport Developer Portal](https://dev-portal.at.govt.nz/).

Use the sample structure below to create a secrets.json file and place it in the root directory.

### Sample secrets.json file
``` JSON
{
  "AT-API-Key": {
    "primary": "",
    "secondary": ""
  },
  "MySql-Login": {
    "username": "",
    "password": ""
  }
}
```


