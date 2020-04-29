
— inputs 



---------------------------------------------------------------- ACTIVITYID------------------------------------------------------------------------------------



INSERT INTO "G00069559"."ACTIVITYID" (ACTIVITY_ID, NAME) VALUES ('1', 'Donor’);

INSERT INTO "G00069559"."ACTIVITYID" (ACTIVITY_ID, NAME) VALUES ('2', 'Receiver’);

INSERT INTO "G00069559"."ACTIVITYID" (ACTIVITY_ID, NAME) VALUES ('3', 'Both’);





------------------------------------------------------------------------------------ MEMBER------------------------------------------------------------------------------------



INSERT INTO "G00069559"."MEMBER" (MEMBER_ID, EMAIL, PASSWORD, PHONE_NUMBER, ADDRESS, CITY, COUNTRY, MEMBER_TYPE, ACTIVITY_ID) VALUES ('I0001', 'Noraj@gmail.com', 'Pass', '0566315432', 'Qurayin', 'Sharjah', 'UAE', 'I', ‘1’);

INSERT INTO "G00069559"."MEMBER" (MEMBER_ID, EMAIL, PASSWORD, PHONE_NUMBER, ADDRESS, CITY, COUNTRY, MEMBER_TYPE, ACTIVITY_ID) VALUES ('I0002', 'Adam2000@gmail.com', 'Pass', '050654321', 'Mirdef', 'Dubai', 'UAE', 'I', ‘1’);

INSERT INTO "G00069559"."MEMBER" (MEMBER_ID, EMAIL, PASSWORD, PHONE_NUMBER, ADDRESS, CITY, COUNTRY, MEMBER_TYPE, ACTIVITY_ID) VALUES ('I0003', 'JmSmith@outlook.com', 'Pass', '065111111', 'Warqa', 'Dubai', 'UAe', 'I', ‘3’);

INSERT INTO "G00069559"."MEMBER" (MEMBER_ID, EMAIL, PASSWORD, PHONE_NUMBER, ADDRESS, CITY, COUNTRY, MEMBER_TYPE, ACTIVITY_ID) VALUES ('O0001', 'DHA@org.ae', 'Pass', '04600500', 'Alwasal', 'Dubai', 'UAE', 'o', ‘3’);

INSERT INTO "G00069559"."MEMBER" (MEMBER_ID, EMAIL, PASSWORD, PHONE_NUMBER, ADDRESS, CITY, COUNTRY, MEMBER_TYPE, ACTIVITY_ID) VALUES ('O0002', 'SHA@org.ae', 'Pass', '06500600', 'AlKhan', 'Sharjah', 'UAE', 'O', ‘3’);

INSERT INTO "G00069559"."MEMBER" (MEMBER_ID, EMAIL, PASSWORD, PHONE_NUMBER, ADDRESS, CITY, COUNTRY, MEMBER_TYPE, ACTIVITY_ID) VALUES ('O0003', 'AHA@org.ae', 'Pass', '07500400', 'Aljarf', 'Ajman', 'UAE', 'O', ‘3’);



------------------------------------------------------------------------------------HEALTH_ORGANIZATION------------------------------------------------------------------------------------



INSERT INTO "G00069559"."HEALTH_ORGANIZATION" (HEALTH_ORG_ID, ORG_NAME, LISENCE_KEY) VALUES ('O0001', 'Dubai Health Authority ', 'DHA2020’);

INSERT INTO "G00069559"."HEALTH_ORGANIZATION" (HEALTH_ORG_ID, ORG_NAME, LISENCE_KEY) VALUES ('O0002', 'Sharjah Health Authority ', 'SHA2020’);

INSERT INTO "G00069559"."HEALTH_ORGANIZATION" (HEALTH_ORG_ID, ORG_NAME, LISENCE_KEY) VALUES ('O0003', 'Ajman Health Authority ', 'AHA2020’);



------------------------------------------------------------------------------------INDIVIDUAL------------------------------------------------------------------------------------



INSERT INTO "G00069559"."INDIVIDUAL" (INDIVIDUAL_ID, FIRST_NAME, LAST_NAME, BIRTHDAY, HEIGHT_CM, WEIGHT_KG, GENDER, APPLIED_LICENSE_KEY) VALUES ('I0001', 'Nora', 'Ahmed', TO_DATE('1990-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '171', '70', 'F', 'AHA2020’);

INSERT INTO "G00069559"."INDIVIDUAL" (INDIVIDUAL_ID, FIRST_NAME, LAST_NAME, BIRTHDAY, HEIGHT_CM, WEIGHT_KG, GENDER, APPLIED_LICENSE_KEY) VALUES ('I0002', 'Adam', 'Zaki', TO_DATE('1970-10-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '165', '89', 'M', 'SHA2020’);

INSERT INTO "G00069559"."INDIVIDUAL" (INDIVIDUAL_ID, FIRST_NAME, LAST_NAME, BIRTHDAY, HEIGHT_CM, WEIGHT_KG, GENDER, APPLIED_LICENSE_KEY) VALUES ('I0003', 'Sam', 'Smith', TO_DATE('1985-12-04 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '180', '77', 'M', 'DHA2020’);







------------------------------------------------------------------------------------ MAP_ORG_PARTNER------------------------------------------------------------------------------------



INSERT INTO "G00069559"."MAP_ORG_PARTNER" (HEALTH_ORG_ID1, HEALTH_ORG_ID2) VALUES ('O0001', 'O0002’);



------------------------------------------------------------------------------------STATE ------------------------------------------------------------------------------------

INSERT INTO "G00069559"."STATE" (STATE_ID, NAME) VALUES ('1', 'Available ‘);

INSERT INTO "G00069559"."STATE" (STATE_ID, NAME) VALUES ('2', 'Unavailable’);

INSERT INTO "G00069559"."STATE" (STATE_ID, NAME) VALUES ('3', 'Requested ‘);



------------------------------------------------------------------------------------ ENTITY_TYPE ------------------------------------------------------------------------------------



INSERT INTO "G00069559"."ENTITY_TYPE" (ENTITY_TYPE_ID, ENTITY_TYPE_NAME) VALUES ('1', 'Blood’);

INSERT INTO "G00069559"."ENTITY_TYPE" (ENTITY_TYPE_ID, ENTITY_TYPE_NAME) VALUES ('2', 'Kidney’);

INSERT INTO "G00069559"."ENTITY_TYPE" (ENTITY_TYPE_ID, ENTITY_TYPE_NAME) VALUES ('3', 'Liver’);

INSERT INTO "G00069559"."ENTITY_TYPE" (ENTITY_TYPE_ID, ENTITY_TYPE_NAME) VALUES ('4', 'Eye’);

INSERT INTO "G00069559"."ENTITY_TYPE" (ENTITY_TYPE_ID, ENTITY_TYPE_NAME) VALUES ('5', 'Bone marrow’);

INSERT INTO "G00069559"."ENTITY_TYPE" (ENTITY_TYPE_ID, ENTITY_TYPE_NAME) VALUES ('6', 'Heart’);



------------------------------------------------------------------------------------TEST------------------------------------------------------------------------------------



INSERT INTO "G00069559"."TEST" (TEST_ID, MEMBER_ID, BLOOD, PET, HTIV, HIV, BONEMARROW, HEPATITISB) VALUES ('1', 'I0001', '1', '1', '1', '1', '1', ‘1’);

INSERT INTO "G00069559"."TEST" (TEST_ID, MEMBER_ID, BLOOD, URINE, COLONSCOPY, ELECTROCARDIOGRAM, PET, HTIV, HIV, BONEMARROW, HEPATITISB) VALUES ('2', 'I0002', '1', '1', '1', '1', '1', '1', '1', '1', ‘1’);

INSERT INTO "G00069559"."TEST" (TEST_ID, MEMBER_ID, BLOOD, URINE, HEPATITISC, CROSSMATCH) VALUES ('3', 'I0003', '1', '0', '1', ‘1’);



------------------------------------------------------------------------------------DONATION_ENTITY------------------------------------------------------------------------------------



INSERT INTO "G00069559"."DONATION_ENTITY" (DONATION_ENTITY_ID, STATE_ID, MEMBER_ID, ENTITY_TYPE_ID) VALUES ('1', '1', 'I0001', ‘1’);

INSERT INTO "G00069559"."DONATION_ENTITY" (DONATION_ENTITY_ID, STATE_ID, MEMBER_ID, ENTITY_TYPE_ID) VALUES ('2', '1', 'I0002', ‘3’);



COMPLETED_DONATION_LOG



INSERT INTO "G00069559"."COMPLETED_DONATION_LOG" (CD_ID, RECEPIENT_ID, DONATION_DATE, DONATION_ENTITY_ID) VALUES ('1', 'O0001', TO_DATE('2020-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), ‘1’);



------------------------------------------------------------------------------------ELIGIBILITY_TO_DONATE------------------------------------------------------------------------------------



INSERT INTO "G00069559"."ELIGIBILITY_TO_DONATE" (ID, TEST_ID, BLOOD, KIDNEY, LIVER, EYES, BONE_MARROW, HEART) VALUES ('1', '1', '1', '0', '0', '0', '0', ‘0’);

INSERT INTO "G00069559"."ELIGIBILITY_TO_DONATE" (ID, TEST_ID, BLOOD, KIDNEY, LIVER, EYES, BONE_MARROW, HEART) VALUES ('2', '2', '1', '0', '1', '0', '0', ‘0’);

INSERT INTO "G00069559"."ELIGIBILITY_TO_DONATE" (ID, TEST_ID, BLOOD, KIDNEY, LIVER, EYES, BONE_MARROW, HEART) VALUES ('3', '3', '0', '1', '0', '0', '0', ‘0’);

