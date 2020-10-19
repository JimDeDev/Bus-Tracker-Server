# Bus Tracker Server

This is a java application that is used to connect to Auckland Transports real time API.

## Database

The tracker database is a MySql databse housed in a docker container.

To start the database use the following command

docker run -p 3306:3306 --name trackerDB trackerdb:0.4
