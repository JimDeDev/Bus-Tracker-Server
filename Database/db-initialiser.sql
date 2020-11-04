USE tracker_schema;

CREATE TABLE transitStop (
    ID VARCHAR(100),
    stopCode INT(6),
    StopName VARCHAR(100),
    StopLat FLOAT(10, 5),
    StopLng FLOAT(10, 5),
    Geom VARCHAR(100),
    PRIMARY KEY (ID)
);

CREATE TABLE route (
    ID VARCHAR(100),
    RouteShortName VARCHAR(100),
    RouteLongName VARCHAR(100),
    RouteType int(2),
    PRIMARY KEY (ID)
);
