# Design and implement program to maintain Equity Positions with unit tests.

## How wo run
- mvn clean install & java -jar equity-position-0.0.1-SNAPSHOT.jar
- or open in IDEA as maven project
- default port is 8080 (you can change by add cmd parameter --server.port=port_number), default H2 is used, so currently no configuration .yam or .properties file used
- after app is running visit page http://127.0.0.1:8080/trade/currentPosition in browse
- or run curl http://127.0.0.1:8080/trade/currentPosition you will get some init data about the positions as following
    [{"securityIdentifier":"REL","position":-60},{"securityIdentifier":"ITC","position":0}]
- add a transaction command:
    ### curl -i -X POST -H "Content-Type:application/json"  -d '{"tradeId":"10","version":"1","securityCode":"IBM","quantity":"50","action":"INSERT","buySell":"BUY"}' http://127.0.0.1:8080/trade/action

## The design
- springboot entry main class EquityApplication.
- the rest controller is TradeController, there are 3 methods one is accept trade another is to review the current position result
- the third is to initialize some demo data via running the trade service
- insert/update/cancel are represented by InsertAction/UpdateAction/CancelAction