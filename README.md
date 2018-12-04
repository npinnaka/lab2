# lab2

FIXME

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein do clean, ring server-headless 3000
    
    end points
    -----------------------------------
    #add a new record
    {:url http://localhost:3000/records 
    :method post
    :content-type application/json
    "data" "Doer,John,M,White,10/10/1991"}
    
    #sort by name
    {:url http://localhost:3000/records/name 
    :method get}
    
    #sort by gender
    {:url http://localhost:3000/records/gender 
    :method get}
    
    #sort by birth0date
    {:url http://localhost:3000/records/birthdate 
    :method get}
        
 To run the application, run:

    lein do clean, run -m lab2.core resources/csv-data.csv resources/pipe-data.csv resources/space-data.csv   
    

## License

Copyright © 2018 FIXME
