# real-time-price-comparison

## Description
This is an API which automatically search grocery stores for a specific item, and returns a response for a client to use.

## Project Status
This project is currently in development.

## Server startup Instructions

1. Run the server


2. Send a post request using:
`curl -X 'POST'   'http://localhost:9000/item' -H 'Content-Type: string' -d 'snowball'`
where snowball is the desired item.


3. Wait until server returns Http status 200 which means OK.


4. Send a get request using:
`curl -X 'GET'   'http://localhost:9000/item'   -H 'accept: application/json'`
to see the list of Items.


## push using makefile
`make git m="your message"`


## HostLocation
`http://localhost:9000/`

