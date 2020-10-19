USE tracker_schema;

CREATE TABLE transitStop (
    StopID VARCHAR(100),
    stopCode INT(6),
    StopName VARCHAR(100),
    StopLat FLOAT(10, 5),
    StopLng FLOAT(10, 5),
    Geom VARCHAR(100),
    PRIMARY KEY (StopID)
);
