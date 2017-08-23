INSERT INTO PRICE_CONFIGURATION (PRICECONFIGURATIONID, PRODUCTID, LAYOUTCONFIGID, CREATED_BY, CREATEDDATE, UPDATED_BY, UPDATEDDATE)
VALUES (21493, 245617, 343715, 1437, '2016-08-29 16:46:46', 1437, '2016-08-29 16:46:46');

INSERT INTO VENUE_OVERVIEW_COMPONENTS_LC (VENUEOVERVIEWCOMPONENTSLCID, LC_ID, TYPE, LINK_ID, DISPLAYVALUE, SHAPE, X, Y, WIDTH, HEIGHT, ISVERTICAL, ISVISIBLE, BGCOLOR, FGCOLOR, FONTSIZE, SECTIONALIAS, LEVELALIAS)
VALUES
  (488490, 343715, 2, NULL, 'GENERAL ADMISSION', 0, 388, 141, 0, 0, 'F', 'T', 0, -6050358, 15, NULL,
   NULL);
INSERT INTO VENUE_OVERVIEW_COMPONENTS_LC (VENUEOVERVIEWCOMPONENTSLCID, LC_ID, TYPE, LINK_ID, DISPLAYVALUE, SHAPE, X, Y, WIDTH, HEIGHT, ISVERTICAL, ISVISIBLE, BGCOLOR, FGCOLOR, FONTSIZE, SECTIONALIAS, LEVELALIAS)
VALUES (488491, 343715, 2, NULL, 'Colour indicates price category', 0, 16, 30, 0, 0, 'F', 'T', 0,
        -6050358, 10, NULL, NULL);
INSERT INTO VENUE_OVERVIEW_COMPONENTS_LC (VENUEOVERVIEWCOMPONENTSLCID, LC_ID, TYPE, LINK_ID, DISPLAYVALUE, SHAPE, X, Y, WIDTH, HEIGHT, ISVERTICAL, ISVISIBLE, BGCOLOR, FGCOLOR, FONTSIZE, SECTIONALIAS, LEVELALIAS)
VALUES (488492, 343715, 2, NULL, 'Ticket price exclude booking fees', 0, 15, 45, 0, 0, 'F', 'T', 0,
        -6050358, 10, NULL, NULL);
INSERT INTO VENUE_OVERVIEW_COMPONENTS_LC (VENUEOVERVIEWCOMPONENTSLCID, LC_ID, TYPE, LINK_ID, DISPLAYVALUE, SHAPE, X, Y, WIDTH, HEIGHT, ISVERTICAL, ISVISIBLE, BGCOLOR, FGCOLOR, FONTSIZE, SECTIONALIAS, LEVELALIAS)
VALUES
  (488493, 343715, 1, 356865, 'General Admission', 0, 330, 105, 240, 150, 'F', 'T', 0, -16777216,
   NULL, 'General Admission', '-');
INSERT INTO VENUE_OVERVIEW_COMPONENTS_LC (VENUEOVERVIEWCOMPONENTSLCID, LC_ID, TYPE, LINK_ID, DISPLAYVALUE, SHAPE, X, Y, WIDTH, HEIGHT, ISVERTICAL, ISVISIBLE, BGCOLOR, FGCOLOR, FONTSIZE, SECTIONALIAS, LEVELALIAS)
VALUES
  (488494, 343715, 2, NULL, 'LEE KONG CHIAN NATURAL HISTORY MUSEUM', 0, 250, 37, 0, 0, 'F', 'T', 0,
   -6050358, 22, NULL, NULL);
INSERT INTO VENUE_OVERVIEW_COMPONENTS_LC (VENUEOVERVIEWCOMPONENTSLCID, LC_ID, TYPE, LINK_ID, DISPLAYVALUE, SHAPE, X, Y, WIDTH, HEIGHT, ISVERTICAL, ISVISIBLE, BGCOLOR, FGCOLOR, FONTSIZE, SECTIONALIAS, LEVELALIAS)
VALUES (488495, 343715, 2, NULL, 'Seat plan is NOT drawn to scale', 0, 15, 15, 0, 0, 'F', 'T', 0,
        -6050358, 10, NULL, NULL);


INSERT INTO VENUE_SEAT_LC (VENUESEATID, LC_ID, BLOCK_ID, LEVEL_ID, SECTION_ID, ROW_ID, SEATNO, SEATALIAS, SEATTYPE, RANK, SEATMAPLOCATION, SEATSTATUS, TICKETABLE, NEARESTENTRANCE, TICKETTYPE, SEATATTRIBUTES, AREAALIAS)
VALUES
  (2814126, 39228, 171272, 54956, 51312, 147769, 6, 6, 1, 283, '357;245', NULL, 1, 'Circle', 'RS',
   0, 'Center');

INSERT INTO VENUE_SECTION_LC (VENUESECTIONLCID, LC_ID, LEVEL_ID, SECTIONALIAS, AREAALIAS, NOOFSEATS, SECTIONTYPE, NEAREST_ENTRANCE_ID, RANK, ISSEATNUMPREVIEW)
VALUES (51312, 39228, 54956, 'Center', 'Center', 0, 'RS', NULL, 0, 'F');

INSERT INTO VENUE_OVERVIEW_COMPONENTS_LC (VENUEOVERVIEWCOMPONENTSLCID, LC_ID, TYPE, LINK_ID, DISPLAYVALUE, SHAPE, X, Y, WIDTH, HEIGHT, ISVERTICAL, ISVISIBLE, BGCOLOR, FGCOLOR, FONTSIZE, SECTIONALIAS, LEVELALIAS)
VALUES (171272, 39228, 0, NULL, NULL, 1, '195;271;271;571;571;630;630;195;195',
                '135;135;381;381;135;135;466;466;135', 0, 0, 'F', 'T', 0, -16777216, NULL, NULL,
        NULL);

INSERT INTO SALES_SEAT_INVENTORY (SALESSEATINVENTORYID, PRODUCT_ID, RANK, SEATSALESSTATUS, TICKETABLE, PRICE_CAT_ID, PRICE_CLASS_ID, PATRON_ID, RESERVEEXPIRYDATE, SYSTEM_SALE_CODE_ID, HOLD_BY, HOLDDATE, SOLD_BY, SOLDDATE, UPDATEDTIME, UPDATED_BY, HOLDREASON, HOLDEXPIRYNOTIFIED, SECTIONRANK, SPANKEY, VENUE_SEAT_LC_ID, VENUE_SECTION_LC_ID, SALESCYCLESTARTTIME, PACKAGE_ID, PACKAGEREQUIREMENT_ID, PRICEVALUE, SESSIONID, HOLDEXPIRYDATE)
VALUES (118491895, 218987, 283, 0, 1, 10201, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                                                                           '2016-06-07 12:56:41',
                                                                           1087, NULL, NULL, 0,
                                                                           'DBS Arts Centre - Home of SRTCenterCircleAA6',
                                                                           2814126, 51312, NULL,
        NULL, NULL, NULL, NULL, NULL);